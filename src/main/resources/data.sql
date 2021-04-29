INSERT INTO project (name, short, description) VALUES
('Logistic Project', 'LP', 'The logistics project about supporting chains of supplies'),
('Enterprise Resources Planning System', 'ERPS', 'The idea to create ERP to automate manufacture processes'),
('Calculator', 'Calc', 'Calculator that supports multiplication, addition, subtraction adn division'),
('Customer Relationship Management System', 'CRMS', 'CRM of new age that provides new functionality to increase customers flow at company');

INSERT INTO task(name, time, start, "end", status) VALUES
('Task1', '400', '2020-01-01', '2020-01-04', 'PROCESS'),
('Task2', '200', '2020-01-01', '2020-01-03', 'NOT_STARTED'),
('Task3', '200', '2020-01-01', '2020-01-03', 'PROCESS'),
('Task4', '80', '2020-01-01', '2020-01-02', 'ACCOMPLISHED');

INSERT INTO project_task(project_id, task_id) VALUES
(1,1), (1,2), (1,3), (1,4);
