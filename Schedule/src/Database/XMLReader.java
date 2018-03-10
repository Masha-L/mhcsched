package Database;
import java.io.IOException;
import org.xml.sax.SAXException;

/**
 * The class to read from XML file.
 */
public class XMLReader implements DataReader {

	/**
	 * Overrides the method from the interface
	 * 
	 * @see Database.DataReader#readData()
	 */
	@Override
	public Database readData() throws IOException, SAXException {
		
		return null;	
	}
	
	/**
	 * Extracts the subjects for the given department 
	 * 
	 * @param departmentName
	 * @return the formed department object
	 */
	public Department readDepartmentData(String departmentName) {
		
		return null;
		
	}

}