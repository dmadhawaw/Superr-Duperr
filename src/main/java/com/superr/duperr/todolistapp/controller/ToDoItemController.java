package com.superr.duperr.todolistapp.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.superr.duperr.todolistapp.dto.ToDoItemWorkDto;
import com.superr.duperr.todolistapp.service.ToDoItemService;
import static org.springframework.http.ResponseEntity.*;

/**
 * ToDoItem Controller
 * 
 * @author dineshwijekoon
 *
 */
@RestController
@Validated
public class ToDoItemController {

	private final ToDoItemService toDoItemService;

	@Autowired
	public ToDoItemController(ToDoItemService toDoItemService) {
		this.toDoItemService = toDoItemService;
	}

	/**
	 * Lookup for all todoItems.
	 * 
	 * @return List of all todoItems
	 */
	@GetMapping(value = "/todoItems")
	public ResponseEntity<?> getAllToDoItems() {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(toDoItemService.getAllToDoItems(), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Lookup a ToDoItem by given ToDo Id.
	 *
	 * @param id ToDoItem identifier
	 * @return Requested ToDoItem for given todoId identifier
	 */
	@GetMapping(value = "/todoItems/{todoId}")
	public ResponseEntity<?> getToDoItem(@PathVariable(value = "todoId") long todoId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(toDoItemService.getToDoItem(todoId), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Lookup for all workTodoItems.
	 * 
	 * @return List of all ToDoItemWorkDtos
	 */
	@GetMapping(value = "/workTodoItems")
	public ResponseEntity<?> getAllWorkToDoItems() {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(toDoItemService.getAllWorkToDoItems(), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Lookup a ToDoItemWork by given ToDo Id.
	 *
	 * @param id ToDoItemWork identifier
	 * @return Requested ToDoItemWork for given todoId identifier
	 */
	@GetMapping(value = "/workTodoItems/{todoId}")
	public ResponseEntity<?> getToDoItemWork(@PathVariable(value = "todoId") long todoId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(toDoItemService.getToDoItemWork(todoId), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Create a WorkToDo Item.
	 *
	 * @param workToDoItemDto rating dataWorkToDoItem data transfer object//TODO
	 */
	@PostMapping(value = "/newTodoItem")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> addToDoItem(@Valid @RequestBody ToDoItemWorkDto newDto) {
		HttpHeaders httpHeaders = new HttpHeaders();
		return new ResponseEntity<>(toDoItemService.addToDoItem(newDto), httpHeaders, HttpStatus.OK);
	}

	/**
	 * Update status or priority of a ToDoItem
	 *
	 * @param todoId WorkToDoItem identifier
	 * @param dto    WorkToDoItemDto Data Transfer Object
	 * @return The modified WorkToDoItemDto DTO.
	 */
	@PutMapping(value = "/workTodoItems/{todoId}")
	public ToDoItemWorkDto updateWorkToDoItem(@PathVariable(value = "todoId") @Min(1) long todoId,
			@Valid @RequestBody ToDoItemWorkDto dto) {
		return toDoItemService.updateWorkToDoItemWithPut(todoId, dto);
	}

	/**
	 * Restore todoItems from state INACTIVE to PENDING
	 *
	 * @param ids list of ToDoItemWork identifiers
	 */
	@PutMapping(value = "/workTodoItems")
	public ResponseEntity<Void> restoreToDoItems(@RequestBody String ids) {
		if (!ids.isEmpty()) {
			toDoItemService.restoreTodoItem(ids);
		}
		return noContent().build();
	}

	/**
	 * Update status or priority of a ToDoItem.
	 *
	 * @param todoId tWorkToDoItemour identifier
	 * @param dto    WorkToDoItemDto Data Transfer Object
	 * @return The modified WorkToDoItemDto DTO.
	 */
	@PatchMapping(value = "/workTodoItems/{todoId}")
	public ToDoItemWorkDto updateWorkToDoItemStatus(@PathVariable(value = "todoId") long todoId,
			@Valid @RequestBody ToDoItemWorkDto dto) {
		return toDoItemService.updateWorkToDoItemWithPatch(todoId, dto);
	}

	/**
	 * Delete a workTodoItem.
	 *
	 * @param todoId workTodoItem identifier
	 */
	@DeleteMapping(path = "/workTodoItems/{todoId}")
	public void delete(@PathVariable(value = "todoId") long todoId) {
		toDoItemService.delete(todoId);
	}

}
