package com.github.twostone.leaderboard.model.ranking;

import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.score.Score;
import com.github.twostone.leaderboard.model.score.ScoreManager;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

@Component
public class RankingManager {
  
  private ScoreManager scoreManager;
  
  @Inject
  RankingManager(ScoreManager scoreManager) {
    super();
    this.scoreManager = scoreManager;
  }

  /**
   * Return a score board for the event with the ranked scores.
   */
  public EventScoreBoard getEventScoreBoard(Event event) {
    Ordering<Score> eventOrdering = event.getType().getOrdering().getComparator();
    Ordering<Score> ordering = Ordering.from(sortOutUnset(eventOrdering));
    List<Score> scores = Lists.newArrayList(this.scoreManager.findScoreByEvent(event));
    Map<Division, List<Score>> groups = 
        scores.stream().collect(Collectors.groupingBy(
            score -> score.getCompetitor().getDivision()));
    
    ListMultimap<Division, RankedEventScore> rankedScores = ArrayListMultimap.create();
    
    for (Division division : groups.keySet()) {
      rankedScores.putAll(division, rank(ordering, groups.get(division)));
    }
    
    return new EventScoreBoard(event, rankedScores);
  }

  private static List<RankedEventScore> rank(Ordering<Score> ordering,
      List<Score> list) {
    List<Score> scores = ordering.sortedCopy(list);

    List<RankedEventScore> rankedScores = Lists.newArrayList();
    
    int lastRank = 1;
    Score lastScore = null;
    
    for (int i = 1; i <= scores.size(); i++) {
      Score score = scores.get(i - 1);
      if (lastScore == null || ordering.compare(score, lastScore) == 0) {
        rankedScores.add(new RankedEventScore(score, lastRank));
      } else {
        rankedScores.add(new RankedEventScore(score, i));
        lastRank = i;
      }
      lastScore = score;
    }
    
    return rankedScores;
  }

  private static Comparator<Score> sortOutUnset(Ordering<Score> eventOrdering) {
    return (left, right) -> {
      if (left.getValue() < 0 && right.getValue() >= 0) {
        return -1;
      }
      if (left.getValue() < 0 && right.getValue() < 0) {
        return 0;
      }
      if (left.getValue() >= 0 && right.getValue() < 0) {
        return 1;
      }
      return eventOrdering.compare(left, right);
    };
  }

  public List<RankedEventScore> getEventScore(Event event, Division division) {
    List<Score> scores = Lists.newArrayList(this.scoreManager.findScoreByEventAndDivision(event, division));
    return rank(event.getType().getOrdering().getComparator(), scores);
  }
}
