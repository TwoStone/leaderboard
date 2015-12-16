package com.github.twostone.leaderboard.model.athlete;

import com.github.twostone.leaderboard.model.AbstractEntity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Entity implementation class for Entity: Athlete.
 */
@Entity
public class Athlete extends AbstractEntity {

  private static final long serialVersionUID = 1L;

  private String firstname;
  private String lastname;
  private LocalDate birthday;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  protected Athlete() {
    super();
  }

  /**
   * Creates a new {@link Athlete} Object.
   * 
   * @param gender TODO
   */
  public Athlete(String firstname, String lastname, Gender gender, LocalDate birthday) {
    this();
    this.firstname = firstname;
    this.lastname = lastname;
    this.gender = gender;
    this.birthday = birthday;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public LocalDate getBirthday() {
    return this.birthday;
  }

  public void setBirthday(LocalDate birthday) {
    this.birthday = birthday;
  }

  public Gender getGender() {
    return this.gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  @Override
  public String toString() {
    return String.format("%s, %s", this.lastname, this.firstname);
  }

}
