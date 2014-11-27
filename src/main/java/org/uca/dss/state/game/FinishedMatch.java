package org.uca.dss.state.game;

public class FinishedMatch extends Match {
    public FinishedMatch(Match partido) {
		super(partido);
	}
    
	@Override
	public String toString() {
		return String.format("%s - %s : %d - %d", 
				local, visitor, localPoints, visitorPoints);
	}
}
