package com.github.twostone.leaderboard.model.score.recipe;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuppressWarnings("serial")
@Entity
public class ScoreIngredient extends AbstractEntity {

  private String name;

  public ScoreIngredient() {
  }
  
  @Enumerated(EnumType.STRING)
  private ScoreIngredientType type;

  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  public ScoreIngredientType getType() {
    return this.type;
  }

  public void setType(ScoreIngredientType type) {
    this.type = type;
  }
}
