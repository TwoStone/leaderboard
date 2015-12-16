package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.AbstractEntity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Entity implementation class for Entity: Division.
 */
@Entity
public class Division extends AbstractEntity implements Serializable {

  private String name;

  @Enumerated(EnumType.STRING)
  private DivisionType type;

  private static final long serialVersionUID = 1L;

  protected Division() {
    super();
  }

  public Division(String name, DivisionType type) {
    super();
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DivisionType getType() {
    return this.type;
  }

  public void setType(DivisionType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
