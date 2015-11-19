package com.github.twostone.leaderboard.model.event;

import java.util.Collection;

import org.springframework.data.repository.Repository;

public interface EventRepository extends Repository<Event, String> {
	
	Event findOne(String id);
	
	Collection<Event> findByCompetitionId(String competitionId);
}
