package exercise_3;
/**
 * Started by M. Moussavi
 * March 2015
 * Completed by: STUDENT(S) NAME
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The WriteRecord class is used to read from a text file containing music record 
 * information and create a binary serialized file containing music record objects.
 * Modified by: Ziad Chemali and Lotfi Hasni
 * @version 1.0
 * @since October 30, 2020
 *
 */
public class WriteRecord {

	/**
	 * For writing record objects to file.
	 */
	ObjectOutputStream objectOut = null;
	
	/**
	 * Used to hold current MusicRecord object for writing.
	 */
	MusicRecord record = null;
	
	/**
	 * For user input.
	 */
	Scanner stdin = null;
	
	/**
	 * For file input.
	 */
	Scanner textFileIn = null;

	/**
	 * Creates an blank MusicRecord object
	 */
	public WriteRecord() {
		record = new MusicRecord();
	}

	/**
	 * Initializes the data fields of a record object
	 * @param year - year that song was purchased
	 * @param songName - name of the song
	 * @param singerName - singer's name
	 * @param price - CD price
	 */
	public void setRecord(int year, String songName, String singerName,
                                                                 double price) {
		record.setSongName(songName);
		record.setSingerName(singerName);
		record.setYear(year);
		record.setPrice(price);
	}
    
	/**
	 * Opens a file input stream, using the data field textFileIn
	 * @param textFileName name of text file to open
	 */
	public void openFileInputStream(String textFileName) {
        
		try {
			textFileIn = new Scanner(new FileInputStream(textFileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Opens an ObjectOutputStream using objectOut data field
	 * @param objectFileName name of the object file to be created
	 */
	public void openObjectOutputStream(String objectFileName) {
        
		FileOutputStream fileOutput = null;
		try {
			fileOutput = new FileOutputStream(objectFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			objectOut = new ObjectOutputStream(fileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	/**
	 * Reads records from given text file, fills the blank MusicRecord
	 * created by the constructor with the existing data in the text
	 * file and serializes each record object into a binary file
	 */
	public void createObjectFile() {

		while (textFileIn.hasNext()) // loop until end of text file is reached
		{
			int year = Integer.parseInt(textFileIn.nextLine());
			System.out.print(year + "  ");       // echo data read from text file
            
			String songName = textFileIn.nextLine();
			System.out.print(songName + "  ");  // echo data read from text file
            
			String singerName = textFileIn.nextLine();
			System.out.print(singerName + "  "); // echo data read from text file
            
			double price = Double.parseDouble(textFileIn.nextLine());
			System.out.println(price + "  ");    // echo data read from text file
            
			setRecord(year, songName, singerName, price);
			textFileIn.nextLine();   // read the dashed lines and do nothing
			
			try {
				objectOut.writeObject(record);
				objectOut.reset();
			} catch (IOException e) {
				System.err.println("Error writing to file.");
				e.printStackTrace();
			} catch(NoSuchElementException e) {
				System.err.println("Invalid input.");
			}
		}
		
		try {
			if(objectOut != null)
				objectOut.close();
		}
		catch(IOException e) {
			System.err.println("Error closing file.");
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates a new WriteRecord object, serializes record objects and writes them to binary file.
	 * @param args command line arguments (none for this program)
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
        
		WriteRecord d = new WriteRecord();
        
		String textFileName = "someSongs.txt"; // Name of a text file that contains
                                               // song records
        
		String objectFileName = "mySongs.ser"; // Name of the binary file to
                                               // serialize record objects
        
		d.openFileInputStream(textFileName);   // open the text file to read from
        
		d.openObjectOutputStream(objectFileName); // open the object file to
                                                  // write music records into it
        
		d.createObjectFile();   // read records from opened text file, and write
                                // them into the object file.
	}
}
