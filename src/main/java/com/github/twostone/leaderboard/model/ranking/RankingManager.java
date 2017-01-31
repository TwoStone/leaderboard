package com.github.twostone.leaderboard.model.ranking;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.score.Score;
import com.github.twostone.leaderboard.model.score.ScoreComparatorFactory;
import com.github.twostone.leaderboard.model.score.ScoreManager;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
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
   * Return the ranked scores for the event and division.
   */
  public List<RankedEventScore> getEventScore(Event event, Division division) {
    List<Score> scores = this.scoreManager.findScoreByEventAndDivision(event, division);
    Comparator<Score> comparator = ScoreComparatorFactory.createComparator(event.getRecipe());
    return rank(scores, comparator);
  }

  /**
   * Returns the ranked scores for the competition.
   */
  public List<RankedCompetitionScore> getCompetitionScore(
      Competition competition, Division division) {
    List<List<RankedEventScore>> scores =
        competition.getEvents().stream().map(event -> this.getEventScore(event, division))
          .collect(Collectors.toList());
    ListMultimap<Competitor, RankedEventScore> competitorScores = ArrayListMultimap.create();
    for (List<RankedEventScore> list : scores) {
      for (RankedEventScore rankedEventScore : list) {
        competitorScores.put(rankedEventScore.getScore().getCompetitor(), rankedEventScore);
      }
    }

    List<RankedCompetitionScore> result = new ArrayList<>();
    for (Competitor competitor : competitorScores.keySet()) {
      result.add(new RankedCompetitionScore(competitor, competitorScores.get(competitor)));
    }
    Collections.sort(result);

    int lastRank = 1;
    RankedCompetitionScore lastScore = null;

    for (int i = 1; i <= result.size(); i++) {
      RankedCompetitionScore rankedCompetitionScore = result.get(i - 1);

      if (lastScore == null || lastScore.compareTo(rankedCompetitionScore) == 0) {
        rankedCompetitionScore.setRank(lastRank);
      } else {
        rankedCompetitionScore.setRank(i);
        lastRank = i;
      }
      lastScore = rankedCompetitionScore;
    }

    return result;
  }

  private static List<RankedEventScore> rank(List<Score> scores, Comparator<Score> comparator) {
    List<RankedEventScore> rankedScores = Lists.newArrayList();

    int lastRank = 1;
    Score lastScore = null;

    for (int i = 1; i <= scores.size(); i++) {
      Score score = scores.get(i - 1);
      if (lastScore == null || comparator.compare(score, lastScore) == 0) {
        rankedScores.add(new RankedEventScore(score, lastRank));
      } else {
        rankedScores.add(new RankedEventScore(score, i));
        lastRank = i;
      }
      lastScore = score;
    }

    return rankedScores;
  }

}
