package com.superr.duperr.todoapp.service;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.superr.duperr.todolistapp.data.repository.ToDoItemRepository;
import com.superr.duperr.todolistapp.domain.ToDoItem;
import com.superr.duperr.todolistapp.domain.ToDoItemWork;
import com.superr.duperr.todolistapp.dto.ToDoItemDto;
import com.superr.duperr.todolistapp.dto.ToDoItemWorkDto;
import com.superr.duperr.todolistapp.service.ToDoItemService;

@ExtendWith(MockitoExtension.class)
public class ToDoItemServiceUnitTest {

	@Mock
	private ToDoItemRepository<ToDoItem> toDoItemRepository;

	@Mock
	private ToDoItemRepository<ToDoItemWork> workToDoItemRepository;

	@InjectMocks
	private ToDoItemService todoItemService;

	@BeforeEach
	void setMockOutput() {

	}

	@Test
	public void testGetToDoItemById() {
		ToDoItem itemToDoOne = new ToDoItem();
		itemToDoOne.setTodoId(Long.valueOf(111));
		itemToDoOne.setTask("TestTast1");
		itemToDoOne.setStatus("Pending");
		itemToDoOne.setDateCreated(new Date());

		Optional<ToDoItem> optToDoItem = Optional.ofNullable(itemToDoOne);
		when(toDoItemRepository.findById(Long.valueOf(100))).thenReturn(optToDoItem);

		ToDoItemDto todoResponseDto = todoItemService.getToDoItem(Long.valueOf(100));
		assertEquals("TestTast1", todoResponseDto.getTask());
		assertEquals("Pending", todoResponseDto.getStatus());
	}

	@Test
	public void testGetAllToDoItems() {
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

		when(toDoItemRepository.findAll()).thenReturn(doItems);

		// Get all ToDoItems
		List<ToDoItemDto> dtos = todoItemService.getAllToDoItems();

		// Verify the getAllToDoItems
		assertEquals(3, dtos.size());
	}

	@Test
	public void testAddToDoItemWork() {
		ToDoItemWork workToDoObj = new ToDoItemWork();
		workToDoObj.setTodoId(Long.valueOf(111));
		workToDoObj.setTask("TestAddNewItem");
		workToDoObj.setPriority("Low");
		workToDoObj.setStatus("Pending");
		workToDoObj.setDateCreated(new Date());

		ToDoItemWorkDto workDto = new ToDoItemWorkDto(workToDoObj);
		when(workToDoItemRepository.save(any(ToDoItemWork.class))).thenReturn(workToDoObj);

		// Save the ToDoItem
		ToDoItemWork newDto = todoItemService.addToDoItem(workDto);

		// Verify the save
		assertEquals("TestAddNewItem", newDto.getTask());
		assertEquals("Low", newDto.getPriority());
		assertEquals("Pending", newDto.getStatus());

	}

}
