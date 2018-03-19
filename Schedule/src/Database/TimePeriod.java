package Database;

/**
 * Provides an abstraction for a single class. 
 * Uses 24-hour format to hold the time (0.00 - 23.59) 
 * Uses a short number to hold a day of the week (1 - Mon, 2 - Tue ... 7 - Sun).
 *
 */
public class TimePeriod {
	// Start time of the class
	private double startTime;
	// End time of the class
	private double endTime;
	// Weekday of the class
	private short weekDay;
	// The name of the teacher
	private String facultyName;
	// The building and the room number
	private String roomAddress;	

	/**
	 * Constructs a Time Period.
	 *
	 * @param startTime the start time of the class
	 * @param endTime the end time of the class 
	 * @param weekDay the day of the week      
	 * @param facultyName name of the teacher
	 * @param roomAddress the room address
	 */	
	public TimePeriod(double startTime, double endTime, short weekDay, String facultyName,
			String roomAddress)
	{

	}
	
	/**
	 * THIS IS WRITTEN FOR THE TESTER
	 */
	public TimePeriod() {
		
	}
}
