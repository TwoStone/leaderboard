package com.github.twostone.leaderboard.model.score;

import static java.util.stream.Collectors.toList;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.competition.RegistrationRepository;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

@Component
public class ScoreManager {

  private ScoreRepository scoreRepository;
  private CompetitionRepository competitionRepository;
  private EventRepository eventRepository;
  private RegistrationRepository competitorRepository; 

  /**
   * Creates a ScoreManager.
   */
  @Inject
  public ScoreManager(
      ScoreRepository scoreRepository,
      CompetitionRepository competitionRepository,
      EventRepository eventRepository,
      RegistrationRepository competitorRepository
      ) {
    super();
    this.scoreRepository = scoreRepository;
    this.competitionRepository = competitionRepository;
    this.eventRepository = eventRepository;
    this.competitorRepository = competitorRepository;
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


    List<Score> scores = this.scoreRepository.findByEventAndCompetitorDivision(event, division);
    scores.sort(ScoreComparatorFactory.createComparator(event.getRecipe()));
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
      return new Score(event, competitor, false);
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
  public Score addScore(Score score) {
//    Iterable<Score> oldScore = this.scoreRepository.findByEventAndCompetitor(event, competitor);
//
//    Score score;
//    if (!Iterables.isEmpty(oldScore)) {
//      score = oldScore.iterator().next();
//      score.setParts(parts);
//      score.setScaled(scaled);
//    } else {
//      score = new Score(event, competitor, scaled);
//    }
//
    return this.scoreRepository.save(score);
  }

  public void deleteScore(Score score) {
    this.scoreRepository.delete(score);
  }

  public Score findScoreByEventAndCompetitor(Long competitionId, Long eventId, Long competitorId) {
    Competition competition = this.competitionRepository.findOne(competitionId);
    Event event = this.eventRepository.findOne(eventId);
    Competitor competitor = this.competitorRepository.getOne(competitorId);
    checkEventInCompetition(competition, event);
    checkCompetitorInCompetition(competition, competitor);
      
    Iterable<Score> scores = this.scoreRepository.findByEventAndCompetitor(event, competitor);
    if (Iterables.isEmpty(scores)) {
      return new Score(event, competitor, false);
    }
    
    return Iterables.getOnlyElement(scores);
  }
  
  private void checkCompetitorInCompetition(Competition competition, Competitor competitor) {
    if (!competition.getCompetitors().contains(competitor)) {
      throw new IllegalArgumentException("The competitor %s is not registered for the competition %s!");
    }
  }

  private void checkEventInCompetition(Competition competition, Event event) {
    if (!competition.getEvents().contains(event)) {
      throw new IllegalArgumentException(String.format("The event %s is not part of the competition %s!", event, competition));
    }
  }

}
