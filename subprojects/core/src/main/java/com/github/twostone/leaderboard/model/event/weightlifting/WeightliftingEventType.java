package com.github.twostone.leaderboard.model.event.weightlifting;

import java.util.Comparator;

import javax.persistence.Entity;

import com.github.twostone.leaderboard.model.competition.Participation;
import com.github.twostone.leaderboard.model.event.EventType;
import com.github.twostone.leaderboard.model.event.Result;

@Entity
public class WeightliftingEventType extends EventType {

	private static final long serialVersionUID = 1L;

	@Override
	public Comparator<Result> getComparator() {
		return null;
	}

	@Override
	public WeightliftingResult createEmptyResult(Participation participant) {
		return new WeightliftingResult(this);
	}
}
