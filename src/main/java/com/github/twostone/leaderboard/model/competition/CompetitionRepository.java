package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.event.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

  
  Competition findCompetitionByEvents(Event event);
}
