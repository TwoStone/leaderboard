package com.github.twostone.leaderboard.model.competition;

import static org.exparity.hamcrest.date.LocalDateMatchers.isDay;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

import com.github.twostone.leaderboard.model.athlete.Athlete;
import com.github.twostone.leaderboard.model.event.Event;
import com.github.twostone.leaderboard.model.event.EventType;
import com.github.twostone.leaderboard.model.event.Result;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.time.Month;

public class CompetitionTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  private Competition competition;

  /**
   * Setup.
   */
  @Before
  public void setUp() {
    LocalDate date = LocalDate.of(2015, 12, 31);
    this.competition = new Competition("Competition 1", date);

  }

  @Test
  public void testConstruction() {

    assertThat(competition.getName(), is("Competition 1"));
    assertThat(competition.getDate(), isDay(2015, Month.DECEMBER, 31));
  }

  @Test
  public void testSetName() {
    competition.setName("New Name");

    assertThat(competition.getName(), is("New Name"));
  }

  @Test
  public void testSetDate() {
    competition.setDate(LocalDate.of(2016, Month.APRIL, 1));

    assertThat(competition.getDate(), isDay(2016, Month.APRIL, 1));
  }

  @Test
  public void testAddDivision() {
    Division eliteMale = new Division("Elite Male");
    competition.addDivision(eliteMale);

    assertThat(competition.getDivisions(), hasItem(eliteMale));
  }

  @Test
  public void testAddDivisionTwice() {
    exception.expect(RuntimeException.class);
    exception.expectMessage("has already been added to the competition");

    Division eliteMale = new Division("Elite Male");
    competition.addDivision(eliteMale);
    competition.addDivision(eliteMale);
  }

  @Test
  public void testRemoveDivision() {
    Division eliteMale = new Division("Elite Male");
    competition.addDivision(eliteMale);
    competition.removeDivision(eliteMale);

    assertThat(competition.getDivisions(), is(empty()));
  }

  @Test
  public void testRemoveDivisionWithAthlete() {
    exception.expect(RuntimeException.class);
    exception.expectMessage("there are already athletes registered for");

    Division eliteMale = new Division("Elite Male");
    competition.addDivision(eliteMale);

    Athlete athlete = new Athlete("Rich", "Froning", LocalDate.of(1987, 7, 21));
    competition.registerAthlete(athlete, eliteMale);

    competition.removeDivision(eliteMale);
  }

  @Test
  public void testRegisterAthlete() {
    Division eliteMale = new Division("Elite Male");
    competition.addDivision(eliteMale);

    Athlete athlete = new Athlete("Rich", "Froning", LocalDate.of(1987, 7, 21));
    CompetitionParticipation registration = competition.registerAthlete(athlete, eliteMale);

    assertThat(competition.getParticipations(), hasItem(registration));
    assertThat(registration, is(notNullValue()));
    assertThat(registration.getDivision(), is(eliteMale));
    assertThat(registration.getParticipant(), is(athlete));
    assertThat(registration.getCompetition(), is(competition));
  }

  @Test
  public void testRegisterAthleteTwice() {
    exception.expect(AthleteAlreadyRegisteredException.class);
    exception.expectMessage("is already registered for the competition");

    Division eliteMale = new Division("Elite Male");
    Division mastersMale = new Division("Masters Male");
    competition.addDivision(eliteMale);

    Athlete athlete = new Athlete("Rich", "Froning", LocalDate.of(1987, 7, 21));
    competition.registerAthlete(athlete, eliteMale);
    competition.registerAthlete(athlete, mastersMale);
  }

  @Test
  public void testRegisterAthleteForWrongDivision() {
    exception.expect(RuntimeException.class);
    exception.expectMessage("because the division does not belong to the competition");

    Division eliteMale = new Division("Elite Male");

    Athlete athlete = new Athlete("Rich", "Froning", LocalDate.of(1987, 7, 21));
    competition.registerAthlete(athlete, eliteMale);
  }

  @Test
  public void testAddEvent() {
    Event event = new Event("Event 1", new StubEventType());
    this.competition.addEvent(event);

    assertThat(competition.getEvents(), hasSize(1));
    assertThat(competition.getEvents(), hasItem(event));
  }

  @Test
  public void testRemoveEvent() {
    Event event = new Event("Event 1", new StubEventType());
    this.competition.addEvent(event);

    this.competition.removeEvent(event);

    assertThat(competition.getEvents(), is(empty()));
  }

  private static class StubEventType extends EventType {

    private static final long serialVersionUID = 1L;

    @Override
    public String serialize(Result result) {
      return null;
    }

    @Override
    public Result deserialize(String resultString) {
      return null;
    }

  }

}
