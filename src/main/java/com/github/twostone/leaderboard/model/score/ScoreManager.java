package com.github.twostone.leaderboard.model.score;

import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.event.Event;

import com.google.common.collect.Iterables;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ScoreManager {
  
  private ScoreRepository scoreRepository;

  @Inject
  ScoreManager(ScoreRepository scoreRepository) {
    super();
    this.scoreRepository = scoreRepository;
  }
  
  public Score findOne(long id) {
    return this.scoreRepository.findOne(id);
  }
  
  public Iterable<Score> findAll() {
    return this.scoreRepository.findAll();
  }
  
  public Iterable<Score> findScoreByEvent(Event event) {
    return this.scoreRepository.findByEvent(event);
  }
  
  public Iterable<Score> findScoreByCompetitor(Competitor competitor) {
    return this.scoreRepository.findByCompetitor(competitor);
  }
  
  public Score addScore(Event event, Competitor competitor, long value) {
    Iterable<Score> oldScore = this.scoreRepository.findByEventAndCompetitor(event, competitor);
    Score score;
    if (!Iterables.isEmpty(oldScore)) {
      score = oldScore.iterator().next();
      score.setValue(value);
    } else {
      score = new Score(event, competitor, value);
    }
    
    return this.scoreRepository.save(score);
  }
  
  public void deleteScore(Score score) {
    this.scoreRepository.delete(score);
  }
  
}
