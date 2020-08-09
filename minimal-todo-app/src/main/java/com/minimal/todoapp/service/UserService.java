package com.minimal.todoapp.service;

import com.minimal.todoapp.dto.AuthenticatedUserDto;
import com.minimal.todoapp.dto.RegistrationRequest;
import com.minimal.todoapp.dto.RegistrationResponse;
import com.minimal.todoapp.entity.User;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface UserService {

	User findByUsername(String username);

	RegistrationResponse registration(RegistrationRequest registrationRequest);

	AuthenticatedUserDto findAuthenticatedUserByUsername(String username);

}
