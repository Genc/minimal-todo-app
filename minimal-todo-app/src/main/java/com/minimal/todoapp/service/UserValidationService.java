package com.minimal.todoapp.service;

import com.minimal.todoapp.advice.exceptions.RegistrationException;
import com.minimal.todoapp.dto.RegistrationRequest;
import com.minimal.todoapp.repository.UserRepository;
import com.minimal.todoapp.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Service
@RequiredArgsConstructor
public class UserValidationService {

	private static final String EMAIL_ALREADY_EXISTS = "email_already_exists";

	private static final String USERNAME_ALREADY_EXISTS = "username_already_exists";

	private final ExceptionMessageAccessor exceptionMessageAccessor;

	private final UserRepository userRepository;

	public void validateUser(RegistrationRequest registrationRequest) {

		final String email = registrationRequest.getEmail();
		final String username = registrationRequest.getUsername();

		checkEmail(email);
		checkUsername(username);
	}

	private void checkUsername(String username) {

		final boolean existsByUsername = userRepository.existsByUsername(username);

		if (existsByUsername) {

			final String existsUsername = exceptionMessageAccessor.getMessage(USERNAME_ALREADY_EXISTS);
			throw new RegistrationException(existsUsername);
		}

	}

	private void checkEmail(String email) {

		final boolean existsByEmail = userRepository.existsByEmail(email);

		if (existsByEmail) {

			final String existsEmail = exceptionMessageAccessor.getMessage(EMAIL_ALREADY_EXISTS);
			throw new RegistrationException(existsEmail);
		}
	}

}
