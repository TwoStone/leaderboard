package com.github.twostone.leaderboard.model.athlete;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.github.twostone.leaderboard.model.AbstractEntity;

/**
 * Entity implementation class for Entity: Athlete
 */
@Entity
public class Athlete extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String lastname;   
	private LocalDate birthday;

	protected Athlete() {
		super();
	} 
	
	public Athlete(String firstname, String lastname, LocalDate birthday) {
		this();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}   
	public String getLastname() {
		return this.lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}   
	public LocalDate getBirthday() {
		return this.birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", this.lastname, this.firstname);
	}
   
}
