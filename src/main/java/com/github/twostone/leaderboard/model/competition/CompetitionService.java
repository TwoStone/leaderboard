package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.event.Event;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("api/competitions/")
public class CompetitionService {
  
  private CompetitionManager competitionManager;

  @Inject
  CompetitionService(CompetitionManager competitionManager) {
    super();
    this.competitionManager = competitionManager;
  }
  
  @RequestMapping(
      path = "/create",
      method = RequestMethod.POST)
  public Competition create(@RequestParam("name") String name) {
    return this.competitionManager.createCompetition(name);
  }
  
  /**
   * Adds a new division for the competition.
   */
  @RequestMapping(
      path = "/{competitionId}/divisions.add",
      method = RequestMethod.POST)
  public Division addDivision(
      @PathVariable("competitionId") Long competitionId, 
      @RequestParam("divisionName") String divisionName) {
    Competition competition = this.competitionManager.findOne(competitionId);
    Division division = this.competitionManager.createDivision(competition, divisionName);
    
    return division;
  }
  
  /**
   * Registers a new competition for the event.
   */
  @RequestMapping(
      path = "/{competitionId}/register",
      method = RequestMethod.POST)
  public void register(
      @PathVariable("competitionId") Long competitionId,
      @RequestParam("divisionId") final Long divisionId,
      @RequestParam("name") String name) {
    
    Competition competition = this.competitionManager.findOne(competitionId);
    Division division = competition.getDivisions().stream().filter(
        (div) -> div.getId().equals(divisionId)).findFirst().get();
    this.competitionManager.register(competition, division, name);
  }
  
  @RequestMapping(
      path = "/{competitionId}/events.add")
  public Event addEvent(
      @PathVariable("competitionId") Long competitionId,
      @RequestParam("name") String eventName) {
    Competition competition = this.competitionManager.findOne(competitionId);
    return this.competitionManager.addEvent(competition, eventName);
  }
}
