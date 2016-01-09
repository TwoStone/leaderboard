package com.github.twostone.leaderboard.model.competition;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CompetitionManager {
 
  private CompetitionRepository competitionRepository;
  private DivisionRespository divisionRepository;
  private RegistrationRepository registrationRepository;
  
  @Inject
  CompetitionManager(CompetitionRepository competitionRepository, 
      DivisionRespository divisionRespository,
      RegistrationRepository registrationRepository) {
    super();
    this.competitionRepository = competitionRepository;
    this.divisionRepository = divisionRespository;
    this.registrationRepository = registrationRepository;
  }
  
  public Competition refresh(Competition oldCompetition) {
    return this.findOne(oldCompetition.getId());
  }
 
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

  public void register(Competition competition, Division division, String name) {
    CompetitionRegistration registration = new CompetitionRegistration(name, division);
    this.registrationRepository.save(registration);
    competition.addRegistration(registration);
    
    this.competitionRepository.save(competition);
  }
}
