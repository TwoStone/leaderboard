package com.github.twostone.leaderboard.model.heat;

import java.util.List;

import javax.inject.Inject;

import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HeatManager {

  private final EventRepository eventRepository;
  private final HeatRepository heatRepository;

  @Inject
  HeatManager(final EventRepository eventRepository, final HeatRepository heatRepository) {
    super();
    this.eventRepository = eventRepository;
    this.heatRepository = heatRepository;
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

}
