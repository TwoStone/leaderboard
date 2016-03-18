package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Competitor extends AbstractEntity {

  private String name;
  
  @ManyToOne
  private Division division;
  
  Competitor() {
    super();
  }
  
  /**
   * Constructs a new {@link Competitor}.
   */
  public Competitor(String name, Division division) {
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

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Competitor [name=").append(name)
      .append(", division=").append(division)
      .append(", getId()=").append(getId())
      .append("]");
    return builder.toString();
  }
}
