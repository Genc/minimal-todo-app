package com.minimal.todoapp.security;

import com.minimal.todoapp.dto.AuthenticatedUserDto;
import com.minimal.todoapp.dto.LoginRequest;
import com.minimal.todoapp.dto.LoginResponse;
import com.minimal.todoapp.entity.User;
import com.minimal.todoapp.mapper.UserMapper;
import com.minimal.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Service
@RequiredArgsConstructor
public class JwtTokenService {

	private final UserService userService;

	private final JwtTokenManager jwtTokenManager;

	private final AuthenticationManager authenticationManager;

	public LoginResponse getLoginResponse(LoginRequest loginRequest) {

		final String username = loginRequest.getUsername();
		final String password = loginRequest.getPassword();

		final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		final AuthenticatedUserDto authenticatedUserDto = userService.findAuthenticatedUserByUsername(username);

		final User user = UserMapper.INSTANCE.convertToUser(authenticatedUserDto);

		final String name = user.getName();
		final String token = jwtTokenManager.generateToken(user);

		return LoginResponse.builder().name(name).token(token).build();
	}

}
