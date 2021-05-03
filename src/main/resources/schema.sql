CREATE TABLE IF NOT EXISTS public.project
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    short       VARCHAR,
    description VARCHAR
);

CREATE TABLE IF NOT EXISTS public.task
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR,
    time   INT,
    start  DATE,
    "end"  DATE,
    status VARCHAR
);

CREATE TABLE IF NOT EXISTS public.project_task
(
    project_id INT REFERENCES project (id),
    task_id    INT REFERENCES task (id)
);