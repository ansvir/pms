DROP TABLE IF EXISTS project_task;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS task;
CREATE TABLE IF NOT EXISTS project
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    short       VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS task
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR,
    time   INT,
    start  DATE,
    "end"  DATE,
    status VARCHAR
);

CREATE TABLE IF NOT EXISTS project_task
(
    project_id INT REFERENCES project (id),
    task_id    INT REFERENCES task (id)
);