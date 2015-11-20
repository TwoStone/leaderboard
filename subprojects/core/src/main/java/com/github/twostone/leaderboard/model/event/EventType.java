package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Participation;

import java.util.Comparator;

import javax.persistence.Entity;

@Entity
public class EventType extends AbstractEntity {

  public enum Ordering {
    ASCENDING {

      @Override
      public Comparator<Result> getComparator() {
        // TODO Auto-generated method stub
        return null;
      }
    },
    DESCENDING {

      @Override
      public Comparator<Result> getComparator() {
        // TODO Auto-generated method stub
        return null;
      }
    };

    public abstract Comparator<Result> getComparator();
  }

  private static final long serialVersionUID = 1L;

  private Ordering ordering;

  protected EventType() {
    super();
  }

  public EventType(Ordering ordering) {
    super();
    this.ordering = ordering;
  }



  public Ordering getOrdering() {
    return this.ordering;
  }

  public void setOrdering(Ordering ordering) {
    this.ordering = ordering;
  }

  public Result createEmptyResult(Participation participant) {
    return new Result(participant);
  }


}
