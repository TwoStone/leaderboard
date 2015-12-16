package com.github.twostone.leaderboard.model.competition;

import static org.exparity.hamcrest.date.LocalDateMatchers.isDay;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

import com.github.twostone.leaderboard.model.athlete.Athlete;
import com.github.twostone.leaderboard.model.athlete.Gender;
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

    assertThat(this.competition.getName(), is("Competition 1"));
    assertThat(this.competition.getDate(), isDay(2015, Month.DECEMBER, 31));
  }

  @Test
  public void testSetName() {
    this.competition.setName("New Name");

    assertThat(this.competition.getName(), is("New Name"));
  }

  @Test
  public void testSetDate() {
    this.competition.setDate(LocalDate.of(2016, Month.APRIL, 1));

    assertThat(this.competition.getDate(), isDay(2016, Month.APRIL, 1));
  }

  @Test
  public void testAddDivision() {
    Division eliteMale = new Division("Elite Male", DivisionType.INDIVIDUAL);
    this.competition.addDivision(eliteMale);

    assertThat(this.competition.getDivisions(), hasItem(eliteMale));
  }

  @Test
  public void testAddDivisionTwice() {
    this.exception.expect(RuntimeException.class);
    this.exception.expectMessage("has already been added to the competition");

    Division eliteMale = new Division("Elite Male", DivisionType.INDIVIDUAL);
    this.competition.addDivision(eliteMale);
    this.competition.addDivision(eliteMale);
  }

  @Test
  public void testRemoveDivision() {
    Division eliteMale = new Division("Elite Male", DivisionType.INDIVIDUAL);
    this.competition.addDivision(eliteMale);
    this.competition.removeDivision(eliteMale);

    assertThat(this.competition.getDivisions(), is(empty()));
  }

  @Test
  public void testRemoveDivisionWithAthlete() {
    this.exception.expect(RuntimeException.class);
    this.exception.expectMessage("there are already athletes registered for");

    Division eliteMale = new Division("Elite Male", DivisionType.INDIVIDUAL);
    this.competition.addDivision(eliteMale);

    Athlete athlete = new Athlete("Rich", "Froning", Gender.MALE, LocalDate.of(1987, 7, 21));
    this.competition.registerAthlete(athlete, eliteMale);

    this.competition.removeDivision(eliteMale);
  }

  @Test
  public void testRegisterAthlete() {
    Division eliteMale = new Division("Elite Male", DivisionType.INDIVIDUAL);
    this.competition.addDivision(eliteMale);

    Athlete athlete = new Athlete("Rich", "Froning", Gender.MALE, LocalDate.of(1987, 7, 21));
    CompetitionParticipation registration = this.competition.registerAthlete(athlete, eliteMale);

    assertThat(this.competition.getParticipations(), hasItem(registration));
    assertThat(registration, is(notNullValue()));
    assertThat(registration.getDivision(), is(eliteMale));
    assertThat(registration.getParticipant(), is(athlete));
    assertThat(registration.getCompetition(), is(this.competition));
  }

  @Test
  public void testRegisterAthleteTwice() {
    this.exception.expect(AthleteAlreadyRegisteredException.class);
    this.exception.expectMessage("is already registered for the competition");

    Division eliteMale = new Division("Elite Male", DivisionType.INDIVIDUAL);
    Division mastersMale = new Division("Masters Male", DivisionType.INDIVIDUAL);
    this.competition.addDivision(eliteMale);

    Athlete athlete = new Athlete("Rich", "Froning", Gender.MALE, LocalDate.of(1987, 7, 21));
    this.competition.registerAthlete(athlete, eliteMale);
    this.competition.registerAthlete(athlete, mastersMale);
  }

  @Test
  public void testRegisterAthleteForWrongDivision() {
    this.exception.expect(RuntimeException.class);
    this.exception.expectMessage("because the division does not belong to the competition");

    Division eliteMale = new Division("Elite Male", DivisionType.INDIVIDUAL);

    Athlete athlete = new Athlete("Rich", "Froning", Gender.MALE, LocalDate.of(1987, 7, 21));
    this.competition.registerAthlete(athlete, eliteMale);
  }

  @Test
  public void testAddEvent() {
    Event event = new Event("Event 1", new StubEventType());
    this.competition.addEvent(event);

    assertThat(this.competition.getEvents(), hasSize(1));
    assertThat(this.competition.getEvents(), hasItem(event));
  }

  @Test
  public void testRemoveEvent() {
    Event event = new Event("Event 1", new StubEventType());
    this.competition.addEvent(event);

    this.competition.removeEvent(event);

    assertThat(this.competition.getEvents(), is(empty()));
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
