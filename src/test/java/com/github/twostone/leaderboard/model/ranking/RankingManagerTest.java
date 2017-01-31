package com.github.twostone.leaderboard.model.ranking;

import static com.github.twostone.leaderboard.utils.RankedEventScoreMatchers.rank;
import static com.github.twostone.leaderboard.utils.RankedEventScoreMatchers.score;
import static com.github.twostone.leaderboard.utils.ScoreMatchers.competitor;
import static com.github.twostone.leaderboard.utils.ScoreMatchers.notSet;
import static com.github.twostone.leaderboard.utils.ScoreMatchers.value;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.score.PartialScore;
import com.github.twostone.leaderboard.model.score.Score;
import com.github.twostone.leaderboard.model.score.ScoreManager;
import com.github.twostone.leaderboard.model.score.ScoreRepository;
import com.github.twostone.leaderboard.model.score.receipt.ScoreIngredient;
import com.github.twostone.leaderboard.model.score.receipt.ScoreIngredientType;
import com.github.twostone.leaderboard.model.score.receipt.ScoreRecipe;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankingManagerTest {

  @Rule
  public MockitoRule mockitoRule() {
    return MockitoJUnit.rule();
  }

  private ScoreRecipe forPoints;
  private ScoreRecipe forTime;
  private Event event;
  private RankingManager rankingManager;
  private List<Score> scores;
  private Competition competition;
  private Division division;
  @Mock
  private ScoreRepository scoreRepository;
  @Mock
  private CompetitionRepository competitionRepository;

  /**
   * Setup fixtures.
   */
  @Before
  public void setupEvent() {
    this.scores = new ArrayList<>();
    this.division = new Division("division");

    ScoreIngredient points = new ScoreIngredient();
    points.setName("points");
    points.setType(ScoreIngredientType.POINTS);
    this.forPoints = new ScoreRecipe(Lists.newArrayList(points));
    ScoreIngredient time = new ScoreIngredient();
    time.setName("time");
    time.setType(ScoreIngredientType.TIME);
    ScoreIngredient reps = new ScoreIngredient();
    reps.setName("reps");
    reps.setType(ScoreIngredientType.POINTS);
    this.forTime = new ScoreRecipe(Lists.newArrayList(time, reps));

    this.event = new Event("test-event", "description", this.forTime, false);
    this.competition = new Competition("test-competition");
    this.competition.addEvent(this.event);
    this.competition.addDivision(this.division);

    when(this.scoreRepository.findByEventAndCompetitorDivision(Matchers.any(), Matchers.any())).thenReturn(this.scores);

    this.competitionRepository = Mockito.mock(CompetitionRepository.class);
    Mockito.when(this.competitionRepository.findCompetitionByEvents(this.event))
      .thenReturn(this.competition);

    ScoreManager scoreManager = new ScoreManager(this.scoreRepository, this.competitionRepository);
    this.rankingManager = new RankingManager(scoreManager);
  }

  @Test
  public void testGetScoreEventScoreBoard() {
    Competitor second = new Competitor("second", this.division);
    Competitor fourth = new Competitor("fourth", this.division);
    Competitor first = new Competitor("first", this.division);
    Competitor third = new Competitor("third", this.division);
    Competitor fifth = new Competitor("fifth", this.division);
    this.competition.addRegistration(first);
    this.competition.addRegistration(second);
    this.competition.addRegistration(third);
    this.competition.addRegistration(fourth);
    this.competition.addRegistration(fifth);

    this.scores.addAll(newArrayList(
        s(this.event, second, false, time(120L, 0L)),
        s(this.event, first, false, time(118L, 0L)),
        s(this.event, third, false, time(120L, 0L))
    ));

    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(5));
    assertThat(scores.get(0), is(allOf(rank(1), score(is(allOf(value(time(118)), competitor(is(first))))))));
    assertThat(scores.get(1), is(allOf(rank(2), score(is(allOf(value(time(120)), competitor(is(second))))))));
    assertThat(scores.get(2), is(allOf(rank(2), score(is(allOf(value(time(120)), competitor(is(third))))))));
    assertThat(scores.get(3), is(allOf(rank(4), score(is(allOf(notSet(), competitor(is(fourth))))))));
    assertThat(scores.get(4),is(allOf(rank(4), score(is(allOf(notSet(), competitor(is(fifth))))))));
  }

  @Test
  public void scaledRankAlwaysBelow() {
    Competitor elite1 = new Competitor("elite1", this.division);
    Competitor elite2 = new Competitor("elite2", this.division);
    Competitor scaled = new Competitor("scaled", this.division);
    this.competition.addRegistration(scaled);
    this.competition.addRegistration(elite1);
    this.competition.addRegistration(elite2);

    this.scores.addAll(newArrayList(
        s(this.event, elite1, false, time(100L, 0L)),
        s(this.event, elite2, false, time(120L, 0L)),
        s(this.event, scaled, true, time(110L, 0L))
    ));

    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(3));
    assertThat(scores.get(0), is(allOf(rank(1), score(is(allOf(value(time(100)), competitor(is(elite1))))))));
    assertThat(scores.get(1), is(allOf(rank(2), score(is(allOf(value(time(120)), competitor(is(elite2))))))));
    assertThat(scores.get(2), is(allOf(rank(3), score(is(allOf(value(time(110)), competitor(is(scaled))))))));
  }

  @Test
  public void scaledWithSameScoreRankAlwaysBelow() {
    Competitor elite = new Competitor("elite", this.division);
    Competitor scaled = new Competitor("scaled", this.division);
    this.competition.addRegistration(scaled);
    this.competition.addRegistration(elite);

    this.scores.addAll(newArrayList(
        s(this.event,elite, false, time(110L, 0L)),
        s(this.event,scaled, true, time(110L, 0L))
    ));

    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(2));
    assertThat(scores.get(0), is(allOf(rank(1), score(is(allOf(value(time(110)), competitor(is(elite))))))));
    assertThat(scores.get(1), is(allOf(rank(2), score(is(allOf(value(time(110)), competitor(is(scaled))))))));
  }

  @Test
  public void testRankingWithTimeCap() {
    Competitor elite1 = new Competitor("elite 1", this.division);
    Competitor elite2 = new Competitor("elite 2", this.division);
    this.competition.addRegistration(elite1);
    this.competition.addRegistration(elite2);

    this.scores.addAll(newArrayList(
        s(this.event,elite1, false, time(110L, 200L)),
        s(this.event,elite2, false, time(110L, 210L))
    ));

    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(2));
    assertThat(scores.get(0), is(allOf(rank(1), score(is(competitor(is(elite2)))))));
    assertThat(scores.get(1), is(allOf(rank(2), score(is(competitor(is(elite1)))))));
  }

  private static Map<String, PartialScore> time(long time, long reps) {
    HashMap<String, PartialScore> score = Maps.newHashMap();
    score.put("time", new PartialScore("time", time));
    score.put("reps", new PartialScore("reps", reps));
    return score;
  }

  private static Score s(Event event, Competitor competitor, boolean scaled, Map<String, PartialScore> scores) {
    Score score = new Score(event, competitor, scaled);
    score.setParts(scores);
    return score;
  }

  private static Matcher<Map<String, PartialScore>> time(long time) {
    return new CustomTypeSafeMatcher<Map<String,PartialScore>>("time") {

      @Override
      protected boolean matchesSafely(Map<String, PartialScore> item) {
        return item.get("time").getValue() == time;
      }
    };
  }
}
