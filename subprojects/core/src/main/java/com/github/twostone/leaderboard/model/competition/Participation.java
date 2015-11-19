package com.github.twostone.leaderboard.model.competition;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.athlete.Athlete;

@Entity
public class Participation extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Athlete participant;
	
	@ManyToOne
	private Competition competition;
	
	@ManyToOne
	private Division division;
	
	protected Participation() {
		super();
	}
	
	Participation(Athlete athlete, Division division, Competition competition) {
		super();
		this.participant = athlete;
		this.division = division;
		this.competition = competition;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Participation) {
			Participation other = (Participation) obj;
			return this.participant.equals(other.participant)
					&& this.competition.equals(other.competition);
		}
		
		return false;
	}

}
