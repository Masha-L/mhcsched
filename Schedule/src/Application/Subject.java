package Application;

import java.util.ArrayList;

public class Subject {
	
	String name;

	//Зачем нам и имя и айди? можно ток имя?
	// как мы передаем информацию этому классу? Уже готовыми лекциями? Где мы эти лекции готовим? 
	//откуда в теории это все приходит?
	
	
	
	int id;
	
	ArrayList <TimePeriod> [] lectures;
	
	ArrayList <TimePeriod> labs;
	
	ArrayList <TimePeriod> createSection(TimePeriod[] weekSchedule){
		
		ArrayList <TimePeriod> newSection = new ArrayList<TimePeriod>(weekSchedule.length);
		
		for(int i = 0; i<weekSchedule.length; i++)
		{
			newSection.add(weekSchedule[i]);
		}
		return newSection;
	}
	
	Subject(String name)
	{
		this.name=name;
		
	}
	
}
