package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.score.receipt.ScoreRecipe;

public class NewEventRequest {
  String name;
  String description;
  ScoreRecipe recipe;
  boolean scalable;

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

  public void setRecipe(ScoreRecipe recipe) {
    this.recipe = recipe;
  }

  public boolean isScalable() {
    return this.scalable;
  }

  public void setScalable(boolean scalable) {
    this.scalable = scalable;
  }
}