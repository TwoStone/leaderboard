package com.github.twostone.leaderboard;

import com.github.twostone.leaderboard.model.ModelConfiguration;
import com.github.twostone.leaderboard.model.athlete.Athlete;
import com.github.twostone.leaderboard.model.athlete.AthleteRepository;
import com.github.twostone.leaderboard.model.athlete.Gender;
import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.competition.DivisionType;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;

@SpringBootApplication
@Import(ModelConfiguration.class)
public class LeaderboardApplication {

  /**
   * Creates demo data.
   */
  @Bean
  public CommandLineRunner demo(CompetitionRepository competitionRepository,
      AthleteRepository athleteRepository) {
    return (args) -> {
      Athlete athlete = athleteRepository
          .save(new Athlete("Niklas", "Walter", Gender.MALE, LocalDate.of(1987, 12, 5)));

      Athlete rich =
          athleteRepository.save(new Athlete("Rich", "Froning", Gender.MALE, LocalDate.now()));

      Competition competition =
          competitionRepository.save(new Competition("Comp 1", LocalDate.of(2015, 12, 24)));
      Division division =
          competition.addDivision(new Division("Elite male", DivisionType.INDIVIDUAL));

      competition.registerAthlete(athlete, division);
      competition.registerAthlete(rich, division);


      competitionRepository.save(competition);
    };
  }
}
