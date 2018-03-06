package Application;

/**
 * Provides an abstraction for a single class. 
 * Uses 24-hour format to hold the time (0.00 - 23.59) 
 * Uses a short number to hold a day of the week (1 - Mon, 2 - Tue ... 7 - Sun).
 *
 * @author Jack 
 * @version 2
 * @date 3/6/18
 */
public class TimePeriod {
	// Start time of the class
	private double startTime;
	// End time of the class
	private double endTime;
	// Weekday of the class
	private short weekDay;

	/**
	 * Constructs a Time Period.
	 *
	 * @param startTime the start time of the class
	 * @param endTime the end time of the class 
	 * @param weekDay the day of the week      
	 */	
	public TimePeriod(double startTime, double endTime, short weekDay)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekDay = weekDay;
	}
}
