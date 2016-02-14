package com.github.twostone.leaderboard;

import com.github.twostone.leaderboard.model.ModelConfiguration;
import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionManager;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.EventTypeManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import javax.persistence.EntityManager;

@SpringBootApplication
@Import(ModelConfiguration.class)
public class LeaderboardApplication {

  /**
   * Creates demo data.
   */
  @Bean
  public CommandLineRunner demo(
      EntityManager entityManager, 
      CompetitionManager competitionManager) {
    return (args) -> {
      Competition demoComp = competitionManager.findOne(0L);
      if (demoComp != null) {
        return;
      }
      
      Competition competition = competitionManager.createCompetition("DEMO Competition");
      Division eliteDivision = competitionManager.createDivision(competition, "Elite");
      competition = competitionManager.refresh(competition);
      competitionManager.register(competition, eliteDivision, "Team Froning");
    };
  }
  
  /**
   * Bootstraps the event type database.
   */
  @Bean
  public CommandLineRunner bootstrapTypes(EventTypeManager eventTypeManager) {
    return (args) -> {
      eventTypeManager.init();
    };
  }
}
