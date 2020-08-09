package com.minimal.todoapp.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
public class TaskOperationsLogging {

	@Before("execution(* com.minimal.todoapp.service.TaskService.*(..))")
	public void beforeTaskServiceOperations(JoinPoint joinPoint) {

		final String arguments = Arrays.toString(joinPoint.getArgs());
		final Signature methodSignature = joinPoint.getSignature();

		log.info("Invoked ---> " + methodSignature + " with this arguments --> " + arguments);
	}
}
