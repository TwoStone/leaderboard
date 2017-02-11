package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestMapping(path = "api/competition/{id}",
 consumes = MediaType.APPLICATION_JSON_VALUE,
 produces = MediaType.APPLICATION_JSON_VALUE)
public class EventService {

  private EventRepository eventRepository;
  private CompetitionRepository competitionRepository;

  @Inject
  EventService(EventRepository eventRepository, CompetitionRepository competitionRepository) {
    super();
    this.eventRepository = eventRepository;
    this.competitionRepository = competitionRepository;
  }

  @GetMapping(path = "events/{eventId}")
  public Event getEventById(@PathVariable("eventId") Long eventId) {
    Event event = this.eventRepository.getOne(eventId);
    return event;
  }

  @PostMapping(path = "events")
  public Event saveEvent(Event event, Long competitionId) {
    Event saved = this.eventRepository.save(event);
    if (event.getId() == null) {
      Competition competition = this.competitionRepository.getOne(competitionId);
      competition.addEvent(saved);
      this.competitionRepository.save(competition);
    }
    return saved;
  }
}
