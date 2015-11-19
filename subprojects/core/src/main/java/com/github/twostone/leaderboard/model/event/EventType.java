package com.github.twostone.leaderboard.model.event;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Participation;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EventType extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	
	public abstract Comparator<Result> getComparator();
	
	public abstract Result createEmptyResult(Participation participant);
}
