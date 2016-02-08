package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.event.Event;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import javax.inject.Inject;

@RestController
@RequestMapping("api/competitions")
public class CompetitionService {
  
  public static class CompetitorRegistrationRequest {
    private long divisionId;
    private String name;
    
    public CompetitorRegistrationRequest() {
    }

    public long getDivisionId() {
      return divisionId;
    }

    public void setDivisionId(long division) {
      this.divisionId = division;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }

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
  
  @RequestMapping(path = "", method = RequestMethod.GET)
  public Iterable<Competition> findAll() {
    return this.competitionManager.findAll();
  }
  
  @RequestMapping(path = "/{id}")
  public Competition findById(@PathVariable("id") long id) {
    return this.competitionManager.findOne(id);
  }
  
  @RequestMapping(path = "/{id}/divisions")
  public Iterable<Division> getDivisions(@PathVariable("id") long id) {
    Competition competition = this.findById(id);
    return competition.getDivisions();
  }
  
  /**
   * Adds a new division for the competition.
   */
  @RequestMapping(
      path = "/{competitionId}/divisions.add",
      method = RequestMethod.POST)
  public Division addDivision(
      @PathVariable("competitionId") Long competitionId, 
      @RequestParam("name") String divisionName) {
    Competition competition = this.competitionManager.findOne(competitionId);
    Division division = this.competitionManager.createDivision(competition, divisionName);
    
    return division;
  }
  
  @RequestMapping(
      path = "/{competitionId}/competitors.add",
      method = RequestMethod.POST)
  public Competitor registerCompetitor(
      @PathVariable("competitionId") Long competitionId, 
      @RequestBody(required = true) CompetitorRegistrationRequest request) {
    Competition competition = this.competitionManager.findOne(competitionId);
    Division division = competition.getDivisions()
        .stream()
        .filter(cur -> cur.getId().equals(request.getDivisionId()))
        .findFirst()
        .orElseThrow(() -> {
          return new NoSuchElementException(MessageFormat.format("division id:{0}", request.getDivisionId()));
        });
    
    return this.competitionManager.register(competition, division, request.getName());
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
