package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.base.AbstractEntity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@SuppressWarnings("serial")
public class EventType extends AbstractEntity {
  
  @JsonFormat(shape = Shape.STRING)
  public enum Direction {
    ASCENDING {
      @Override
      public org.springframework.data.domain.Sort.Direction getDirection() {
        return org.springframework.data.domain.Sort.Direction.ASC;
      }
    },
    DESCENDING {
      @Override
      public org.springframework.data.domain.Sort.Direction getDirection() {
        return org.springframework.data.domain.Sort.Direction.DESC;
      }
    };
    
    public abstract org.springframework.data.domain.Sort.Direction getDirection();
  }
  
  private String name;
  @Enumerated(EnumType.ORDINAL)
  private Direction direction;

  /**
   * Creates a new event type.
   */
  public EventType(String name, Direction direction) {
    super();
    this.name = name;
    this.direction = direction;
  }

  EventType() {
    super();
  }

  public String getName() {
    return this.name;
  }
  
  public Direction getOrdering() {
    return this.direction;
  }
}
