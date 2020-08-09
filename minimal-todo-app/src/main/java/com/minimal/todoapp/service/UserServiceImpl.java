package com.minimal.todoapp.service;

import com.minimal.todoapp.dto.AuthenticatedUserDto;
import com.minimal.todoapp.dto.RegistrationRequest;
import com.minimal.todoapp.dto.RegistrationResponse;
import com.minimal.todoapp.entity.User;
import com.minimal.todoapp.entity.UserRole;
import com.minimal.todoapp.mapper.UserMapper;
import com.minimal.todoapp.repository.UserRepository;
import com.minimal.todoapp.utils.GeneralMessageAccessor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private static final String REGISTRATION_SUCCESSFUL = "registration_successful";

	private final UserRepository userRepository;

	private final UserValidationService userValidationService;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	private final GeneralMessageAccessor generalMessageAccessor;

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	@Transactional
	public RegistrationResponse registration(RegistrationRequest registrationRequest) {

		userValidationService.validateUser(registrationRequest);

		final User user = UserMapper.INSTANCE.convertToUser(registrationRequest);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setUserRole(UserRole.USER);

		userRepository.save(user);

		final String username = registrationRequest.getUsername();
		final String registrationSuccessMessage = generalMessageAccessor.getMessage(REGISTRATION_SUCCESSFUL, username);

		return new RegistrationResponse(registrationSuccessMessage);
	}

	@Override
	public AuthenticatedUserDto findAuthenticatedUserByUsername(String username) {

		final User user = findByUsername(username);

		return UserMapper.INSTANCE.convertToAuthenticatedUserDto(user);
	}

}
