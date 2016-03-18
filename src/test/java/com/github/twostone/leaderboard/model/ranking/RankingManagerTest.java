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
import com.github.twostone.leaderboard.model.event.EventType;
import com.github.twostone.leaderboard.model.score.Score;
import com.github.twostone.leaderboard.model.score.ScoreManager;
import com.github.twostone.leaderboard.model.score.ScoreRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class RankingManagerTest {
  
  @Rule
  public MockitoRule mockitoRule() {
    return MockitoJUnit.rule();
  }
  
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
    
    this.event = new Event("test-event", "description", EventType.FOR_TIME, false);
    this.competition = new Competition("test-competition");
    this.competition.addEvent(this.event);
    this.competition.addDivision(this.division);
    
    when(this.scoreRepository.findByEventAndCompetitorDivision(Mockito.any(), Mockito.any())).thenReturn(scores);
    
    this.competitionRepository = Mockito.mock(CompetitionRepository.class);
    Mockito.when(competitionRepository.findCompetitionByEvents(this.event))
      .thenReturn(this.competition);
    
    ScoreManager scoreManager = new ScoreManager(scoreRepository, competitionRepository);
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
        new Score(this.event, second, 120L, false),
        new Score(this.event, first, 118L, false),
        new Score(this.event, third, 120L, false)
    ));
    
    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(5));
    assertThat(scores.get(0), is(allOf(rank(1), score(is(allOf(value(118), competitor(is(first))))))));
    assertThat(scores.get(1), is(allOf(rank(2), score(is(allOf(value(120), competitor(is(second))))))));
    assertThat(scores.get(2), is(allOf(rank(2), score(is(allOf(value(120), competitor(is(third))))))));
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
        new Score(this.event, elite1, 100L, false),
        new Score(this.event, elite2, 120L, false),
        new Score(this.event, scaled, 110L, true)
    ));
    
    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(3));
    assertThat(scores.get(0), is(allOf(rank(1), score(is(allOf(value(100), competitor(is(elite1))))))));
    assertThat(scores.get(1), is(allOf(rank(2), score(is(allOf(value(120), competitor(is(elite2))))))));
    assertThat(scores.get(2), is(allOf(rank(3), score(is(allOf(value(110), competitor(is(scaled))))))));
  }
  
  @Test
  public void scaledWithSameScoreRankAlwaysBelow() {
    Competitor elite = new Competitor("elite", this.division);
    Competitor scaled = new Competitor("scaled", this.division);
    this.competition.addRegistration(scaled);
    this.competition.addRegistration(elite);
    
    this.scores.addAll(newArrayList(
        new Score(this.event, elite, 110L, false),
        new Score(this.event, scaled, 110L, true)
    ));
    
    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(2));
    assertThat(scores.get(0), is(allOf(rank(1), score(is(allOf(value(110), competitor(is(elite))))))));
    assertThat(scores.get(1), is(allOf(rank(2), score(is(allOf(value(110), competitor(is(scaled))))))));
  }
}
