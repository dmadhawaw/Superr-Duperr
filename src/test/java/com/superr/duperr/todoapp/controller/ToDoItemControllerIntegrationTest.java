package com.superr.duperr.todoapp.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.superr.duperr.todolistapp.controller.ToDoItemController;
import com.superr.duperr.todolistapp.domain.ToDoItem;
import com.superr.duperr.todolistapp.dto.ToDoItemDto;
import com.superr.duperr.todolistapp.dto.ToDoItemWorkDto;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ToDoItemControllerIntegrationTest {

	@Autowired
	private ToDoItemController todoController;

	@Test
	public void testGetToDoItemByIdValid() {
		// GET an dtoToDoItem to the controller; check the outcome
		ResponseEntity<?> dtoToDoItem = todoController.getToDoItem(Long.valueOf(101));
		ToDoItem dto = (ToDoItem)dtoToDoItem.getBody();
		// Assert THAT the outcome is as expected
		assertThat(dto.getStatus(), is(equalTo("Pending")));
		assertThat(dto.getTodoId(), is(equalTo(Long.valueOf(101))));
	}

//	@Test
//	public void testGetToDoItemWorkByIdValid() {
//		// GET an dtoToDoItem to the controller; check the outcome
//		ToDoItemWorkDto todoDto = todoController.getToDoItemWork(Long.valueOf(101));
//
//		// Assert THAT the outcome is as expected
//		assertThat(todoDto.getStatus(), is(equalTo("Pending")));
//		assertThat(todoDto.getPriority(), is(equalTo("LOW")));
//	}
//
//	@Test
//	public void testGetAllToDoItemWorks() {
//		// GET an ToDoItemWork to the controller; check the outcome
//		List<ToDoItemWorkDto> transactions = todoController.getAllWorkToDoItems();
//		// Assert THAT the outcome is as expected
//		assertThat(transactions.size(), is(equalTo(2)));
//	}

}
