package com.github.twostone.leaderboard.model.event.weightlifting;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.github.twostone.leaderboard.model.event.Result;

@Entity
public class WeightliftingResult extends Result {

	private static final long serialVersionUID = 1L;
	
	private double firstTry;
	private double secondTry;
	private double thirdTry;
	
	@ManyToOne
	private WeightliftingEventType eventType;
	
	public WeightliftingResult() {
		super();
	}
	
	public WeightliftingResult(WeightliftingEventType eventType) {
		super();
		this.eventType = eventType;
	}

	public double getFirstTry() {
		return firstTry;
	}

	public void setFirstTry(double firstTry) {
		this.firstTry = firstTry;
	}

	public double getSecondTry() {
		return secondTry;
	}

	public void setSecondTry(double secondTry) {
		this.secondTry = secondTry;
	}

	public double getThirdTry() {
		return thirdTry;
	}

	public void setThirdTry(double thirdTry) {
		this.thirdTry = thirdTry;
	}
	
}
