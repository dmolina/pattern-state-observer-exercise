package org.uca.dss.observer.reports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;

/**
 * This class contains a list of
 * the rankings for each Team
 * @author daniel
 *
 */
public class TeamRankings {
	Map<String,Integer> score;
	List<String> sortedTeams;
	// sorted indicates if sortedTeams is usable (it is a cache)
	boolean sorted;
	
	public TeamRankings() {
		score = new HashMap<String, Integer>();
		sortedTeams = new ArrayList<String>();
		sorted = false;
	}
	
	/**
	 * Return the points of each team (0 for an unknown team)
	 * @param team to obtain information
	 * @return the number of points
	 */
	public int getPoints(String team) {
		if (score.containsKey(team)) {
			return score.get(team).intValue();
		}
		else {
			return 0;
		}
	}
	
	/**
	 * Return the ranking of each team (0 for unknown team)
	 * @param team to obtain information
	 * @return the number of points
	 */
	public int getRanking(String team) {
		if (!score.containsKey(team)) {
			return 0;
		}
		
		if (!sorted) {
			sortedTeams = calculateRankings();
			sorted = true;
		}
		
		return sortedTeams.indexOf(team)+1; 
	}
	
	class sortByPoints implements Comparator<String> {

		public int compare(String team1, String team2) {
			int points1 = score.get(team1).intValue();
			int points2 = score.get(team2).intValue();
			
			return (points2 - points1);
		}
		
	}
	
	/**
	 * Calculate a list of Teams using the points as criterion
	 * @return a sorted list of teams
	 */
	private List<String> calculateRankings() {
		List<String> teams;
		
		teams = new ArrayList<String>(score.keySet());
		
		Collections.sort(teams, new sortByPoints());
		
		return teams;
	}

	/**
	 * Add a new result in the table
	 * @param team1 name of first team
	 * @param goals1 goals obtained by team 1
	 * @param team2 name of second team
	 * @param goals2 goals obtained by team 2
	 */
	public void addResult(String team1, int goals1, String team2, int goals2) {
		Preconditions.checkArgument(goals1 >= 0);
		Preconditions.checkArgument(goals2 >= 0);
		
		if (goals1 > goals2) {
			addScore(team1, 3);
			addScore(team2, 0);
		}
		else if (goals1 == goals2) {
			addScore(team1, 1);
			addScore(team2, 1);
		}
		else {
			addScore(team2, 3);
			addScore(team1, 0);
		}
		
		sorted = false;
	}
	
	/**
	 * Increm the points to team
	 * @param team
	 * @param score to increases
	 */
	private void addScore(String team, int newScore) {
		if (!score.containsKey(team)) {
			score.put(team,  newScore);
		}
		else {
			score.put(team, score.get(team).intValue()+newScore);
		}
	}
	
}
