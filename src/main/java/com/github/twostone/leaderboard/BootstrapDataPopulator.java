package com.github.twostone.leaderboard;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.competition.NewEventRequest;
import com.github.twostone.leaderboard.model.event.EventType;
import com.github.twostone.leaderboard.services.CompetitionService;
import com.github.twostone.leaderboard.services.CompetitionService.CompetitorRegistrationRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
public class BootstrapDataPopulator implements InitializingBean {
  
  private Logger log = LoggerFactory.getLogger(BootstrapDataPopulator.class);

  private CompetitionService competitionService;
  
  @Inject
  BootstrapDataPopulator(CompetitionService competitionService) {
    super();
    this.competitionService = competitionService;
  }

  @Override
  @Transactional
  public synchronized void afterPropertiesSet() throws Exception {
    this.log.info("Bootstrapping data...");
    this.createDemoCompetition();
    this.log.info("... Bootstrapping complete");
  }
  
  private void createDemoCompetition() {
    Competition demoComp = this.competitionService.findById(1L);
    if (demoComp != null) {
      return;
    }
    
    this.log.info("... creating demo competition");    
    
    Competition competition = this.competitionService.create("DEMO Competition");
    Division eliteDivision = this.competitionService.addDivision(competition.getId(), "Elite");
    Division scaledDivision = this.competitionService.addDivision(competition.getId(), "Scaled");
    
    this.competitionService.registerCompetitor(competition.getId(), 
        this.createRegisterRequest(eliteDivision, "Elite Team 1"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(eliteDivision, "Elite Team 2"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(eliteDivision, "Elite Team 3"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(eliteDivision, "Elite Team 4"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(eliteDivision, "Elite Team 5"));
    
    this.competitionService.registerCompetitor(competition.getId(), 
        this.createRegisterRequest(scaledDivision, "Scaled Team 1"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(scaledDivision, "Scaled Team 2"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(scaledDivision, "Scaled Team 3"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(scaledDivision, "Scaled Team 4"));
    this.competitionService.registerCompetitor(competition.getId(),
        this.createRegisterRequest(scaledDivision, "Scaled Team 5"));
    
    this.competitionService.addEvent(competition.getId(), 
        this.createEventRequest("Event 1", EventType.FOR_TIME));
    this.competitionService.addEvent(competition.getId(), 
        this.createEventRequest("Event 2", EventType.FOR_POINTS));
   
  }
  
  private NewEventRequest createEventRequest(String name, EventType type) {
    NewEventRequest newEvent = new NewEventRequest();
    newEvent.setName(name);
    newEvent.setType(type);
    return newEvent;
  }

  private CompetitorRegistrationRequest createRegisterRequest(Division division, String name) {
    CompetitorRegistrationRequest registrationRequest = new CompetitorRegistrationRequest();
    registrationRequest.setDivisionId(division.getId());
    registrationRequest.setName(name);
    return registrationRequest;
  }
}