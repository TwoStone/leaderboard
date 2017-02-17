package com.github.twostone.leaderboard.model.heat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.twostone.leaderboard.model.event.Event;

@Repository
public interface HeatRepository extends CrudRepository<HeatPlan, Long> {

  HeatPlan findByEvent(Event event);
}
