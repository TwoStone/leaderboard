package com.github.twostone.leaderboard.model.competition;

import javax.persistence.Entity;

@Entity
public class TeamRegistration extends CompetitionRegistration {

  private static final long serialVersionUID = 1L;

  private String name;

  protected TeamRegistration(String name, Competition competition, Division division) {
    super(competition, division);
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}