package com.github.twostone.leaderboard.utils;

import com.github.twostone.leaderboard.model.ranking.RankedEventScore;
import com.github.twostone.leaderboard.model.score.Score;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Description;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

/**
 * Provides matchers for {@link RankedEventScore} objects.
 */
public abstract class RankedEventScoreMatchers {
  
  private RankedEventScoreMatchers() { }

  public static Matcher<RankedEventScore> rank(int rank) {
    return new CustomTypeSafeMatcher<RankedEventScore>("rank " + rank) {

      @Override
      protected boolean matchesSafely(RankedEventScore item) {
        return item.getRank() == rank;
      }
      
      @Override
      protected void describeMismatchSafely(RankedEventScore item,
          Description mismatchDescription) {
        mismatchDescription.appendText("rank ");
        mismatchDescription.appendValue(item.getRank());
      }
    };
  }
  
  public static Matcher<RankedEventScore> score(Matcher<Score> scoreMatcher) {
    return new FeatureMatcher<RankedEventScore, Score>(scoreMatcher, "score", "score") {

      @Override
      protected Score featureValueOf(RankedEventScore actual) {
        return actual.getScore();
      }
    };
  }
}
