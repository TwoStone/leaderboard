package com.github.twostone.leaderboard.model.score.receipt;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class ScoreRecipe extends AbstractEntity {

  @OneToMany(cascade = CascadeType.ALL)
  private List<ScoreIngredient> parts;

  public ScoreRecipe() {
    this.parts = Lists.newArrayList();
  }

  @JsonCreator
  public ScoreRecipe(
      @JsonProperty("parts") List<ScoreIngredient> parts) {
    this.parts = parts;
  }

  public List<ScoreIngredient> getParts() {
    return this.parts;
  }
}
