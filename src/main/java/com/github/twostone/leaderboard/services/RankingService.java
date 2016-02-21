package com.github.twostone.leaderboard.services;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.competition.DivisionRespository;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;
import com.github.twostone.leaderboard.model.ranking.RankedCompetitionScore;
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
  private CompetitionRepository competitionRepository;

  @Inject
  RankingService(
      RankingManager rankingManager,
      CompetitionRepository competitionRepository,
      EventRepository eventRepository,
      DivisionRespository divisionRespository) {
    super();
    this.rankingManager = rankingManager;
    this.competitionRepository = competitionRepository;
    this.eventRepository = eventRepository;
    this.divisionRespository = divisionRespository;
  }

  /**
   * Return the ranked scores for the event and division.
   */
  @RequestMapping("/{eventId}/{divisionId}")
  public List<RankedEventScore> getEventScoreForDivision(
      @PathVariable("eventId") long eventId,
      @PathVariable("divisionId") long divisionId) {
    Event event = this.eventRepository.findOne(eventId);
    if (event == null) {
      throw new NotFoundException();
    }
    
    Division division = this.divisionRespository.findOne(divisionId);
    if (division == null) {
      throw new NotFoundException();
    }
    
    return this.rankingManager.getEventScore(event, division);
  }
  
  @RequestMapping("/all/{divisionId}")
  public List<RankedCompetitionScore> getCompetitionScore(
      @PathVariable("competitionId") long competitionId,
      @PathVariable("divisionId") long divisionId) {
    Competition competition = this.competitionRepository.findOne(competitionId);
    Division division = this.divisionRespository.findOne(divisionId);
    
    if (competition == null || division == null) {
      throw new NotFoundException();
    }
    return this.rankingManager.getCompetitionScore(competition, division);
  }
  
  
}
