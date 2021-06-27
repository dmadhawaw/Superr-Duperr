package com.superr.duperr.todoapp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.superr.duperr.todolistapp.controller.ToDoItemController;
import com.superr.duperr.todolistapp.dto.ToDoItemDto;
import com.superr.duperr.todolistapp.dto.ToDoItemWorkDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ToDoItemControllerIntegrationTest {

	@Autowired
	private ToDoItemController todoController;

	@Test
	public void testGetToDoItemByIdValid() {
		// GET a toDoItem via the controller.
		ResponseEntity<?> dtoToDoItem = todoController.getToDoItem(Long.valueOf(101));
		ToDoItemDto todoItemDto = (ToDoItemDto) dtoToDoItem.getBody();
		// Assert THAT the outcome is as expected
		assertThat(todoItemDto.getStatus(), is(equalTo("Pending")));
		assertThat(todoItemDto.getTodoId(), is(equalTo(Long.valueOf(101))));
	}

	@Test
	public void testGetToDoItemWorkByIdValid() {
		// GET a ToDoItemWork via the controller.
		ResponseEntity<?> todoDto = todoController.getToDoItemWork(Long.valueOf(101));
		ToDoItemWorkDto todoItemWorkDto = (ToDoItemWorkDto) todoDto.getBody();
		// Assert THAT the outcome is as expected
		assertThat(todoItemWorkDto.getStatus(), is(equalTo("Pending")));
		assertThat(todoItemWorkDto.getPriority(), is(equalTo("LOW")));
	}

	@Test
	public void testGetAllToDoItemWorks() {
		// GET all ToDoItemWork via the controller.
		ResponseEntity<?> todoItemWorks = todoController.getAllWorkToDoItems();
		List<ToDoItemWorkDto> todoItemWorkDtos = (List<ToDoItemWorkDto>) todoItemWorks.getBody();
		// Assert THAT the outcome is as expected
		assertThat(todoItemWorkDtos.size(), is(equalTo(2)));
	}

}
