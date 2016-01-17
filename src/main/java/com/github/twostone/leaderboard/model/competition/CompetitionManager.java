package com.github.twostone.leaderboard.model.competition;

import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventRepository;

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
    
    division = this.divisionRepository.save(division);
    competition.addDivision(division);
    this.competitionRepository.save(competition);
    
    return division;
  }
  
  public Competition findOne(Long id) {
    return this.competitionRepository.findOne(id);
  }

  public Competition createCompetition(String name) {
    return this.competitionRepository.save(new Competition(name));
  }

  /**
   * Registers a new competitor for the competition with the given division and name.
   */
  public void register(Competition competition, Division division, String name) {
    CompetitionRegistration registration = new CompetitionRegistration(name, division);
    this.registrationRepository.save(registration);
    competition.addRegistration(registration);
    
    this.competitionRepository.save(competition);
  }

  /**
   * Adds a new event to the competition.
   */
  public Event addEvent(Competition competition, String eventName) {
    Event event = new Event(eventName);
    event = this.eventRepository.save(event);
    competition.addEvent(event);
    
    this.competitionRepository.save(competition);
    return event;
  }
}
