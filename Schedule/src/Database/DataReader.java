package Database;
/**
 * The interface to read the subject database from the external file.
 */
public interface DataReader {

	/**
	 * Reads data from the external file and composes an internal database out of it.
	 * 
	 * @return the instance of the database
	 * @throws Exception 
	 */
	Database readData() throws Exception;
	
}
