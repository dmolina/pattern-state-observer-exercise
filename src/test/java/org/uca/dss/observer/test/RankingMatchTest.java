package org.uca.dss.observer.test;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.uca.dss.observer.reports.TeamRankings;
import org.uca.dss.state.common.Team;
import org.uca.dss.state.game.Match;
import org.uca.dss.state.game.PendingMatch;
import org.uca.dss.state.game.Tournament;

public class RankingMatchTest {
	TeamRankings ranking;
	String local, visitor;
	Tournament torneo;
	
	@Before
	public void setUp() throws Exception {
		ranking = new TeamRankings();
		LocalDate fecha = new LocalDate(2014, 12, 12);
		torneo = new Tournament();
		Team spain = new Team("España");
		Team france = new Team("Francia");
		// TODO: Change with new class Match
		Match partido = new PendingMatch(spain, france, fecha);
		// TODO: Relationship between ranking and partido		torneo.addMatch(partido);
		torneo.addMatch(partido);
		local = torneo.getMatch(0).getLocalName();
		visitor = torneo.getMatch(0).getVisitorName();
	}
	
	private void assertNotchanges() {
		assertEquals(ranking.getPoints(local), 0);
		assertEquals(ranking.getPoints(visitor), 0);
	}

	@Test
	public void testNotChanges() {
		
		assertNotchanges();
		
		// Init match
		torneo.startMatch(0);
		assertNotchanges();
		Match partido = torneo.getMatch(0);
		partido.addLocalPoints(3);
		assertNotchanges();
	}
	
	@Test
	public void testChangeAtFinish() {
		assertNotchanges();
		
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);
		partido.addLocalPoints(3);
		torneo.finishMatch(0);
		
		assertEquals(ranking.getPoints(local), 3);
		assertEquals(ranking.getPoints(visitor), 0);
	}

}
