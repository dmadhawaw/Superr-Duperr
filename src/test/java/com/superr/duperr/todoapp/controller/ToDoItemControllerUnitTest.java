package com.superr.duperr.todoapp.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.superr.duperr.todolistapp.controller.ToDoItemController;
import com.superr.duperr.todolistapp.domain.ToDoItem;
import com.superr.duperr.todolistapp.domain.ToDoItemWork;
import com.superr.duperr.todolistapp.dto.ToDoItemDto;
import com.superr.duperr.todolistapp.dto.ToDoItemWorkDto;
import com.superr.duperr.todolistapp.service.ToDoItemService;

@ExtendWith(MockitoExtension.class)
public class ToDoItemControllerUnitTest {

	@Mock
	private ToDoItemService todoItemService;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private ToDoItemController totoItemController;

	@BeforeEach
	void setMockOutput() {
		mockMvc = MockMvcBuilders.standaloneSetup(totoItemController).build();

	}

	/*
	 * Test method for 'getToDoItem' and JSON content contains the specific todoItem
	 * details for given todoId.
	 */
	@Test
	void whenGetToDoItemById_returnJsonContent() throws Exception {
		ToDoItem todoItem = new ToDoItem();
		todoItem.setTodoId(Long.valueOf(111));
		todoItem.setTask("TestTast1");
		todoItem.setStatus("Pending");
		todoItem.setDateCreated(new Date());

		ToDoItemDto workDto = new ToDoItemDto(todoItem);

		when(todoItemService.getToDoItem(Long.valueOf(111))).thenReturn(workDto);

		this.mockMvc.perform(get("/todoItems/111")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TestTast1"))).andDo(print());
	}

	/*
	 * Test method for 'getToDoItem' and JSON content contains the specific todoItem
	 * details for given todoId.
	 */
	@Test
	void whenGetToDoItemWorkById_returnJsonContent() throws Exception {
		ToDoItemWork todoItem = new ToDoItemWork();
		todoItem.setTodoId(Long.valueOf(111));
		todoItem.setTask("TestTast1");
		todoItem.setStatus("Pending");
		todoItem.setPriority("HIGH");
		todoItem.setDateCreated(new Date());

		ToDoItemWorkDto workDto = new ToDoItemWorkDto(todoItem);

		when(todoItemService.getToDoItemWork(Long.valueOf(111))).thenReturn(workDto);

		this.mockMvc.perform(get("/workTodoItems/111")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TestTast1"))).andDo(print());
	}

	/*
	 * Test method for 'getAllToDoItems' and JSON content contains details.
	 */
	@Test
	void whenGetAllToDoItems_returnJsonContent() throws Exception {

		ToDoItem itemToDoOne = new ToDoItem();
		itemToDoOne.setTodoId(Long.valueOf(111));
		itemToDoOne.setTask("TestTast1");
		itemToDoOne.setStatus("Pending");
		itemToDoOne.setDateCreated(new Date());

		ToDoItem itemToDoTwo = new ToDoItem();
		itemToDoTwo.setTodoId(Long.valueOf(122));
		itemToDoTwo.setTask("TestTast2");
		itemToDoTwo.setStatus("Completed");
		itemToDoTwo.setDateCreated(new Date());

		ToDoItem itemToDoThree = new ToDoItem();
		itemToDoThree.setTodoId(Long.valueOf(133));
		itemToDoThree.setTask("TestTast3");
		itemToDoThree.setStatus("Inactive");
		itemToDoThree.setDateCreated(new Date());

		List<ToDoItem> doItems = new ArrayList<>();
		doItems.add(itemToDoOne);
		doItems.add(itemToDoTwo);
		doItems.add(itemToDoThree);

		List<ToDoItemDto> dtoItems = new ArrayList<>();
		dtoItems.add(new ToDoItemDto(itemToDoOne));
		dtoItems.add(new ToDoItemDto(itemToDoTwo));
		dtoItems.add(new ToDoItemDto(itemToDoThree));

		when(todoItemService.getAllToDoItems()).thenReturn(dtoItems);

		this.mockMvc.perform(get("/todoItems")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Inactive"))).andDo(print());

		this.mockMvc.perform(get("/todoItems")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TestTast3"))).andDo(print());

		this.mockMvc.perform(get("/todoItems")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TestTast1"))).andDo(print());

	}

	/*
	 * Test method for 'getAllToDoWORKItems' and JSON content contains details.
	 */
	@Test
	void whenGetAllToDoWORKItems_returnJsonContent() throws Exception {

		ToDoItemWork itemToDoOne = new ToDoItemWork();
		itemToDoOne.setTodoId(Long.valueOf(111));
		itemToDoOne.setTask("TestTast1");
		itemToDoOne.setStatus("Pending");
		itemToDoOne.setPriority("Low");
		itemToDoOne.setDateCreated(new Date());

		ToDoItemWork itemToDoTwo = new ToDoItemWork();
		itemToDoTwo.setTodoId(Long.valueOf(122));
		itemToDoTwo.setTask("TestTast2");
		itemToDoTwo.setStatus("Completed");
		itemToDoOne.setPriority("M");
		itemToDoTwo.setDateCreated(new Date());

		ToDoItemWork itemToDoThree = new ToDoItemWork();
		itemToDoThree.setTodoId(Long.valueOf(133));
		itemToDoThree.setTask("TestTast3");
		itemToDoThree.setStatus("Inactive");
		itemToDoOne.setPriority("Low");
		itemToDoThree.setDateCreated(new Date());

		List<ToDoItemWork> doItems = new ArrayList<>();
		doItems.add(itemToDoOne);
		doItems.add(itemToDoTwo);
		doItems.add(itemToDoThree);

		List<ToDoItemWorkDto> dtoItems = new ArrayList<>();
		dtoItems.add(new ToDoItemWorkDto(itemToDoOne));
		dtoItems.add(new ToDoItemWorkDto(itemToDoTwo));
		dtoItems.add(new ToDoItemWorkDto(itemToDoThree));

		when(todoItemService.getAllWorkToDoItems()).thenReturn(dtoItems);

		this.mockMvc.perform(get("/workTodoItems")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Inactive"))).andDo(print());

		this.mockMvc.perform(get("/workTodoItems")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TestTast3"))).andDo(print());

		this.mockMvc.perform(get("/workTodoItems")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Low"))).andDo(print());

	}

}
