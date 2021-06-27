package com.superr.duperr.todolistapp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HOME_LIST")
public class ToDoItemPersonal extends ToDoItem {

}
