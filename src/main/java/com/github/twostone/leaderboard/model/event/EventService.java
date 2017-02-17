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

  private final EventRepository eventRepository;
  private final CompetitionRepository competitionRepository;

  @Inject
  EventService(final EventRepository eventRepository, final CompetitionRepository competitionRepository) {
    super();
    this.eventRepository = eventRepository;
    this.competitionRepository = competitionRepository;
  }

  @RequestMapping("{eventId}")
  public Event getEventById(@PathVariable("eventId") final Long eventId) {
    final Event event = this.eventRepository.getOne(eventId);
    logger.info("found event {}", event); //$NON-NLS-1$
    return event;
  }

  @PostMapping("")
  public Event saveEvent(@RequestBody final Event event, @PathVariable("id") final Long competitionId) {
    final boolean isNew = event.getId() == null;
    final Event saved = this.eventRepository.save(event);
    if (isNew) {
      final Competition competition = this.competitionRepository.getOne(competitionId);
      competition.addEvent(saved);
      this.competitionRepository.save(competition);
    }
    return saved;
  }
}
