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
	TeamRankings currentRanking;
	TeamRankings finalRanking;
	
	String local, visitor;
	Tournament torneo;
	
	@Before
	public void setUp() throws Exception {
		currentRanking = new TeamRankings();
		finalRanking = new TeamRankings();
		LocalDate fecha = new LocalDate(2014, 12, 12);
		torneo = new Tournament();
		Team spain = new Team("España");
		Team france = new Team("Francia");
		// TODO: Change with new class Match
		Match partido = new PendingMatch(spain, france, fecha);
		// TODO: Relationship between rankings and partido		torneo.addMatch(partido);
		torneo.addMatch(partido);
		local = torneo.getMatch(0).getLocalName();
		visitor = torneo.getMatch(0).getVisitorName();
	}


	@Test
	public void testNotChanges() {
		// Init match
		torneo.startMatch(0);
		testFinalScore(0, 0);
		Match partido = torneo.getMatch(0);
		partido.addLocalPoints(3);
		testFinalScore(0, 0);
		testCurrentScore(3, 0);
	}
	
	
	private void testCurrentScore(int scoreLocal, int scoreVisitor) {
		assertEquals(currentRanking.getPoints(local), scoreLocal);
		assertEquals(currentRanking.getPoints(visitor), scoreVisitor);
	}
	
	private void testFinalScore(int scoreLocal, int scoreVisitor) {
		assertEquals(finalRanking.getPoints(local), scoreLocal);
		assertEquals(finalRanking.getPoints(visitor), scoreVisitor);
	}
	
	@Test
	public void testChangeAtFinish1() {
		testFinalScore(0, 0);
		testCurrentScore(0, 0);
		
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);
		partido.addLocalPoints(3);
		torneo.finishMatch(0);
		
		testFinalScore(3, 0);
		testCurrentScore(3, 0);
	}
	
	@Test
	public void testChangeAtFinish2() {
		testFinalScore(0, 0);
		
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);
		partido.addVisitorPoints(3);
		torneo.finishMatch(0);
		
		testFinalScore(0, 3);
	}
	
	@Test
	public void testChangeAtFinish3() {
		testFinalScore(0, 0);
		
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);
		partido.addVisitorPoints(3);
		partido.addLocalPoints(3);
		torneo.finishMatch(0);
		
		testFinalScore(1, 1);
	}
	
}