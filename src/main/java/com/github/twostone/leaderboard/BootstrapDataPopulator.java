package com.github.twostone.leaderboard;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.competition.NewEventRequest;
import com.github.twostone.leaderboard.model.event.EventTypeManager;
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
  private EventTypeManager eventTypeManager;
  
  @Inject
  BootstrapDataPopulator(CompetitionService competitionService,
      EventTypeManager eventTypeManager) {
    super();
    this.competitionService = competitionService;
    this.eventTypeManager = eventTypeManager;
  }

  @Override
  @Transactional
  public synchronized void afterPropertiesSet() throws Exception {
    this.log.info("Bootstrapping data...");
    this.createEventTypes();
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
    
    this.competitionService.addEvent(competition.getId(), this.createEventRequest("Event 1", 1));
    this.competitionService.addEvent(competition.getId(), this.createEventRequest("Event 2", 2));
   
  }
  
  private NewEventRequest createEventRequest(String name, long typeId) {
    NewEventRequest newEvent = new NewEventRequest();
    newEvent.setName(name);
    newEvent.setTypeId(typeId);
    return newEvent;
  }

  private CompetitorRegistrationRequest createRegisterRequest(Division division, String name) {
    CompetitorRegistrationRequest registrationRequest = new CompetitorRegistrationRequest();
    registrationRequest.setDivisionId(division.getId());
    registrationRequest.setName(name);
    return registrationRequest;
  }
  
  private void createEventTypes() {
    this.eventTypeManager.init();
  }

}
