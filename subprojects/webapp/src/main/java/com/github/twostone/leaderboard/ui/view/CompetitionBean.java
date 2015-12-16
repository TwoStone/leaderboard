package com.github.twostone.leaderboard.ui.view;

import java.time.LocalDate;

public class CompetitionBean {

  private String id;
  private String name;
  private LocalDate date;

  CompetitionBean(String id, String name, LocalDate date) {
    super();
    this.id = id;
    this.name = name;
    this.date = date;
  }

  public String getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public LocalDate getDate() {
    return this.date;
  }
}
