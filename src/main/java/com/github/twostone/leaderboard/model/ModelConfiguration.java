package com.github.twostone.leaderboard.model;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRegistration;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.eventbus.EventBus;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class ModelConfiguration {
  
  /**
   * Configure the spring data rest repositories.
   */
  @Bean
  public RepositoryRestConfigurerAdapter configureRestRepository() {
    return new RepositoryRestConfigurerAdapter() {
      @Override
      public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        config.exposeIdsFor(
            Competition.class, 
            Division.class, 
            Event.class, 
            CompetitionRegistration.class);
        config.setBasePath("/api");
      }
    };
  }
  
  @Bean
  public EventBus eventBus() {
    return new EventBus();
  }
  
  @Bean
  public Module installJsr310Module() {
    return new JavaTimeModule();
  }
  
}
