package com.superr.duperr.todolistapp.repository;

import com.superr.duperr.todolistapp.domain.ToDoItem;
import com.superr.duperr.todolistapp.domain.ToDoItemWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoItemRepository<T extends ToDoItem> extends JpaRepository<T, Long> {
	@Query("from ToDoItemWork")
	List<ToDoItemWork> findAllToDoItemWorks();
}
