package com.github.twostone.leaderboard.utils;

import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.score.Score;

import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.FeatureMatcher;
import org.hamcrest.Matcher;

/**
 * Provides matchers for {@link Score} objects.
 */
public abstract class ScoreMatchers {
  
  private ScoreMatchers() { }

  public static Matcher<Score> value(int score) {
    return new CustomTypeSafeMatcher<Score>("") {

      @Override
      protected boolean matchesSafely(Score item) {
        return item.getScore() == score;
      }
    };
  }
  
  /**
   * Returns a matcher that checks that the score is not set.
   */
  public static Matcher<Score> notSet() {
    return new CustomTypeSafeMatcher<Score>("not set") {

      @Override
      protected boolean matchesSafely(Score item) {
        return item.isNotSet();
      }
    };
  }
  
  public static Matcher<Score> competitor(Matcher<Competitor> competitorMatcher) {
    return new FeatureMatcher<Score, Competitor>(competitorMatcher, "competitor", "competitor") {

      @Override
      protected Competitor featureValueOf(Score actual) {
        return actual.getCompetitor();
      }
    };
  }
  
  public static Matcher<Score> event(Matcher<Event> eventMatcher) {
    return new FeatureMatcher<Score, Event>(eventMatcher, "event", "event") {

      @Override
      protected Event featureValueOf(Score actual) {
        return actual.getEvent();
      }
    };
  }
  
  
  
}
