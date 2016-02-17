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
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    
    AbstractEntity other = (AbstractEntity) obj;
    if (this.id == null) {
      return false;
    } 
    return this.id.equals(other.id);
  }

}
