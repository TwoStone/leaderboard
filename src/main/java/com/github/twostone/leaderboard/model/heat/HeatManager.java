package com.github.twostone.leaderboard.model.heat;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;
import com.github.twostone.leaderboard.model.ranking.RankedCompetitionScore;
import com.github.twostone.leaderboard.model.ranking.RankingManager;
import com.github.twostone.leaderboard.utils.PartitioningSpliterator;
import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeatManager {

  private final CompetitionRepository competitionRepository;
  private final EventRepository eventRepository;
  private final HeatRepository heatRepository;
  private final RankingManager rankingManager;

  @Inject
  HeatManager(
      final EventRepository eventRepository, 
      final HeatRepository heatRepository, 
      final CompetitionRepository competitionRepository, 
      final RankingManager rankingManager) {
    super();
    this.eventRepository = eventRepository;
    this.heatRepository = heatRepository;
    this.competitionRepository = competitionRepository;
    this.rankingManager = rankingManager;
  }
 
  @SuppressWarnings("nls")
  public HeatPlan getHeatPlan(final long eventId) {
    log.info("Getting heat plan for event {}", eventId);
    final Event event = this.eventRepository.getOne(eventId);

    HeatPlan heatPlan = this.heatRepository.findByEvent(event);
    if (heatPlan == null) {
      log.info("No heat plan found, creating new one");
      final List<Heat> heats = Lists.newArrayList();
      final HeatPlan plan = new HeatPlan(heats, event);
      plan.setHeatSize(5);
      heatPlan = this.save(plan);
    }
    return heatPlan;
  }

  public HeatPlan save(final HeatPlan plan) {
    return this.heatRepository.save(plan);
  }
  
  public HeatPlan fillByRanking(long competitionId, HeatPlan plan) {
    Competition competition = this.competitionRepository.getOne(competitionId);
    List<Heat> heats = competition.getDivisions().stream().flatMap(d -> {
      return createHeats(plan, competition, d);
    }).collect(Collectors.toList());
    plan.setHeats(heats);
    return plan;
  }

  private Stream<Heat> createHeats(HeatPlan plan, Competition competition, Division d) {
    List<RankedCompetitionScore> score = this.rankingManager.getCompetitionScore(competition, d);
    List<Heat> heats = PartitioningSpliterator.partition(
        score.stream().map(RankedCompetitionScore::getCompetitor), plan.getHeatSize())
      .map(this::createHeat).collect(Collectors.toList());
    Collections.reverse(heats);
    return heats.stream();
  }

  private Heat createHeat(List<Competitor> competitors) {
    Heat heat = new Heat(competitors);
    return heat;
  }

  public HeatPlan copyFromPrevious(long competitionId, HeatPlan plan) {
    Competition competition = this.competitionRepository.getOne(competitionId);
    int index = competition.getEvents().indexOf(plan.getEvent());
    if (index > 0) {
      Event prevEvent = competition.getEvents().get(index - 1);
      HeatPlan heatPlan = this.getHeatPlan(prevEvent.getId());
      plan.setHeatSize(heatPlan.getHeatSize());
      plan.setHeats(heatPlan.getHeats().stream().map(Heat::getCompetitors).map(Heat::new).collect(Collectors.toList()));
    } 
    
    return plan;
  }

  public HeatPlan mergeHeats(HeatPlan plan, boolean mergeDivisions) {
    List<Heat> heats = Lists.newArrayList();
    Stream<Competitor> map = heats.stream().map(Heat::getCompetitors).flatMap(List::stream);
    if (!mergeDivisions) {
      Map<Division, List<Competitor>> mappedByDivision = map.collect(Collectors.groupingBy(Competitor::getDivision));
      plan.setHeats(mappedByDivision.keySet().stream().map(k -> mappedByDivision.get(k).stream()).flatMap(division -> {
        return PartitioningSpliterator.partition(division, plan.getHeatSize());
      }).map(Heat::new).collect(Collectors.toList()));
    } else {
      plan.setHeats(PartitioningSpliterator.partition(map, plan.getHeatSize()).map(Heat::new).collect(Collectors.toList()));
    }
    
    return plan;
  }


}
