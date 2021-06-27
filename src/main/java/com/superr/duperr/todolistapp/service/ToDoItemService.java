package com.superr.duperr.todolistapp.service;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.superr.duperr.todolistapp.data.repository.ToDoItemRepository;
import com.superr.duperr.todolistapp.domain.ToDoItem;
import com.superr.duperr.todolistapp.domain.ToDoItemWork;
import com.superr.duperr.todolistapp.dto.ToDoItemDto;
import com.superr.duperr.todolistapp.dto.ToDoItemWorkDto;
import com.superr.duperr.todolistapp.exception.ResourceNotFoundException;
import com.superr.duperr.todolistapp.exception.ToDoListErrorCode;
import com.superr.duperr.todolistapp.utils.Priority;
import com.superr.duperr.todolistapp.utils.ToDoStatus;

@Service
public class ToDoItemService {

	@Autowired
	ToDoItemRepository<ToDoItem> toDoItemRepository;

	@Autowired
	ToDoItemRepository<ToDoItemWork> workToDoItemRepository;

	/**
	 * Lookup all ToDoItemDtos with all different list types.
	 *
	 * @return All ToDoItem as ToDoItemDto's
	 */
	public List<ToDoItemDto> getAllToDoItems() {
		List<ToDoItemDto> toListItems = new ArrayList<>();
		toListItems = toDoItemRepository.findAll().stream()
				.map(d -> new ToDoItemDto(d.getTodoId(), d.getTask(), d.getStatus(), d.getDateCreated()))
				.collect(Collectors.toList());

		return toListItems;
	}

	public ToDoItemDto getToDoItem(long todoId) {

		Optional<ToDoItem> todoItemResponse = toDoItemRepository.findById(todoId);
		if (!todoItemResponse.isPresent())
			throw new ResourceNotFoundException("ToDoItem for [toDoId=" + todoId + "] can't be found",
					ToDoListErrorCode.NOT_FOUND);
		return new ToDoItemDto(todoItemResponse.get());
	}

	/**
	 * Lookup all WorkToDoItems.
	 *
	 * @return All todoDtos as WorkToDoItemsDto's
	 */
	public List<ToDoItemWorkDto> getAllWorkToDoItems() {
		List<ToDoItemWork> toListItems = new ArrayList<>();
		List<ToDoItemWorkDto> todoDtos = new ArrayList<>();
		toListItems = workToDoItemRepository.findAllToDoItemWorks();
		toListItems.forEach(item -> todoDtos.add(new ToDoItemWorkDto(item)));
		return todoDtos;
	}

	public ToDoItemWorkDto getToDoItemWork(long todoId) {

		Optional<ToDoItemWork> todoItemWorkResponse = workToDoItemRepository.findById(todoId);
		if (!todoItemWorkResponse.isPresent())
			throw new ResourceNotFoundException("ToDoItemWork for [toDoId=" + todoId + "] can't be found",
					ToDoListErrorCode.NOT_FOUND);
		return new ToDoItemWorkDto(todoItemWorkResponse.get());
	}

	/**
	 * Add new ToDo Item to the list. Assume initial status is : Pending Date
	 * created will be the date of the item created. Based on the Priority the
	 * reminder duration will be vary and initial reminder for [P1 - 3hrs][P2 -
	 * 12hrs][P3 - 24hrs] Create a new WorkToDoItem.
	 *
	 * @param workToDoItemDto rating dataWorkToDoItem data transfer object//TODO
	 */
	public ToDoItemWork addToDoItem(ToDoItemWorkDto newDto) {
		ToDoItemWork todoItemNew = new ToDoItemWork();
		todoItemNew.setTask(newDto.getTask());
		todoItemNew.setStatus(ToDoStatus.PENDING.toString());
		todoItemNew.setPriority(Priority.fromString(newDto.getPriority()).toString());
		todoItemNew.setDateCreated(new Date());
		todoItemNew.setReminderDuration(Priority.getDurationByName(newDto.getPriority()));
		return workToDoItemRepository.save(todoItemNew);
	}

	/**
	 * Update status[PENDING -> COMPLETED] or priority of a WorkToDoItem
	 *
	 * @param todoId WorkToDoItem identifier
	 * @param dto    WorkToDoItem Data Transfer Object
	 * @return The WorkToDoItem DTO.
	 */
	public ToDoItemWorkDto updateWorkToDoItemWithPut(long todoId, ToDoItemWorkDto dto) {
		ToDoItemWork todoItem = verifyToDoItem(todoId);
		// add check to complete.
		todoItem.setStatus(ToDoStatus.findByStatus(dto.getStatus()).toString());
		todoItem.setPriority(Priority.fromString(dto.getPriority()).toString());
		return new ToDoItemWorkDto(toDoItemRepository.save(todoItem));
	}

	/**
	 * Update status to Inactive WorkToDoItem - If Current status is COMPLETED and
	 * task is more than 30 days OLD will move to inactive status.
	 *
	 * @param todoId WorkToDoItem identifier
	 * @param dto    WorkToDoItem Data Transfer Object
	 * @return The WorkToDoItem DTO.
	 */
	public ToDoItemWorkDto updateWorkToDoItemWithPatch(long todoId, ToDoItemWorkDto dto) {
		ToDoItemWork todoItem = verifyToDoItem(todoId);
		ZonedDateTime now = ZonedDateTime.now();
		ZonedDateTime thirtyDaysAgo = now.plusDays(-30);

		if (todoItem.getDateCreated().toInstant().isBefore(thirtyDaysAgo.toInstant())
				&& todoItem.getStatus().equalsIgnoreCase(ToDoStatus.COMPLETED.toString())) {
			todoItem.setStatus(ToDoStatus.INACTIVE.toString());
		}

		return new ToDoItemWorkDto(toDoItemRepository.save(todoItem));
	}

	public void restoreTodoItem(String ids) {

		if (!ids.isEmpty()) {
			List<String> todoIds = Arrays.asList(ids.split(","));
			if (!CollectionUtils.isEmpty(todoIds)) {
				for (String id : todoIds) {
					ToDoItemWork todoItem = verifyToDoItem(Long.valueOf(id));
					if (todoItem.getStatus().equalsIgnoreCase(ToDoStatus.INACTIVE.toString())) {
						todoItem.setStatus(ToDoStatus.PENDING.toString());
						workToDoItemRepository.save(todoItem);
					}

				}
			}
		}
	}

	/**
	 * Delete a WorkToDoItem
	 *
	 * @param todoId WorkToDoItem identifier
	 */
	public void delete(long todoId) {
		ToDoItemWork todoItem = verifyToDoItem(todoId);
		workToDoItemRepository.delete(todoItem);
	}

	/**
	 * Verify and return the WorkToDoItem given a todoId.
	 *
	 * @param todoId WorkToDoItem identifier
	 * @return the found WorkToDoItem
	 * @throws NoSuchElementException if no WorkToDoItem found.
	 */
	private ToDoItemWork verifyToDoItem(long todoId) throws NoSuchElementException {
		return workToDoItemRepository.findById(todoId).orElseThrow(() -> new ResourceNotFoundException(
				"WorkToDoItem does not exist for given Todo Id : " + todoId, ToDoListErrorCode.NOT_FOUND));
	}

	/**
	 * Exception handler if NoSuchElementException is thrown in this Controller
	 *
	 * @param ex exception
	 * @return Error message String.
	 */
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public String return400(NoSuchElementException ex) {
		return ex.getMessage();

	}

}
