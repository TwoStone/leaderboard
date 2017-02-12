package com.github.twostone.leaderboard.model.score;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.twostone.leaderboard.model.base.AbstractEntity;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class PartialScore extends AbstractEntity implements Comparable<PartialScore> {
  private String name;
  private long value;

  private PartialScore() {
    super();
  }
  
  @JsonCreator
  public PartialScore(
      @JsonProperty("name") String name, 
      @JsonProperty("value") long value) {
    this();
    this.name = name;
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public long getValue() {
    return this.value;
  }
  public void setValue(long value) {
    this.value = value;
  }

  @Override
  public int compareTo(PartialScore o) {
    return Long.compare(this.value, o.value);
  }

}