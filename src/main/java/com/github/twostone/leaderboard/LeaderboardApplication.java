package com.github.twostone.leaderboard;

import com.github.twostone.leaderboard.model.ModelConfiguration;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ModelConfiguration.class)
public class LeaderboardApplication {
  
}
