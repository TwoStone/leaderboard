package com.github.twostone.leaderboard.model.heat;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;

import com.github.twostone.leaderboard.model.base.AbstractEntity;
import com.github.twostone.leaderboard.model.competition.Competitor;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Heat extends AbstractEntity {

  @NonNull
  @ManyToMany
  @OrderColumn
  private List<Competitor> competitors;

  @SuppressWarnings("unused")
  private Heat() {
  }

}
