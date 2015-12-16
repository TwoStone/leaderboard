package com.github.twostone.leaderboard.ui.view;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.ui.controller.CreateCompetitionController;
import com.github.twostone.leaderboard.ui.design.CreateCompetitionDesign;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.annotation.PostConstruct;

@SuppressWarnings("serial")
@SpringView(name = CreateCompetitionView.VIEW_NAME)
public class CreateCompetitionView extends CreateCompetitionDesign implements View {

  public static final String VIEW_NAME = "createCompetition";

  @Autowired
  private CreateCompetitionController controller;

  @Override
  public void enter(ViewChangeEvent event) {}

  /**
   * Initializes the view.
   */
  @PostConstruct
  public void init() {
    this.controller.setView(this);
    this.submitButton.addClickListener(new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        CreateCompetitionView.this.createCompetition();
      }
    });

    this.abortButton.addClickListener(new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        CreateCompetitionView.this.getUI().getNavigator().navigateTo(CompetitionsView.VIEW_NAME);
      }
    });

  }

  private void createCompetition() {
    String name = this.nameField.getValue();
    Date date = this.dateField.getValue();
    LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

    this.controller.createCompetition(name, localDate);
  }

  public void onCompetitionCreated(Competition competition) {
    this.getUI().getNavigator().navigateTo(CompetitionsView.VIEW_NAME);
  }

}
