package org.uca.dss.state.tests;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.uca.dss.state.common.Team;
import org.uca.dss.state.game.Match;
import org.uca.dss.state.game.PendingMatch;
import org.uca.dss.state.game.Tournament;

public class GameTests {
	Tournament torneo;
	Team spain;
	Team france;
	
	@Before
	public void setUp() throws Exception {
		LocalDate fecha = new LocalDate(2014, 12, 12);
		torneo = new Tournament();
		spain = new Team("España");
		france = new Team("Francia");
		torneo.addMatch( new PendingMatch(spain, france, fecha));
		torneo.addMatch( new PendingMatch(france, spain, fecha.plusDays(7)));
	}

	
	@Test
	public void test() {
		String expected;
		String expected1, expected2;
		
		expected = "España - Francia : 2014-12-12";
		assertEquals(torneo.getMatch(0).toString(), expected);
		
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);
		partido.addLocalPoints(3);
		partido.addVisitorPoints(1);
		
		expected = "España - Francia : 3 - 1, 0 minutes";
		assertEquals(torneo.getMatch(0).toString(), expected);
		expected2 = "Francia - España : 2014-12-19";
		assertEquals(torneo.getMatch(1).toString(), expected2);

		torneo.finishMatch(0);
		expected1 = "España - Francia : 3 - 1";
		assertEquals(torneo.getMatch(0).toString(), expected1);
		
		partido.addLocalPoints(4);
		
		assertEquals(torneo.getMatch(0).toString(), expected1);
		assertEquals(torneo.getMatch(1).toString(), expected2);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExceptionNotInit1() {
		Match partido = torneo.getMatch(0);		
		partido.addLocalPoints(3);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExceptionNotInit2() {
		Match partido = torneo.getMatch(0);		
		partido.addVisitorPoints(2);
	}
	
	@Test
	public void testExceptionInit() {
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);			
		partido.addVisitorPoints(2);
		partido.addLocalPoints(3);
		assertTrue(true);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExceptionFinish() {
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);			
		partido.addVisitorPoints(2);
		torneo.finishMatch(0);
		Match partidoFinish = torneo.getMatch(0);			
		partidoFinish.addLocalPoints(3);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testExceptionState() {
		torneo.startMatch(0);
		Match partido = torneo.getMatch(0);			
		partido.addVisitorPoints(2);
		torneo.finishMatch(0);		
		partido.addLocalPoints(3);
	}
	
	

}
