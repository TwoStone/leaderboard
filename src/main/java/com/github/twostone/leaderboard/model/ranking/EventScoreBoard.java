package com.github.twostone.leaderboard.model.ranking;

import com.github.twostone.leaderboard.model.competition.Division;
import com.github.twostone.leaderboard.model.event.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ListMultimap;

public class EventScoreBoard {

  private Event event;
  @JsonIgnore
  private ListMultimap<Division, RankedEventScore> scores;
  
  public EventScoreBoard(Event event, ListMultimap<Division, RankedEventScore> scores) {
    this.event = event;
    this.scores = scores;
  }  
  
  public ListMultimap<Division, RankedEventScore> getScores() {
    return this.scores;
  }
  
  public Event getEvent() {
    return this.event;
  }
}
