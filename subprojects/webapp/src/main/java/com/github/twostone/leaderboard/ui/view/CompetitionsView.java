package com.github.twostone.leaderboard.ui.view;

import com.github.twostone.leaderboard.model.competition.Competition;
import com.github.twostone.leaderboard.model.competition.CompetitionRepository;
import com.github.twostone.leaderboard.ui.controller.CompetitionsController;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.ListContainer;
import org.vaadin.viritin.fields.MTable;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;

@SuppressWarnings("serial")
@SpringView(name = CompetitionsView.VIEW_NAME)
public class CompetitionsView extends VerticalLayout implements View {

  public static final String VIEW_NAME = "competitions";

  private MTable<Competition> table;
  private Button newCompetitionButton;

  @Autowired
  private CompetitionsController controller;

  @Autowired
  private CompetitionRepository repository;

  CompetitionsView() {
    super();
  }

  @Override
  public void enter(ViewChangeEvent event) {}

  @PostConstruct
  void init() {
    this.setMargin(true);

    this.table = new MTable<>(Competition.class).withProperties("name", "date")
        .withColumnHeaders("Name", "Date").withFullWidth().withIcon(FontAwesome.DATABASE)
        .withCaption("<h3>Competitions</h3>");
    this.table.setCaptionAsHtml(true);
    this.table.addGeneratedColumn("name", this::createCompetitionLinkButton);

    new PagingRepostioryAdapter<>(this.repository, this.table);

    this.newCompetitionButton = new Button("New competition", FontAwesome.PLUS);
    this.newCompetitionButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
    this.newCompetitionButton.addClickListener(event -> CompetitionsView.this.getUI().getNavigator()
        .navigateTo(CreateCompetitionView.VIEW_NAME));

    HorizontalLayout buttonContainer = new HorizontalLayout();
    buttonContainer.setWidth(100, Unit.PERCENTAGE);
    buttonContainer.addComponent(this.newCompetitionButton);
    buttonContainer.setComponentAlignment(this.newCompetitionButton, Alignment.TOP_RIGHT);

    this.addComponent(buttonContainer);
    this.addComponent(this.table);

    this.controller.setView(this);
  }

  private Object createCompetitionLinkButton(Table source, Object itemId, Object columnId) {
    @SuppressWarnings("unchecked")
    ListContainer<Competition>.DynaBeanItem<Competition> item =
        (ListContainer<Competition>.DynaBeanItem<Competition>) source.getItem(itemId);

    Button button = new Button(item.getBean().getName());
    button.setStyleName(ValoTheme.BUTTON_LINK);
    button.addClickListener(new ClickListener() {

      @Override
      public void buttonClick(ClickEvent event) {
        CompetitionsView.this.getUI().getNavigator()
            .navigateTo("competition/" + item.getBean().getId());
      }
    });
    return button;
  }

  /**
   * EventHandler for new competitions.
   */
  public void onCompetitionCreated(Competition competition) {
    this.table.refreshRowCache();
    Notification.show(MessageFormat.format("Competition \"{1}\" created.", competition.getName()),
        Notification.Type.TRAY_NOTIFICATION);
  }
}
