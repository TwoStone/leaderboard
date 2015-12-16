package com.github.twostone.leaderboard.ui.view;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.ui.controller.CompetitionsController;
import com.github.twostone.leaderboard.ui.design.CompetitionsDesign;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.event.ShortcutAction.ModifierKey;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;

@SuppressWarnings("serial")
@SpringView(name = CompetitionsView.VIEW_NAME)
public class CompetitionsView extends CompetitionsDesign implements View {

  public static final String VIEW_NAME = "competitions";

  @Autowired
  private CompetitionsController controller;

  @Autowired
  private CompetitionRepository repository;

  CompetitionsView() {
    super();
  }

  @Override
  public void enter(ViewChangeEvent event) {}

  @SuppressWarnings("unchecked")
  @PostConstruct
  void init() {
    this.setMargin(true);

    this.competitionsTable
        .withColumnHeaders("Date", "Name")
        .withProperties("date")
        .withGeneratedColumn("name", this::createCompetitionLinkButton);
    this.competitionsTable.setCaptionAsHtml(true);
    this.competitionsTable.setColumnWidth("date", 150);
    this.competitionsTable.setSortableProperties("date", "name");

    new PagingRepostioryAdapter<>(this.repository, this.competitionsTable);

    this.createButton.addClickListener(event -> CompetitionsView.this.getUI().getNavigator()
        .navigateTo(CreateCompetitionView.VIEW_NAME));
    this.createButton.setClickShortcut(
        KeyCode.C, ModifierKey.ALT); 
    this.controller.setView(this);
  }

  private Object createCompetitionLinkButton(Object object) {
    Competition competition = (Competition) object;
    
    Button button = new Button(competition.getName());
    button.setStyleName(ValoTheme.BUTTON_LINK);
    button.addClickListener(new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        CompetitionsView.this.getUI().getNavigator()
            .navigateTo("competition/" + competition.getId());
      }
    });
    return button;
  }

  /**
   * EventHandler for new competitions.
   */
  public void onCompetitionCreated(Competition competition) {
    this.competitionsTable.refreshRowCache();
    Notification.show(MessageFormat.format("Competition \"{1}\" created.", competition.getName()),
        Notification.Type.TRAY_NOTIFICATION);
  }
}
