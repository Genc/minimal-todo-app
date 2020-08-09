package com.minimal.todoapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@Builder
public class LoginResponse {

	private String name;

	private String token;

}
