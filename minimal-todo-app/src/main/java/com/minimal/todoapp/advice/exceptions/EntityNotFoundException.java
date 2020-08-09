package com.minimal.todoapp.advice.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@RequiredArgsConstructor
public class EntityNotFoundException extends RuntimeException {

	private final String errorMessage;

}

