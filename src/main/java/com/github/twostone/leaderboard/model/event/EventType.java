package com.github.twostone.leaderboard.model.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

@JsonFormat(shape = Shape.NUMBER_INT)
public enum EventType {
  
  FOR_TIME {
    @Override
    public org.springframework.data.domain.Sort.Direction getDirection() {
      return org.springframework.data.domain.Sort.Direction.ASC;
    }
  },
  FOR_POINTS {
    @Override
    public org.springframework.data.domain.Sort.Direction getDirection() {
      return org.springframework.data.domain.Sort.Direction.DESC;
    }
  };
      
  public abstract org.springframework.data.domain.Sort.Direction getDirection();
}
