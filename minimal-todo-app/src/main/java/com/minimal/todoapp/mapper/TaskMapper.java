package com.minimal.todoapp.mapper;

import com.minimal.todoapp.dto.TaskDtoInput;
import com.minimal.todoapp.dto.TaskDtoOutput;
import com.minimal.todoapp.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskMapper {

	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

	TaskDtoOutput convertToTaskDtoOutput(Task task);

	Task convertToTask(TaskDtoInput taskDtoInput);

	List<TaskDtoOutput> convertToListTaskDto(List<Task> tasks);

}
