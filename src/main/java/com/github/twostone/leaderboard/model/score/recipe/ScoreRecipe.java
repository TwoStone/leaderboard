package com.github.twostone.leaderboard.model.score.recipe;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@SuppressWarnings("serial")
@Entity
public class ScoreRecipe extends AbstractEntity {

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @OrderColumn
  private List<ScoreIngredient> parts;

  public ScoreRecipe() {
    this.parts = Lists.newArrayList();
  }

  @JsonCreator
  public ScoreRecipe(
      @JsonProperty("parts") List<ScoreIngredient> parts) {
    this();
    this.parts = parts;
  }

  public List<ScoreIngredient> getParts() {
    return this.parts;
  }
}
