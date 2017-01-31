package com.github.twostone.leaderboard.model.score;

import com.github.twostone.leaderboard.model.score.receipt.ScoreIngredient;
import com.github.twostone.leaderboard.model.score.receipt.ScoreIngredientType;
import com.github.twostone.leaderboard.model.score.receipt.ScoreRecipe;

import com.google.common.collect.Ordering;

import java.util.Comparator;
import java.util.Objects;

public class ScoreComparatorFactory implements Comparator<Score> {


  private ScoreRecipe recipe;

  private ScoreComparatorFactory(ScoreRecipe recipe) {
    this.recipe = recipe;
  }

  public static Comparator<Score> createComparator(ScoreRecipe recipe) {
    return Ordering.from(scaledComparator()).compound(new ScoreComparatorFactory(recipe));
  }

  private Comparator<PartialScore> createComparator(ScoreIngredient ingredient) {
    return this.createForType(ingredient.getType());
  }

  private Comparator<PartialScore> createForType(ScoreIngredientType type) {
    switch (type) {
    case POINTS:
      return Ordering.natural().nullsFirst().reverse();
    case TIME:
    default:
      return Ordering.natural().nullsFirst();
    }
  }

  @Override
  public int compare(Score me, Score other) {
    for (ScoreIngredient ingredient : this.recipe.getParts()) {
      Comparator<PartialScore> comparator = this.createComparator(ingredient);

      int result = Objects.compare(me.getPart(ingredient.getName()), other.getPart(ingredient.getName()), comparator);
      if (result != 0) {
        return result;
      }
    }
    return 0;
  }

  private static Comparator<Score> scaledComparator() {
    return new Comparator<Score>() {

      @Override
      public int compare(Score o1, Score o2) {
        // parameters switched because when o2 isScaled o1 has to be higher.
        return Boolean.compare(o1.isScaled(), o2.isScaled());
      }
    };

  }
}
