package com.github.twostone.leaderboard;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.competition.NewEventRequest;
import com.github.twostone.leaderboard.model.score.recipe.ScoreIngredient;
import com.github.twostone.leaderboard.model.score.recipe.ScoreIngredientType;
import com.github.twostone.leaderboard.model.score.recipe.ScoreRecipe;
import com.github.twostone.leaderboard.services.CompetitionService;
import com.github.twostone.leaderboard.services.CompetitionService.CompetitorRegistrationRequest;

import com.google.common.collect.Lists;
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

    
    for (int i = 1; i <= 30; i++) {
      this.competitionService.registerCompetitor(competition.getId(),
          this.createRegisterRequest(eliteDivision, "Elite Team " + i));
    }
    
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

    ScoreIngredient forTime =  new ScoreIngredient();
    forTime.setName("time");
    forTime.setType(ScoreIngredientType.TIME);
    this.competitionService.addEvent(competition.getId(),
        this.createEventRequest("Event 1",  new ScoreRecipe(Lists.newArrayList(forTime))));

    ScoreIngredient forPoints =  new ScoreIngredient();
    forPoints.setName("points");
    forPoints.setType(ScoreIngredientType.POINTS);
    this.competitionService.addEvent(competition.getId(),
        this.createEventRequest("Event 2", new ScoreRecipe(Lists.newArrayList(forPoints))));

  }

  private NewEventRequest createEventRequest(String name, ScoreRecipe recipe) {
    NewEventRequest newEvent = new NewEventRequest();
    newEvent.setName(name);
    newEvent.setRecipe(recipe);
    return newEvent;
  }

  private CompetitorRegistrationRequest createRegisterRequest(Division division, String name) {
    CompetitorRegistrationRequest registrationRequest = new CompetitorRegistrationRequest();
    registrationRequest.setDivisionId(division.getId());
    registrationRequest.setName(name);
    return registrationRequest;
  }
}
