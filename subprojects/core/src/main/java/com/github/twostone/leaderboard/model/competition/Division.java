package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.AbstractEntity;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Division
 */
@Entity
public class Division extends AbstractEntity implements Serializable {

	
	private String name;
	private static final long serialVersionUID = 1L;

	protected Division() {
		super();
	}   
	
	public Division(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
   
}
