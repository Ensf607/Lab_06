package exercise_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server class implementation where it accepts clients and communicate via
 * sockets
 *
 */

public class Server {
	int conn_counter = 0;// keeps track of clients connected
	private Socket aSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private ExecutorService pool;
	private Game game1, game2;

	/**
	 * initialize pool and serverSocket
	 */
	public Server() {
		try {
			serverSocket = new ServerSocket(9090);
			pool = Executors.newFixedThreadPool(10);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * infinite loop that keeps accepting clients, whenever two clients are
	 * connected then pool executes the threads for these two clients. Each client
	 * has a game object and each game object knows of the other
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void runServer() throws IOException, InterruptedException {
		try {
			while (true) {
				aSocket = serverSocket.accept();
				socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
				socketOut = new PrintWriter(aSocket.getOutputStream(), true);
				conn_counter++;
				System.out.println(conn_counter);
				if (conn_counter % 2 == 1) {
					game1 = new Game(socketOut, socketIn, 'X');
				}
				if (conn_counter % 2 == 0) {
					game2 = new Game(socketOut, socketIn, 'O');
					game1.setOtherGame(game2);
					game2.setOtherGame(game1);
					System.out.println("starting game");
					pool.execute(game1);
					pool.execute(game2);
					conn_counter = 0;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Good Bye!");
		socketIn.close();
		socketOut.close();
	}

	public static void main(String[] args) throws IOException {

		Server myServer = new Server();

		System.out.println("Server is now running.");
		try {
			myServer.runServer();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
