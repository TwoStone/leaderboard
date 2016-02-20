package com.github.twostone.leaderboard.services;

import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.competition.DivisionRespository;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;
import com.github.twostone.leaderboard.model.ranking.EventScoreBoard;
import com.github.twostone.leaderboard.model.ranking.RankedEventScore;
import com.github.twostone.leaderboard.model.ranking.RankingManager;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.inject.Inject;

@RestController
@RequestMapping("api/ranking/{competitionId}")
public class RankingService {

  private RankingManager rankingManager;
  private EventRepository eventRepository;
  private DivisionRespository divisionRespository;

  @Inject
  RankingService(
      RankingManager rankingManager, 
      EventRepository eventRepository,
      DivisionRespository divisionRespository) {
    super();
    this.rankingManager = rankingManager;
    this.eventRepository = eventRepository;
    this.divisionRespository = divisionRespository;
  }
  
  @RequestMapping("event/{eventId}")
  public EventScoreBoard getEventScoreBoard(
      @PathVariable("eventId") long eventId,
      @PathVariable("divisionId") long divisionId) {
    Event event = this.eventRepository.findOne(eventId);
    return this.rankingManager.getEventScoreBoard(event);
  }
  
  @RequestMapping("/{eventId}/{divisionId}")
  public List<RankedEventScore> getEventScoreForDivision(
      @PathVariable("eventId") long eventId,
      @PathVariable("divisionId") long divisionId) {
    Event event = this.eventRepository.findOne(eventId);
    Division division = this.divisionRespository.findOne(divisionId);
    return this.rankingManager.getEventScore(event, division);
  }
  
  
  
}
