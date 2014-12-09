package org.uca.dss.state.common;

public class Team {
	String name;
	
	public Team(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {
		return name;
	}
}
