package com.github.twostone.leaderboard.model.event;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;

@RestController
@RequestMapping(path = "api/competitions/{id}/events")
@Transactional
public class EventService {

  private static final Logger logger = LoggerFactory.getLogger(EventService.class);
  
  private EventRepository eventRepository;
  private CompetitionRepository competitionRepository;

  @Inject
  EventService(EventRepository eventRepository, CompetitionRepository competitionRepository) {
    super();
    this.eventRepository = eventRepository;
    this.competitionRepository = competitionRepository;
  }

  @RequestMapping("{eventId}")
  public Event getEventById(@PathVariable("eventId") Long eventId) {
    Event event = this.eventRepository.getOne(eventId);
    logger.info("found event {}", event);
    return event;
  }

  @PostMapping("")
  public Event saveEvent(@RequestBody Event event, @PathVariable("id") Long competitionId) {
    boolean isNew = event.getId() == null;
    Event saved = this.eventRepository.save(event);
    if (isNew) {
      Competition competition = this.competitionRepository.getOne(competitionId);
      competition.addEvent(saved);
      this.competitionRepository.save(competition);
    }
    return saved;
  }
}
