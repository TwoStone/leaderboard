package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class CompetitionRegistration extends AbstractEntity {

  private String name;
  @ManyToOne
  private Division division;
  
  CompetitionRegistration() {
    super();
  }
  
  public CompetitionRegistration(String name, Division division) {
    this();
    this.name = name;
    this.division = division;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Division getDivision() {
    return this.division;
  }

  public void setDivision(Division division) {
    this.division = division;
  }
}
