CREATE TABLE todo_list(
	todo_Id BIGINT generated by default as identity,
	task VARCHAR(100),
	status VARCHAR(50),
	date_created DATE,
	priority VARCHAR(10),
	reminder BIGINT,
	todolist_type VARCHAR(10)
);