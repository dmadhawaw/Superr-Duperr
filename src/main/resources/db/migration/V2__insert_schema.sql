INSERT INTO todo_list (todo_id, task, status, date_created, priority, reminder, todolist_type) VALUES
  ('101', 'Pay Bill', 'Pending', TO_DATE('22-Jun-2021'), 'LOW', 86400000, 'HOME_LIST'),
  ('102', 'Meeting', 'Pending', TO_DATE('20-Jun-2021'), 'MEDIUM', 43200000, 'WORK_LIST'),
  ('103', 'Training', 'Completed', TO_DATE('20-Jan-2021'), 'HIGH', 10800000,  'WORK_LIST');