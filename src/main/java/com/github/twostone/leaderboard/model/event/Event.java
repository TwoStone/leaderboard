package com.github.twostone.leaderboard.model.event;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.score.recipe.ScoreRecipe;

@Entity
@SuppressWarnings("serial")
public class Event extends AbstractEntity {

  private String name;
  private String description;
  private boolean scalable;

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private ScoreRecipe recipe;

  Event() {
    super();
  }

  /**
   * Creates a new {@link Event} object.
   */
  @JsonCreator
  public Event(
      @JsonProperty("name") String name, 
      @JsonProperty("description") String description, 
      @JsonProperty("recipe") ScoreRecipe receipt, 
      @JsonProperty("scalable") boolean scalable) {
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
