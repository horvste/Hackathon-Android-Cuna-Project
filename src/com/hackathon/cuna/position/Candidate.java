package com.hackathon.cuna.position;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Candidate {
	private Map<String, String> attributeMap;
	private Position appliedPosition;

	public Candidate(Map<String, String> attributeMap, Position appliedPosition) {
		super();
		this.attributeMap = attributeMap;
		this.appliedPosition = appliedPosition;
	}

	public String getName() {
		return attributeMap.get("name");
	}

	public String getGraduationDate() {
		return attributeMap.get("gradate");
	}

	public String getSchool() {
		return attributeMap.get("school");
	}
	
	public String getPhotoLocation() { 
		return attributeMap.get("photolocation");
	}
	
	public Position getAppliedPosition() {
		return this.appliedPosition;
	}

	public double getScore() {
		double total = 0;
		List<Requirement> reqs = appliedPosition.getRequirements();

		for (Requirement req : reqs) {
			double score = Double.valueOf(attributeMap.get(req.description));
			total = total + (score * req.weight);
		}

		return total / reqs.size();
	}
	
	public Map<String, String> getAttributes(){
		return this.attributeMap;
	}

	public void addAttributes(Map<String, String> newAttrs) {
		this.attributeMap.putAll(newAttrs);
	}

	public void addAttributes(String key, String value) {
		this.attributeMap.put(key, value);
	}

	public static List<Candidate> sortByRating(List<Candidate> candidates) {
		Comparator<Candidate> c = new Comparator<Candidate>() {

			@Override
			public int compare(Candidate arg0, Candidate arg1) {
				if (arg0.getScore() == arg1.getScore())
					return 0;
				if (arg0.getScore() < arg1.getScore())
					return -1;
				return 1;
			}

		};

		Collections.sort(candidates, c);
		
		return candidates;
	}
	
	@Override
	public String toString(){
		return this.getName();
	}
}
