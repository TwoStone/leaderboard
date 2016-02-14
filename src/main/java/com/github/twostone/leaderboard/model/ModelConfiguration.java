package com.github.twostone.leaderboard.model;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.eventbus.EventBus;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class ModelConfiguration {
  
  @Bean
  public EventBus eventBus() {
    return new EventBus();
  }
  
  @Bean
  public Module installJsr310Module() {
    return new JavaTimeModule();
  }
}
