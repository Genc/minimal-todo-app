package com.minimal.todoapp.service;

import com.minimal.todoapp.dto.TaskDtoInput;
import com.minimal.todoapp.dto.TaskDtoOutput;
import com.minimal.todoapp.dto.TaskUpdateInput;

import java.util.List;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface TaskService {

	boolean delete(Long id);

	TaskDtoOutput findById(Long id);

	TaskDtoOutput create(TaskDtoInput taskDtoInput);

	TaskDtoOutput update(Long id, TaskUpdateInput taskUpdateInput);

	List<TaskDtoOutput> getAllTasksByAuthenticatedUser();

}
