package com.superr.duperr.todolistapp.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.superr.duperr.todolistapp.domain.ToDoItem;
import com.superr.duperr.todolistapp.domain.ToDoItemWork;

public interface ToDoItemRepository<T extends ToDoItem> extends JpaRepository<T, Long> {
	@Query("from ToDoItemWork")
	List<ToDoItemWork> findAllToDoItemWorks();
}
