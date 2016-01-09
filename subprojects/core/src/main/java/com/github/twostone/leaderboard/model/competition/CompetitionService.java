package com.github.twostone.leaderboard.model.competition;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/competitions/")
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
  
  
  @RequestMapping(
      path = "/{competitionId}/addDivision",
      method = RequestMethod.POST)
  public Division addDivision(
      @PathVariable("competitionId") Long competitionId, 
      @RequestParam("divisionName") String divisionName) {
    Competition competition = this.competitionManager.findOne(competitionId);
    Division division = this.competitionManager.createDivision(competition, divisionName);
    
    return division;
  }
  
  @RequestMapping(
      path = "/{competitionId}/register",
      method = RequestMethod.POST)
  public void register(
      @PathVariable("competitionId") Long competitionId,
      @RequestParam("divisionId") final Long divisionId,
      @RequestParam("name") String name) {
    
    Competition competition = this.competitionManager.findOne(competitionId);
    Division division = competition.getDivisions().stream().filter((d) -> d.getId().equals(divisionId)).findFirst().get();
    this.competitionManager.register(competition, division, name);
  }
}
