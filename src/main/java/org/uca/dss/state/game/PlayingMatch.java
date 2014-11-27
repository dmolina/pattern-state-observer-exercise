package org.uca.dss.state.game;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;

public class PlayingMatch extends Match {

	public PlayingMatch(Match game) {
		super(game);
		this.starting_time = new LocalTime();
	}
	
	@Override
	public void addLocalPoints(int points) {
		localPoints += points;
	}
	
	@Override
	public void addVisitorPoints(int points) {
		visitorPoints += points;
	}
	
	@Override
	public String toString() {
		LocalTime actual = new LocalTime();
		Period time = new Period(starting_time, actual);
		return String.format("%s - %s : %d - %d, %d minutes", 
				local, visitor, localPoints, visitorPoints, 
				time.getHours());
	}

}
