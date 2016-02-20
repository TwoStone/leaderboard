package com.github.twostone.leaderboard.model.score;

import static java.util.stream.Collectors.toList;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

@Component
public class ScoreManager {
  
  private ScoreRepository scoreRepository;
  private CompetitionRepository competitionRepository;

  /**
   * Creates a ScoreManager.
   */
  @Inject
  public ScoreManager(ScoreRepository scoreRepository, 
      CompetitionRepository competitionRepository) {
    super();
    this.scoreRepository = scoreRepository;
    this.competitionRepository = competitionRepository;
  }
  
  public Score findOne(long id) {
    return this.scoreRepository.findOne(id);
  }
  
  public Iterable<Score> findAll() {
    return this.scoreRepository.findAll();
  }
  
  /**
   * Returns the scores for the event.
   * Includes a score for every competitor in the competition.
   */
  public Iterable<Score> findScoreByEvent(Event event) {
    Competition competition = this.competitionRepository.findCompetitionByEvents(event);
    Iterable<Score> scores = this.scoreRepository.findByEvent(event);
    return complete(event, scores, competition.getCompetitors());
  }
  
  public Iterable<Score> findScoreByEventAndDivision(Event event, Division division) {
    Competition competition = this.competitionRepository.findCompetitionByEvents(event);
    Iterable<Score> scores = this.scoreRepository.findByEventAndCompetitorDivision(event, division);
    return complete(event, scores, competition.getCompetitors().stream().filter(competitor -> {
      return competitor.getDivision().equals(division);
    }).collect(toList()));
  }

  private Iterable<Score> complete(Event event, Iterable<Score> scores, Iterable<Competitor> competitors) {
    List<Score> result = Lists.newArrayList(scores);
    List<Competitor> competitorsWithScore = result.stream().map(
        s -> s.getCompetitor()).collect(Collectors.toList());
    List<Competitor> competitorsWithoutScore = Lists.newArrayList(competitors);
    competitorsWithoutScore.removeAll(competitorsWithScore);
    result.addAll(competitorsWithoutScore.stream().map(
        competitor -> {
          return new Score(event, competitor, -1);
        }).collect(toList()));
    
    return result;
  }
  
  public Iterable<Score> findScoreByCompetitor(Competitor competitor) {
    return this.scoreRepository.findByCompetitor(competitor);
  }
  
  /**
   * Adds a new score or updates an existing score.
   */
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
