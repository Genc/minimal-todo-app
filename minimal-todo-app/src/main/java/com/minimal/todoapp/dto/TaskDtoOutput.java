package com.minimal.todoapp.dto;

import com.minimal.todoapp.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDtoOutput {

	private Long id;

	private String description;

	private TaskStatus taskStatus;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

}
