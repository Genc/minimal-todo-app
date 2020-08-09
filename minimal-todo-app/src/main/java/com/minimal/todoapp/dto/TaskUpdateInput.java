package com.minimal.todoapp.dto;

import com.minimal.todoapp.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateInput {

	@NotEmpty(message = "{task_description_not_empty}")
	private String description;

	private TaskStatus taskStatus;

}
