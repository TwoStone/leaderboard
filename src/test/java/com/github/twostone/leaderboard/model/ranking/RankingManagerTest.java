package com.github.twostone.leaderboard.model.ranking;

import static com.github.twostone.leaderboard.utils.RankedEventScoreMatchers.rank;
import static com.github.twostone.leaderboard.utils.RankedEventScoreMatchers.score;
import static com.github.twostone.leaderboard.utils.ScoreMatchers.competitor;
import static com.github.twostone.leaderboard.utils.ScoreMatchers.notSet;
import static com.github.twostone.leaderboard.utils.ScoreMatchers.value;
import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
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

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.util.ArrayList;
import java.util.Comparator;
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
    
    when(this.scoreRepository.findByEventAndCompetitorDivision(Mockito.any(), Mockito.any(), Mockito.any())).then(sortedScored());
    
    this.competitionRepository = Mockito.mock(CompetitionRepository.class);
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
    
    this.scores.addAll(newArrayList(
        new Score(this.event, second, 120L),
        new Score(this.event, first, 118L),
        new Score(this.event, third, 120L)
    ));
    
    List<RankedEventScore> scores = this.rankingManager.getEventScore(this.event, this.division);
    assertThat(scores, hasItems(
        is(allOf(rank(1), score(is(allOf(value(118), competitor(is(first))))))),
        is(allOf(rank(2), score(is(allOf(value(120), competitor(is(second))))))),
        is(allOf(rank(2), score(is(allOf(value(120), competitor(is(third))))))),
        is(allOf(rank(4), score(is(allOf(notSet(), competitor(is(fourth))))))),
        is(allOf(rank(4), score(is(allOf(notSet(), competitor(is(fifth)))))))));
  }
  
  private Answer<?> sortedScored() {
    return (invocation) -> {
      List<Score> result = Lists.newArrayList(scores);
      result.sort(new Comparator<Score>() {

        @Override
        public int compare(Score o1, Score o2) {
          Sort sort = invocation.getArgumentAt(2, Sort.class);
          Direction direction = sort.getOrderFor("score").getDirection();
          return 
              (direction == Direction.ASC ? 1 : -1) * 
              (int) (o1.getScore() - o2.getScore());
        }
      });
      return result;
    };
  }
}
