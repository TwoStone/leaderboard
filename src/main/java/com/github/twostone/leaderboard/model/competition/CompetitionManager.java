package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;
import com.github.twostone.leaderboard.model.event.EventType;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CompetitionManager {
 
  private CompetitionRepository competitionRepository;
  private DivisionRespository divisionRepository;
  private RegistrationRepository registrationRepository;
  private EventRepository eventRepository;
  
  @Inject
  CompetitionManager(CompetitionRepository competitionRepository, 
      DivisionRespository divisionRespository,
      RegistrationRepository registrationRepository,
      EventRepository eventRepository) {
    super();
    this.competitionRepository = competitionRepository;
    this.divisionRepository = divisionRespository;
    this.registrationRepository = registrationRepository;
    this.eventRepository = eventRepository;
  }
  
  public Competition refresh(Competition oldCompetition) {
    return this.findOne(oldCompetition.getId());
  }
 
  /**
   * Creates a new division for the competition.
   */
  public Division createDivision(Competition competition, String divisionName) {
    Division division = new Division(divisionName);
    division = this.divisionRepository.saveAndFlush(division);
    competition.addDivision(division);
    this.competitionRepository.saveAndFlush(competition);
    
    return division;
  }
  
  public Competition findOne(Long id) {
    return this.competitionRepository.findOne(id);
  }

  public Competition createCompetition(String name) {
    return this.competitionRepository.saveAndFlush(new Competition(name));
  }

  /**
   * Registers a new competitor for the competition with the given division and name.
   */
  public Competitor register(Competition competition, Division division, String name) {
    Competitor registration = new Competitor(name, division);
    registration = this.registrationRepository.save(registration);
    competition.addRegistration(registration);
    this.competitionRepository.saveAndFlush(competition);
    return registration;
  }

  /**
   * Adds a new event to the competition.
   */
  public Event addEvent(Competition competition, 
      String eventName, 
      String description, 
      EventType eventType, 
      boolean scalable) {
    Event event = new Event(eventName, description, eventType, scalable);
    
    event = this.eventRepository.saveAndFlush(event);
    competition.addEvent(event);
    
    this.competitionRepository.saveAndFlush(competition);
    return event;
  }

  public Iterable<Competition> findAll() {
    return this.competitionRepository.findAll();
  }
}
