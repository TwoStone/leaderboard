package com.github.twostone.leaderboard.model.competition;

public class NewEventRequest {
  String name;
  String description;
  long typeId;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getTypeId() {
    return this.typeId;
  }

  public void setTypeId(long id) {
    this.typeId = id;
  }
}