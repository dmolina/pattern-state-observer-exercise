package org.uca.dss.observer.reports;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TeamRankingsTest {
	TeamRankings teamRanking;

	@Before
	public void init() {
		teamRanking = new TeamRankings();
	}
	
	@Test
	public void testEmpty() {
		String teams [] = {"Spain", "France"};
		
		for (String team : teams) {
			assertTrue(teamRanking.getPoints(team)==0);
			assertTrue(teamRanking.getRanking(team)==0);
		}
	}
	
	/**
	 * Test that with different results the points are right
	 */
	@Test
	public void testFirstWin() {
		String team1 = "Spain";
		String team2 = "France";
		
		for (int i=0; i < 5; i+=1) {
			for (int j = 0; j < i; j++) {
				TeamRankings rankings = new TeamRankings();
				rankings.addResult(team1, i, team2, j);
				assertTrue(rankings.getPoints(team1) == 3);
				assertTrue(rankings.getPoints(team2)== 0);
			}
		}			
	}
	
	@Test
	public void testSecondWin() {
		String team1 = "Spain";
		String team2 = "France";
		
		for (int i=0; i < 5-1; i+=1) {
			for (int j = i+1; j < 5; j++) {
				TeamRankings rankings = new TeamRankings();
				rankings.addResult(team1, i, team2, j);
				assertTrue(rankings.getPoints(team1)==0);
				assertTrue(rankings.getPoints(team2) == 3);
			}
		}			
	}
	
	@Test
	public void testEquals() {
		String team1 = "Spain";
		String team2 = "France";
		
		for (int i=0; i < 5; i+=1) {
			TeamRankings rankings = new TeamRankings();
			rankings.addResult(team1, i, team2, i);
			assertTrue(rankings.getPoints(team1)==1);
			assertTrue(rankings.getPoints(team2)==1);
		}			
	}
	
	@Test
	public void testRanking() {
		String team1 = "Spain";
		String team2 = "France";
		String team3 = "UK";
		
		teamRanking.addResult(team1, 1, team2, 0);
		
		assertTrue(teamRanking.getRanking(team1)==1);
		assertTrue(teamRanking.getRanking(team2)==2);
		
		teamRanking.addResult(team1, 0, team2, 0);
		
		assertTrue(teamRanking.getRanking(team1)==1);
		assertTrue(teamRanking.getRanking(team2)==2);
		
		teamRanking.addResult(team1, 1, team2, 2);
		teamRanking.addResult(team1, 0, team2, 1);
		
		assertTrue(teamRanking.getRanking(team1)==2);
		assertTrue(teamRanking.getRanking(team2)==1);

	}

}
