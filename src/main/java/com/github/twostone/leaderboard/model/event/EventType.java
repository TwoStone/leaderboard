package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.score.Score;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.google.common.collect.Ordering;

@JsonFormat(shape = Shape.NUMBER_INT)
public enum EventType {
  
  FOR_TIME {
    @Override
    public Ordering<Score> getOrdering() {
      return Ordering.natural();
    }
  },
  FOR_POINTS {
    @Override
    public Ordering<Score> getOrdering() {
      return Ordering.natural().reverse();
    }
  };
      
  public abstract Ordering<Score> getOrdering();
}
