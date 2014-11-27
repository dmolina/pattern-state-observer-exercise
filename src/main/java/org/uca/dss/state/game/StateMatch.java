package org.uca.dss.state.game;

import org.joda.time.LocalTime;
import org.joda.time.Period;

public abstract class StateMatch {
	protected Match match;
	
	public StateMatch(Match match) {
		this.match = match;
	}
	public void addLocalPoints(int points) {
		throw new IllegalStateException();
	}
	public void addVisitorPoints(int points) {
		throw new IllegalStateException();
	}
	public abstract String toString();
	}
