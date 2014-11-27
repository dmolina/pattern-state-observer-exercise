package org.uca.dss.state.game;


import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.uca.dss.state.common.Team;

public class PendingMatch extends Match {
	
	public PendingMatch(Team local, Team visitor, LocalDate fecha) {
		super();
		this.local = local;
		this.visitor = visitor;
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return String.format("%s - %s : %s", 
				local, visitor, fecha);
	}
}
