package com.github.twostone.leaderboard.model;

import static com.spencerwi.hamcrestJDK8Time.matchers.IsAfter.after;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import static org.junit.Assert.assertThat;

import com.github.twostone.leaderboard.model.athlete.Athlete;
import com.github.twostone.leaderboard.model.athlete.AthleteRepository;
import com.github.twostone.leaderboard.model.athlete.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelConfiguration.class)
@WebAppConfiguration
public class AbstractEntityTest {

  @Autowired
  private AthleteRepository repository;

  @Test
  public void testAutodId() {
    Athlete entity = this.repository
        .save(new Athlete("Test", "User", Gender.MALE, LocalDate.of(2000, Month.JANUARY, 1)));

    assertThat(entity.getId(), is(not(isEmptyOrNullString())));
  }

  @Test
  public void testCreateDate() {
    Athlete entity = this.repository
        .save(new Athlete("Test", "User", Gender.MALE, LocalDate.of(2000, Month.JANUARY, 1)));

    assertThat(entity.getCreatedAt(), is(not(nullValue())));
  }

  @Test
  public void testUpdateDate() {
    Athlete entity = this.repository
        .save(new Athlete("Test", "User", Gender.MALE, LocalDate.of(2000, Month.JANUARY, 1)));

    assertThat(entity.getUpdatedAt(), is(not(nullValue())));

    Instant updatedAt = entity.getUpdatedAt();
    entity.setFirstname("Test2");
    entity = this.repository.save(entity);
    assertThat(entity.getUpdatedAt(), is(after(updatedAt)));
  }
}
