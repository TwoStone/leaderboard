package com.github.twostone.leaderboard.model.competition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegistrationRepository extends JpaRepository<CompetitionRegistration, Long> {

}
