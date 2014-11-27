package org.uca.dss.state.game;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.uca.dss.state.common.Team;

public abstract class Match {
	protected Team local;
	protected Team visitor;
	protected LocalDate fecha;
	protected int localPoints;
	protected int visitorPoints;
	protected LocalTime starting_time;
	
	public Match() {		
	}
	public Match(Match game) {
		this.local = game.local;
		this.visitor = game.visitor;
		this.fecha = game.fecha;
		this.starting_time = game.starting_time;
		this.localPoints = game.localPoints;
		this.visitorPoints = game.visitorPoints;
	}
	
	public void addLocalPoints(int points) {
		throw new IllegalStateException();
	}
	public void addVisitorPoints(int points) {
		throw new IllegalStateException();
	}
	public abstract String toString();
}
