package com.github.twostone.leaderboard.ui.controller;

import com.github.twostone.leaderboard.events.NewCompetitionEvent;
import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.ui.view.CreateCompetitionView;

import com.google.common.eventbus.EventBus;
import com.vaadin.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@SpringComponent
public class CreateCompetitionController {


  private CreateCompetitionView view;

  @Autowired
  private EventBus eventBus;
  @Autowired
  private CompetitionRepository repository;

  /**
   * Creates a new competition.
   */
  public void createCompetition(String name, LocalDate date) {
    Competition competition = this.repository.save(new Competition(name, date));
    this.eventBus.post(new NewCompetitionEvent(competition));
    this.view.onCompetitionCreated(competition);
  }

  public void setView(CreateCompetitionView createCompetitionView) {
    this.view = createCompetitionView;
  }

}
