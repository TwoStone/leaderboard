package com.github.twostone.leaderboard.services;

import com.github.twostone.leaderboard.model.competition.RegistrationRepository;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;
import com.github.twostone.leaderboard.model.score.Score;
import com.github.twostone.leaderboard.model.score.ScoreManager;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("api/scores")
public class ScoreService {

  private ScoreManager scoreManager;
  private EventRepository eventRepository;
  private RegistrationRepository registrationRepository;

  @Inject
  ScoreService(
      ScoreManager scoreManager,
      EventRepository eventRepository,
      RegistrationRepository registrationRepository) {
    super();
    this.scoreManager = scoreManager;
    this.eventRepository = eventRepository;
    this.registrationRepository = registrationRepository;
  }

  /**
   * Adds a new score or updates an existing score.
   */
  @RequestMapping(path = "/", method = RequestMethod.POST)
  public Score addScore(@RequestBody Score score) {
//    Event event = this.eventRepository.findOne(score.getEvent().getId());
//    Competitor competitor = this.registrationRepository.findOne(score.getCompetitor().getId());
    return this.scoreManager.addScore(score);
  }

  @RequestMapping("/event/{eventId}")
  public Iterable<Score> getScoresForEvent(
      @PathVariable("eventId") long eventId) {
    Event event = this.eventRepository.findOne(eventId);
    return this.scoreManager.findScoreByEvent(event);
  }
}
