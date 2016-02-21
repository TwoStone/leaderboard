package com.github.twostone.leaderboard.model.score;

import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {
  
  Iterable<Score> findByEvent(Event event);
  
  Iterable<Score> findByCompetitor(Competitor competitor);
  
  Iterable<Score> findByEventAndCompetitor(Event event, Competitor competitor);
  
  Iterable<Score> findByEventAndCompetitorDivision(Event event, Division division);
  
  List<Score> findByEventAndCompetitorDivision(Event event, Division division, Sort sort);
}
