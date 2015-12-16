package com.github.twostone.leaderboard.ui.controller;

import com.github.twostone.leaderboard.events.NewCompetitionEvent;
import com.github.twostone.leaderboard.ui.view.CompetitionsView;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;

@SpringComponent
@ViewScope
public class CompetitionsController {

  private CompetitionsView view;

  public void setView(CompetitionsView competitionsView) {
    this.view = competitionsView;
  }

  @Subscribe
  @AllowConcurrentEvents
  public void onCompetitionCreated(NewCompetitionEvent event) {
    this.view.onCompetitionCreated(event.getCompetition());
  }

}
