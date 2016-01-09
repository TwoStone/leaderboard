package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Division extends AbstractEntity {

  private String name;

  public Division(String name) {
    this();
    this.name = name;
  }

  Division() {
    super();
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
