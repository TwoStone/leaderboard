package com.github.twostone.leaderboard;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.twostone.leaderboard.model.athlete.Athlete;
import com.github.twostone.leaderboard.model.athlete.AthleteRepository;
import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventType;
import com.github.twostone.leaderboard.model.event.EventType.Ordering;

@SpringBootApplication
public class LeaderboardApplication {

  @Bean
  public CommandLineRunner demo(CompetitionRepository competitionRepository,
      AthleteRepository athleteRepository) {
    return (args) -> {
      Athlete athlete =
          athleteRepository.save(new Athlete("Niklas", "Walter", LocalDate.of(1987, 12, 5)));

      Athlete rich = athleteRepository.save(new Athlete("Rich", "Froning", LocalDate.now()));

      Competition competition =
          competitionRepository.save(new Competition("Comp 1", LocalDate.of(2015, 12, 24)));
      Division division = competition.addDivision(new Division("Elite male"));

      competition.registerAthlete(athlete, division);
      competition.registerAthlete(rich, division);

      Event event = new Event("WOD 1", new EventType(Ordering.ASCENDING));
      competition.addEvent(event);

      competitionRepository.save(competition);
    };
  }
}
