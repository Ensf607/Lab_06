package exercise_3;
/** 
 * Started by M. Moussavi
 * March 2015
 * Completed by: STUDENT(S) NAME
 */

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The ReadRecord class is used to deserialize and retrieve 
 * record information from binary file. 
 * Modified by: Ziad Chemali and Lotfi Hasni
 * @version 1.0
 * @since October 30, 2020
 *
 */
public class ReadRecord {
    
	/**
	 * Used to recover data from serialized objects.
	 */
    private ObjectInputStream input;
    
    
    /**
     * Opens an ObjectInputStream using a FileInputStream
     * @param name the file name
     */
    private void readObjectsFromFile(String name)
    {
        MusicRecord record ;
        
        try
        {
            input = new ObjectInputStream(new FileInputStream( name ) );
        }
        catch ( IOException ioException )
        {
            System.err.println( "Error opening file." );
        }
        
        /* The following loop is supposed to use readObject method of
         * ObjectInputSteam to read a MusicRecord object from a binary file that
         * contains several reords.
         * Loop should terminate when an EOFException is thrown.
         */
        
        try
        {
            while ( true )
            {
            	record = (MusicRecord) input.readObject();
            	System.out.println(record.getYear() + " " + record.getSongName() + " " +
            			record.getSingerName() + " " + record.getPurchasePrice());
           
            }   // END OF WHILE
        }
                // NECESSARY catch CLAUSES HERE
        catch(EOFException e) {
        	 ; 
        }
        catch(ClassNotFoundException e) {
        	System.err.println("Error ....");
        	e.printStackTrace();
        }
        catch(IOException e) {
        	System.err.println("Error ....");
        	e.printStackTrace();
        }
    }           // END OF METHOD 
    
    /**
	 * Creates a new ReadRecord object and reads objects from given file.
	 * @param args command line arguments (none for this program)
	 */
    public static void main(String [] args)
    {
        ReadRecord d = new ReadRecord();
        d.readObjectsFromFile("allSongs.ser");
    }
    
}