package com.github.twostone.leaderboard.services;

import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;
import com.github.twostone.leaderboard.model.ranking.EventScoreBoard;
import com.github.twostone.leaderboard.model.ranking.RankingManager;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("api/ranking/{competitionId}")
public class RankingService {

  private RankingManager rankingManager;
  private EventRepository eventRepository;

  @Inject
  RankingService(RankingManager rankingManager, EventRepository eventRepository) {
    super();
    this.rankingManager = rankingManager;
    this.eventRepository = eventRepository;
  }
  
  @RequestMapping("event/{eventId}")
  public EventScoreBoard getEventScoreBoard(@PathVariable("eventId") long eventId) {
    Event event = this.eventRepository.findOne(eventId);
    return this.rankingManager.getScoreEventScoreBoard(event);
  }
  
  
}
