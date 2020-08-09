package com.minimal.todoapp.controller;

import com.minimal.todoapp.dto.TaskDtoInput;
import com.minimal.todoapp.dto.TaskDtoOutput;
import com.minimal.todoapp.dto.TaskUpdateInput;
import com.minimal.todoapp.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/tasks")
@RequiredArgsConstructor
public class TaskController {

	private final TaskService taskService;

	@GetMapping("/{id}")
	public ResponseEntity<TaskDtoOutput> getTaskById(@PathVariable(value = "id") Long id) {

		final TaskDtoOutput taskDtoOutput = taskService.findById(id);

		return ResponseEntity.ok(taskDtoOutput);
	}

	@GetMapping
	public ResponseEntity<Collection<TaskDtoOutput>> getAllTasks() {

		final List<TaskDtoOutput> taskDtoOutputs = taskService.getAllTasksByAuthenticatedUser();

		return ResponseEntity.ok(taskDtoOutputs);
	}

	@PostMapping
	public ResponseEntity<TaskDtoOutput> createTask(@Valid @RequestBody TaskDtoInput taskDtoInput) {

		final TaskDtoOutput taskDtoOutput = taskService.create(taskDtoInput);

		return ResponseEntity.status(HttpStatus.CREATED).body(taskDtoOutput);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaskDtoOutput> updateTask(@PathVariable(value = "id") Long id, @Valid @RequestBody TaskUpdateInput taskUpdateInput) {

		final TaskDtoOutput updatedTask = taskService.update(id, taskUpdateInput);

		return ResponseEntity.ok(updatedTask);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteTaskById(@PathVariable(value = "id") Long id) {

		final boolean deleted = taskService.delete(id);

		return ResponseEntity.ok(deleted);
	}

}
