package com.minimal.todoapp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Aspect
@Component
public class LoginOperationsLogging {

	@After("execution(* com.minimal.todoapp.security.JwtTokenService.*(..))")
	public void afterJwtTokenServiceOperations(JoinPoint joinPoint) {

		final String arguments = Arrays.toString(joinPoint.getArgs());
		final Signature methodSignature = joinPoint.getSignature();

		log.info("Invoked ---> " + methodSignature + " with this arguments --> " + arguments);
	}

}
