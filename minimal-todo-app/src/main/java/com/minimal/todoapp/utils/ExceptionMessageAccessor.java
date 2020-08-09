package com.minimal.todoapp.utils;

import com.minimal.todoapp.constants.ProjectsConstants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Service
public class ExceptionMessageAccessor {

	private final MessageSource messageSource;

	ExceptionMessageAccessor(@Qualifier("exceptionMessageSource") MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key, Object... parameter) {

		return messageSource.getMessage(key, parameter, ProjectsConstants.TURKISH_LOCALE);
	}
}
