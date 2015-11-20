package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Participation;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Result.
 */
@Entity
public class Result extends AbstractEntity implements Serializable {


  private static final long serialVersionUID = 1L;

  private int points;

  private Participation participant;

  public Result() {
    super();
  }

  /**
   * Create a new {@link Result} object.
   */
  public Result(Participation participant) {
    super();
    this.participant = participant;
    this.points = -1;
  }

  public Participation getParticipant() {
    return this.participant;
  }

  public int getPoints() {
    return this.points;
  }

  public void setPoints(int points) {
    this.points = points;
  }


}
