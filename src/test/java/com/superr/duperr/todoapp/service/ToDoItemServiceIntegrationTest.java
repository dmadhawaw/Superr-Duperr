package com.superr.duperr.todoapp.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.superr.duperr.todolistapp.dto.ToDoItemDto;
import com.superr.duperr.todolistapp.dto.ToDoItemWorkDto;
import com.superr.duperr.todolistapp.service.ToDoItemService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ToDoItemServiceIntegrationTest {

	@Autowired
	private ToDoItemService todoItemService;

	@Test
	public void testGetToDoItemById() {

		ToDoItemDto itemDtoResponse = todoItemService.getToDoItem(Long.valueOf(101));
		
		// Verify get ToDoItem by Id
		assertThat(itemDtoResponse.getStatus(), is(equalTo("Pending")));
		assertThat(itemDtoResponse.getTask(), is(equalTo("Pay Bill")));
	}

	@Test
	public void testGetToDoWorkItemById() {

		ToDoItemWorkDto itemDtoResponse = todoItemService.getToDoItemWork(Long.valueOf(102));
		
		// Verify get ToDoItemWork by Id
		assertThat(itemDtoResponse.getStatus(), is(equalTo("Pending")));
		assertThat(itemDtoResponse.getTask(), is(equalTo("Meeting")));
	}

	@Test
	public void testAllGetToDoWorkItems() {

		// Verify get all ToDoItemWork items.
		List<ToDoItemWorkDto> items = todoItemService.getAllWorkToDoItems();
		assertThat(items, hasSize(5));
	}

}
