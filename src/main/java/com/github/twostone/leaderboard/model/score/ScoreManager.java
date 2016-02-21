package com.github.twostone.leaderboard.model.score;

import static java.util.stream.Collectors.toList;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

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
   * Returns all scores for the event and division.
   * There is an entry for every competitor in the competition. 
   */
  public List<Score> findScoreByEventAndDivision(Event event, Division division) {
    List<Competitor> competitors = Lists.newArrayList(
        this.competitionRepository.findCompetitionByEvents(event)
          .getCompetitors()).stream().filter(competitor -> {
            return competitor.getDivision().equals(division);
          }).collect(toList());
    List<Score> scores = this.scoreRepository.findByEventAndCompetitorDivision(event, division, 
        new Sort(event.getType().getOrdering().getDirection(), "score"));
    return this.createUnsetScores(event, competitors, scores);
  }
  
  /**
   * Returns all scores for the event.
   * There is an entry for every competitor in the competition.
   */
  public Collection<Score> findScoreByEvent(Event event) {
    Iterable<Score> scores = this.scoreRepository.findByEvent(event);
    Competition competition = this.competitionRepository.findCompetitionByEvents(event);
    return this.createUnsetScores(event, competition.getCompetitors(), scores);
  }
  
  private List<Score> createUnsetScores(Event event, Collection<Competitor> competitors,
      Iterable<Score> scores) {
    competitors.removeAll(
        Lists.newArrayList(scores)
          .stream()
          .map(Score::getCompetitor)
          .collect(toList()));
    
    List<Score> unsetScores = competitors.stream().map(competitor -> {
      return new Score(event, competitor, null);
    })
        .collect(toList());
    
    List<Score> result = Lists.newArrayList(scores);
    result.addAll(unsetScores);
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
      score.setScore(value);
    } else {
      score = new Score(event, competitor, value);
    }
    
    return this.scoreRepository.save(score);
  }
  
  public void deleteScore(Score score) {
    this.scoreRepository.delete(score);
  }
  
}
