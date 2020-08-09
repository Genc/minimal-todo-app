package com.minimal.todoapp.service;

import com.minimal.todoapp.advice.exceptions.TaskNotFoundException;
import com.minimal.todoapp.dto.TaskDtoInput;
import com.minimal.todoapp.dto.TaskDtoOutput;
import com.minimal.todoapp.dto.TaskUpdateInput;
import com.minimal.todoapp.entity.Task;
import com.minimal.todoapp.entity.TaskStatus;
import com.minimal.todoapp.entity.User;
import com.minimal.todoapp.mapper.TaskMapper;
import com.minimal.todoapp.repository.TaskRepository;
import com.minimal.todoapp.security.SecurityUtils;
import com.minimal.todoapp.utils.ExceptionMessageAccessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

	private static final String TASK_NOT_FOUND = "task_not_found";

	private static final String ACCESS_DENIED_MESSAGE = "access_denied_exception";

	private final UserService userService;

	private final TaskRepository taskRepository;

	private final ExceptionMessageAccessor exceptionMessageAccessor;

	@Override
	public boolean delete(Long id) {

		final Task task = findTaskById(id);
		final boolean userHasAuthorityForTaskOperations = isUserHasAuthorityForTaskOperations(task);

		if (userHasAuthorityForTaskOperations) {
			taskRepository.delete(task);
			return true;
		}

		final String accessDeniedExceptionMessage = exceptionMessageAccessor.getMessage(ACCESS_DENIED_MESSAGE);
		throw new AccessDeniedException(accessDeniedExceptionMessage);
	}

	@Override
	public TaskDtoOutput findById(Long id) {

		final Task task = findTaskById(id);
		final boolean userHasAuthorityForTaskOperations = isUserHasAuthorityForTaskOperations(task);

		if (userHasAuthorityForTaskOperations) {
			return TaskMapper.INSTANCE.convertToTaskDtoOutput(task);
		}

		final String accessDeniedExceptionMessage = exceptionMessageAccessor.getMessage(ACCESS_DENIED_MESSAGE);
		throw new AccessDeniedException(accessDeniedExceptionMessage);
	}

	@Override
	public TaskDtoOutput create(TaskDtoInput taskDtoInput) {

		final User authenticatedUser = getAuthenticatedUser();

		final Task task = initialValuesForTask(taskDtoInput);
		task.setUser(authenticatedUser);
		final Task savedTask = taskRepository.save(task);

		return TaskMapper.INSTANCE.convertToTaskDtoOutput(savedTask);
	}

	@Override
	public TaskDtoOutput update(Long id, TaskUpdateInput taskUpdateInput) {

		final Task taskDb = findTaskById(id);
		final boolean userHasAuthorityForTaskOperations = isUserHasAuthorityForTaskOperations(taskDb);

		if (userHasAuthorityForTaskOperations) {

			taskDb.setDescription(taskUpdateInput.getDescription());
			taskDb.setTaskStatus(taskUpdateInput.getTaskStatus());
			taskDb.setUpdatedAt(LocalDateTime.now());

			final Task updatedTask = taskRepository.save(taskDb);

			return TaskMapper.INSTANCE.convertToTaskDtoOutput(updatedTask);
		}

		final String accessDeniedExceptionMessage = exceptionMessageAccessor.getMessage(ACCESS_DENIED_MESSAGE);
		throw new AccessDeniedException(accessDeniedExceptionMessage);
	}

	@Override
	public List<TaskDtoOutput> getAllTasksByAuthenticatedUser() {

		final User authenticatedUser = getAuthenticatedUser();
		final Long userId = authenticatedUser.getId();

		final List<Task> allTasks = taskRepository.getTasksByUserId(userId);

		return TaskMapper.INSTANCE.convertToListTaskDto(allTasks);
	}

	private Task findTaskById(Long id) {

		final Optional<Task> taskOptional = taskRepository.findById(id);

		if (!taskOptional.isPresent()) {

			log.error("Task is not found ---> task id : " + id);

			final String taskNotFoundExceptionMessage = exceptionMessageAccessor.getMessage(TASK_NOT_FOUND, id);
			throw new TaskNotFoundException(taskNotFoundExceptionMessage);
		}

		return taskOptional.get();
	}

	private Task initialValuesForTask(TaskDtoInput taskDtoInput) {

		final Task task = TaskMapper.INSTANCE.convertToTask(taskDtoInput);
		task.setTaskStatus(TaskStatus.TODO);
		task.setCreatedAt(LocalDateTime.now());
		task.setUpdatedAt(LocalDateTime.now());

		return task;
	}

	private User getAuthenticatedUser() {

		final String authenticatedUsername = SecurityUtils.getAuthenticatedUsername();

		return userService.findByUsername(authenticatedUsername);
	}

	private boolean isUserHasAuthorityForTaskOperations(Task task) {

		final User authenticatedUser = getAuthenticatedUser();
		final User user = task.getUser();

		return authenticatedUser.equals(user);
	}

}
