package com.github.twostone.leaderboard.model.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ComponentAccessor implements ApplicationContextAware {

  private static ApplicationContext applicationContext;
  
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ComponentAccessor.applicationContext = applicationContext;
  }
  
  public static <T> T getComponent(Class<T> clz) {
    return applicationContext.getAutowireCapableBeanFactory().getBean(clz);
  }

}
