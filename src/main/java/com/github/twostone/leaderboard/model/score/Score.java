package com.github.twostone.leaderboard.model.score;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competitor;
import com.github.twostone.leaderboard.model.event.Event;
import com.google.common.collect.Maps;

@Entity
@SuppressWarnings("serial")
public class Score extends AbstractEntity {

  @ManyToOne
  private Event event;

  @ManyToOne
  private Competitor competitor;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @MapKey(name = "name")
  private Map<String, PartialScore> parts;

  private boolean scaled;
  
  private Score() {
    super();
  }

  /**
   * Creates a new score.
   */
  public Score(Event event, Competitor competitor, boolean scaled) {
    this();
    this.event = event;
    this.competitor = competitor;
    this.scaled = scaled;
    this.parts = Maps.newHashMap();
  }

  @JsonCreator Score(
      @JsonProperty("event") Event event,
      @JsonProperty("competitor") Competitor competitor,
      @JsonProperty("scaled") boolean scaled,
      @JsonProperty("parts") Map<String, PartialScore> parts) {
    this(event, competitor, scaled);
    this.parts = parts;
  }

  public Event getEvent() {
    return this.event;
  }

  public Competitor getCompetitor() {
    return this.competitor;
  }

  public boolean isScaled() {
    return this.scaled;
  }

  public void setParts(Map<String, PartialScore> parts) {
    this.parts = parts;
  }

  public Map<String, PartialScore> getParts() {
    return this.parts;
  }

  public void setScaled(boolean scaled) {
    this.scaled = scaled;
  }

  public boolean isNotSet() {
    return this.parts.isEmpty();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Score [event=").append(this.event).append(", competitor=").append(this.competitor)
        .append(", parts=").append(this.parts).append(", scaled=").append(this.scaled).append(", getId()=")
        .append(this.getId()).append("]");
    return builder.toString();
  }

  public PartialScore getPart(String name) {
    return this.parts.get(name);
  }

}
