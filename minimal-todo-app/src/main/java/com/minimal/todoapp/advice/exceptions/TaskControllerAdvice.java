package com.minimal.todoapp.advice.exceptions;

import com.minimal.todoapp.controller.TaskController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@RestControllerAdvice(basePackageClasses = TaskController.class)
public class TaskControllerAdvice {

	@ExceptionHandler(TaskNotFoundException.class)
	ResponseEntity<ApiExceptionResponse> handleTaskNotFoundException(TaskNotFoundException exception) {

		final ApiExceptionResponse response = new ApiExceptionResponse(exception.getErrorMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());

		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@ExceptionHandler(AccessDeniedException.class)
	ResponseEntity<ApiExceptionResponse> handleAccessDeniedException(AccessDeniedException accessDeniedException) {

		final ApiExceptionResponse response = new ApiExceptionResponse(accessDeniedException.getMessage(), HttpStatus.FORBIDDEN, LocalDateTime.now());

		return ResponseEntity.status(response.getStatus()).body(response);
	}

}
