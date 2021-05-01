package com.project.pms.service;

import com.project.pms.dao.*;
import com.project.pms.model.Project;
import com.project.pms.model.ProjectTask;
import com.project.pms.model.Status;
import com.project.pms.model.Task;
import com.project.pms.qualifiers.ProjectDAOImplQualifier;
import com.project.pms.qualifiers.ProjectTaskDAOImplQualifier;
import com.project.pms.qualifiers.TaskDAOImplQualifier;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

@Singleton
@Startup
public class DatabasePopulator {

    @Inject
    private InitiateDatabaseDAO initiateDatabaseDAO;

    @Inject
    @ProjectDAOImplQualifier
    private ProjectDAO projectDAO;

    @Inject
    @TaskDAOImplQualifier
    private TaskDAO taskDAO;

    @Inject
    @ProjectTaskDAOImplQualifier
    private ProjectTaskDAO projectTaskDAO;

    private static final Logger LOGGER = Logger.getLogger(DatabasePopulator.class.getName());

    // schema and data SQL files are stored under src/main/resources folder
    @PostConstruct
    private void initializeDatabase() {
        if (initiateDatabaseDAO.createTables() == 0) {
            return;
        } else {
            LOGGER.info("Tables were created");
        }

        Project project1 = new Project();
        project1.setName("Logistics project");
        project1.setShortName("LP");
        project1.setDescription("The logistics project about supporting chains of supplies");
        Long project1Id = projectDAO.create(project1);

        Project project2 = new Project();
        project2.setName("Enterprise Resources Planning System");
        project2.setShortName("ERPS");
        project2.setDescription("The idea to create ERP to automate manufacture processes");
        Long project2Id = projectDAO.create(project2);

        Project project3 = new Project();
        project3.setName("Calculator");
        project3.setShortName("Calc");
        project3.setDescription("Calculator that supports multiplication, addition, subtraction and division");
        Long project3Id = projectDAO.create(project3);

        Project project4 = new Project();
        project4.setName("Customer Relationship Management System");
        project4.setShortName("CRMS");
        project4.setDescription("CRM of new age that provides new functionality to increase customers flow at company");
        Long project4Id = projectDAO.create(project4);

        try {
            Task task1 = new Task();
            task1.setName("Implement UI");
            task1.setTime(120);
            task1.setStart((Date) new SimpleDateFormat().parse("2021-01-01"));
            task1.setEnd((Date) new SimpleDateFormat().parse("2021-01-21"));
            task1.setStatus(Status.PROCESS);
            Long task1Id = taskDAO.create(task1);

            Task task2 = new Task();
            task2.setName("Create addition and division operations");
            task2.setTime(160);
            task2.setStart((Date) new SimpleDateFormat().parse("2021-01-01"));
            task2.setEnd((Date) new SimpleDateFormat().parse("2021-02-1"));
            task2.setStatus(Status.PROCESS);
            Long task2Id = taskDAO.create(task2);

            Task task3 = new Task();
            task3.setName("Migrate project to Maven");
            task3.setTime(120);
            task3.setStart((Date) new SimpleDateFormat().parse("2020-12-01"));
            task3.setEnd((Date) new SimpleDateFormat().parse("2021-12-21"));
            task3.setStatus(Status.ACCOMPLISHED);
            Long task3Id = taskDAO.create(task3);

            Task task4 = new Task();
            task4.setName("Find out the requirements");
            task4.setTime(80);
            task4.setStart((Date) new SimpleDateFormat().parse("2020-12-01"));
            task4.setEnd((Date) new SimpleDateFormat().parse("2021-12-14"));
            task4.setStatus(Status.ACCOMPLISHED);
            Long task4Id = taskDAO.create(task4);

            Task task5 = new Task();
            task5.setName("Implement business logic");
            task5.setTime(320);
            task5.setStart((Date) new SimpleDateFormat().parse("2021-01-01"));
            task5.setEnd((Date) new SimpleDateFormat().parse("2021-03-01"));
            task5.setStatus(Status.DELAYED);
            Long task5Id = taskDAO.create(task5);

            ProjectTask project1task3 = new ProjectTask(project1Id, task3Id);
            ProjectTask project1task4 = new ProjectTask(project1Id, task4Id);
            ProjectTask project1task5 = new ProjectTask(project1Id, task5Id);
            ProjectTask project3task1 = new ProjectTask(project3Id, task1Id);
            ProjectTask project3task2 = new ProjectTask(project3Id, task2Id);
            projectTaskDAO.create(project1task3);
            projectTaskDAO.create(project1task4);
            projectTaskDAO.create(project1task5);
            projectTaskDAO.create(project3task1);
            projectTaskDAO.create(project3task2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
