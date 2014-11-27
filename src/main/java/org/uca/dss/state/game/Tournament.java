package org.uca.dss.state.game;

import java.util.ArrayList;

public class Tournament {
	private ArrayList<Match> games;
	
	public Tournament() {
		games = new ArrayList<Match>();
	}
	
	public void addMatch(Match partido) {
		games.add(partido);
	}
	
	public Match getMatch(int pos) {
		return games.get(pos);
	}
	
	public void startMatch(int pos) {
		Match match = games.get(pos);
		
		if (!(match instanceof PendingMatch)) {
			throw new IllegalStateException();
		}
		else {
			PlayingMatch newMatch;
			newMatch = new PlayingMatch(match);
			games.set(pos, newMatch);			
		}
		
	}

	public void finishMatch(int pos) {
		Match game = games.get(pos);
		
		if (!(game instanceof PlayingMatch)) {
			throw new IllegalStateException();
		}
		else {
			FinishedMatch newMatch;
			newMatch = new FinishedMatch(game);
			games.set(pos, newMatch);
		}
		
	}
	
}
