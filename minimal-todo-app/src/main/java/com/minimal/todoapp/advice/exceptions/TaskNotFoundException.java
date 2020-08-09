package com.minimal.todoapp.advice.exceptions;

import lombok.Getter;
import lombok.Setter;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
public class TaskNotFoundException extends EntityNotFoundException {

	public TaskNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
