package com.github.twostone.leaderboard.model.heat;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.event.Event;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
public class HeatPlan extends AbstractEntity {

  @NonNull
  @OneToMany(cascade = CascadeType.ALL)
  @OrderColumn
  private List<Heat> heats;

  private int heatSize;

  @NonNull
  @OneToOne
  private Event event;

  @SuppressWarnings("unused")
  private HeatPlan() {
    super();
  }
}
