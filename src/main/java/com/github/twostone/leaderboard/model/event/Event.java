package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.score.receipt.ScoreRecipe;

import javax.persistence.Entity;

@Entity
@SuppressWarnings("serial")
public class Event extends AbstractEntity {

  private String name;
  private String description;
  private boolean scalable;

  private ScoreRecipe recipe;

  Event() {
    super();
  }

  /**
   * Creates a new {@link Event} object.
   */
  public Event(String name, String description, ScoreRecipe receipt, boolean scalable) {
    super();
    this.name = name;
    this.description = description;
    this.recipe = receipt;
    this.scalable = scalable;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ScoreRecipe getRecipe() {
    return this.recipe;
  }

  public boolean isScalable() {
    return this.scalable;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Event [name=").append(this.name).append(", description=").append(this.description)
        .append(", scalable=").append(this.scalable).append(", recipe=").append(this.recipe).append(", getId()=")
        .append(this.getId()).append("]");
    return builder.toString();
  }
}
