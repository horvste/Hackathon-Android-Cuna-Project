package com.hackathon.cuna.position;

import java.util.ArrayList;
import java.util.List;

public class Position {
	private String name;
	private String description;
	private List<Requirement> reqs = new ArrayList<Requirement>();

	public Position(String name, String description, List<Requirement> reqs) {
		this.name = name;
		this.description = description;
		this.reqs = reqs;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public List<Requirement> getRequirements(){
		return reqs;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
}
