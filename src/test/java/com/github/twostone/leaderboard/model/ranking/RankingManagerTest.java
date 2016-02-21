package com.github.twostone.leaderboard.model.ranking;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventType;
import com.github.twostone.leaderboard.model.score.Score;
import com.github.twostone.leaderboard.model.score.ScoreManager;
import com.github.twostone.leaderboard.model.score.ScoreRepository;

import com.google.common.collect.Lists;
import org.hamcrest.CustomTypeSafeMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class RankingManagerTest {

  private Event event;
  private RankingManager rankingManager;
  private List<Score> scores;
  private Competition competition;
  private Division division;
  
  /**
   * Setup fixtures.
   */
  @Before
  public void setupEvent() {
    this.scores = new ArrayList<>();
    this.division = new Division("division");
    
    this.event = new Event("test-event", "description", EventType.FOR_TIME);
    this.competition = new Competition("test-competition");
    this.competition.addEvent(this.event);
    this.competition.addDivision(this.division);
    
    ScoreRepository scoreRepository = Mockito.mock(ScoreRepository.class);
    Mockito.when(scoreRepository.findByEvent(this.event)).thenReturn(this.scores);
    
    CompetitionRepository competitionRepository = Mockito.mock(CompetitionRepository.class);
    Mockito.when(competitionRepository.findCompetitionByEvents(this.event))
      .thenReturn(this.competition);
    
    ScoreManager scoreManager = new ScoreManager(scoreRepository, competitionRepository);
    this.rankingManager = new RankingManager(scoreManager);
  }
  
  @SuppressWarnings("unchecked")
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
    
    this.scores.addAll(Lists.newArrayList(
        new Score(this.event, second, 120L),
        new Score(this.event, first, 118L),
        new Score(this.event, third, 120L)
    ));
    
    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasSize(5));
    assertThat(scores.subList(3, 4), hasItems(
          isUnset(),
          isUnset()));
  }
  
  private static Matcher<RankedEventScore> isUnset() {
    return new CustomTypeSafeMatcher<RankedEventScore>("score is unset") {

      @Override
      protected boolean matchesSafely(RankedEventScore item) {
        return item.getScore().isNotSet();
      }
    };
  }
}
