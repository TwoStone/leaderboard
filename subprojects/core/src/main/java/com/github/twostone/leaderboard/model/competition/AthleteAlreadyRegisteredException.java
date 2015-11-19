package com.github.twostone.leaderboard.model.competition;

import java.text.MessageFormat;

import com.github.twostone.leaderboard.model.athlete.Athlete;

public class AthleteAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Athlete athlete;
	private Competition competition;

	public AthleteAlreadyRegisteredException(Athlete athlete, Competition competition) {
		super(MessageFormat.format("Athlete {} is already registered for the competition {}", athlete, competition));
		this.athlete = athlete;
		this.competition = competition;
	}

	public Athlete getAthlete() {
		return athlete;
	}

	public Competition getCompetition() {
		return competition;
	}	
}
