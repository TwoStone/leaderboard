package com.github.twostone.leaderboard.model.score.receipt;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuppressWarnings("serial")
@Entity
public class ScoreIngredient extends AbstractEntity {

  private String name;

  @Enumerated(EnumType.STRING)
  private ScoreIngredientType type;

  public String getName() {
    return this.name;
  }

  public ScoreIngredientType getType() {
    return this.type;
  }

  public void setType(ScoreIngredientType type) {
    this.type = type;
  }

  public void setName(String name) {
    this.name = name;
  }
}
