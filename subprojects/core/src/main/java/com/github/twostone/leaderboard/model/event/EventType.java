package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EventType extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  public abstract String serialize(Result result);

  public abstract Result deserialize(String resultString);

}
