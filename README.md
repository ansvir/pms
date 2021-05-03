# Simple Project Managament System

Functionality:

1. Add projects
2. Add tasks
3. View added projects and tasks

How to run:

1. Use `git clone https://github.com/ansvir/pms`
2. Use postgreSQL database, create here "pms" database
3. In the /pms root directory run `mvn wildfly:run`. Maven will download Wildfly application server and start it, project will be automatically compiled
4. Navigate to `http://localhost:8080/`
5. Use Create, Change, Delete buttons to manage existing projects and tasks

Database

To create databse `pms` install postgreSQL server on your PC from `https://www.postgresql.org/download/`, run psql and type `CREATE DATABASE pms;` or run pgAdmin and create database `pms` using UI

Tests

To run tests execute `mvn test`
