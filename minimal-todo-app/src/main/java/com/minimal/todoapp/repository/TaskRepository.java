package com.minimal.todoapp.repository;

import com.minimal.todoapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created on Ağustos, 2020
 *
 * @author Faruk
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

	List<Task> getTasksByUserId(Long userId);

}
