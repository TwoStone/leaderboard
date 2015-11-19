package com.github.twostone.leaderboard.model.event;

import com.github.twostone.leaderboard.model.AbstractEntity;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Result
 *
 */
@Entity
@Inheritance
public abstract class Result extends AbstractEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Result() {
		super();
	}
}
