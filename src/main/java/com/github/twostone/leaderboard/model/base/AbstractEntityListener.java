package com.github.twostone.leaderboard.model.base;

import com.google.common.eventbus.EventBus;

import javax.persistence.PostUpdate;

public class AbstractEntityListener {

  public class EntityUpdateEvent {
    
    private AbstractEntity entity;

    EntityUpdateEvent(AbstractEntity entity) {
      super();
      this.entity = entity;
    }
    
    public AbstractEntity getEntity() {
      return this.entity;
    }
  }

  @PostUpdate
  public void postUpdate(AbstractEntity entity) {
    EventBus eventBus = ComponentAccessor.getComponent(EventBus.class);
    eventBus.post(new EntityUpdateEvent(entity));
  }
}
