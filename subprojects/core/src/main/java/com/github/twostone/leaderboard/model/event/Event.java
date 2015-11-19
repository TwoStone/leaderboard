package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competition;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 */
@Entity
public class Event extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	
	@ManyToOne
	private EventType type;
	
	@OneToMany
	private Collection<Result> results; 

	@ManyToOne
	private Competition competition;
	
	public Event() {
		super();
		this.results = new ArrayList<>();
	}   

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Collection<Result> getResults() {
		return results;
	}
   
}
