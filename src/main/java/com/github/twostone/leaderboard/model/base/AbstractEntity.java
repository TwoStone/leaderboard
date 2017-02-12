package com.github.twostone.leaderboard.model.base;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@EntityListeners(AbstractEntityListener.class)
public abstract class AbstractEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Instant createdAt;
  private Instant updatedAt;

  protected AbstractEntity() {
    super();
  }

  public Long getId() {
    return this.id;
  }

  public Instant getCreatedAt() {
    return this.createdAt;
  }

  public Instant getUpdatedAt() {
    return this.updatedAt;
  }
  
  @JsonIgnore
  @Transient
  public boolean isNew() {
    return this.id != null;
  }

  @PrePersist
  protected void onCreate() {
    this.createdAt = Instant.now();
    this.updatedAt = this.createdAt;
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = Instant.now();
  }

  @Override
  public int hashCode() {
    return this.id != null ? this.id.hashCode() : 42 * this.getClass().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(this.getClass().isAssignableFrom(obj.getClass()) || obj.getClass().isAssignableFrom(this.getClass()))) {
      return false;
    }

    AbstractEntity other = (AbstractEntity) obj;
    if (this.getId() == null) {
      return false;
    }
    return this.getId().equals(other.getId());
  }

}
