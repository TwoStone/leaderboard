package com.github.twostone.leaderboard.ui.view;

import com.github.twostone.leaderboard.ui.design.ApplicationDesign;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.VerticalLayout;

public class ApplicationView extends ApplicationDesign {

  private static final long serialVersionUID = -1234502729718857023L;

  @SuppressWarnings("serial")
  public ApplicationView() {
    super();
    
    this.menuTitleLabel.setValue("Leader<strong>board</strong>");
    this.competitionsButton.addClickListener(new ClickListener() {
      
      @Override
      public void buttonClick(ClickEvent event) {
        ApplicationView.this.getUI().getNavigator().navigateTo(CompetitionsView.VIEW_NAME);
      }
    });
  }
  
  public VerticalLayout getContent() {
    return this.content;
  }

}
