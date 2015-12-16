package com.github.twostone.leaderboard.events;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class EventBusSubscriberService implements BeanPostProcessor {

  @Autowired
  private EventBus eventBus;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

    for (Method method : bean.getClass().getMethods()) {
      if (method.getAnnotation(Subscribe.class) != null) {
        this.eventBus.register(beanName);
        break;
      }
    }

    return bean;
  }

}
