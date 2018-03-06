package Application;
/**
 * 
 * @author Mashka
 *
 */
public class TimePeriod {

	//QUESTION: Do we want to do decimalsfor minutes?
	//We can just make them hour.00-hour.59
	/**
	 * @param startTime and endTime double represent
	 *  the start and end times of a class in 24 hour format
	 *  Minutes are ...
	 *  @param weekDay short 
	 *  a number represents a day of the week
	 *   1 - Mon, 2 - Tue ... 7 - Sun
	 */
	double startTime;
	double endTime;
	short weekDay;
	
	TimePeriod(double startTime,	double endTime,	short weekDay)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekDay = weekDay;
	}
}
