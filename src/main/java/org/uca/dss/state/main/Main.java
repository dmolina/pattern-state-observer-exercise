package org.uca.dss.state.main;

import org.joda.time.LocalDate;
import org.uca.dss.state.common.Team;
import org.uca.dss.state.game.Match;
import org.uca.dss.state.game.PendingMatch;
import org.uca.dss.state.game.Tournament;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tournament torneo = new Tournament();
		Team spain = new Team("España");
		Team france = new Team("Francia");
		LocalDate fecha = new LocalDate().plusDays(1);
		
		torneo.addMatch( new PendingMatch(spain, france, fecha));
		torneo.addMatch( new PendingMatch(france, spain, fecha.plusDays(7)));
		
		System.out.println(torneo.getMatch(0));
		System.out.println(torneo.getMatch(1));
		
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);
		partido.addLocalPoints(3);
		partido.addVisitorPoints(1);
		
		System.out.println(torneo.getMatch(0));
		System.out.println(torneo.getMatch(1));
		
		System.out.println("Match is finished");
		torneo.finishMatch(0);
		
		partido.addLocalPoints(4);
		System.out.println(torneo.getMatch(0));
		System.out.println(torneo.getMatch(1));
		
	}

}
