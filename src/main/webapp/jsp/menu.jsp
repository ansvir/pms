<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <script type="text/javascript" src="../static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../static/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-2">
        </div>
        <div class="col-8 d-flex justify-content-center">
            <h1>Menu</h1>
        </div>
        <div class="col-2">
        </div>
    </div>
    <hr>
    <div class="row mb-5">
        <div class="col-1"></div>
        <div class="col-10" style="border: 2px solid red">
            <div class="row">
                <div class="col-11" style="border: 2px solid blue">
                    <h4>Projects</h4>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>&nbsp;</th>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Alias</th>
                            <th>description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.projects}" var="project">
                            <tr>
                                <td>
                                    <input name="projectsCB" class="form-check-input" type="checkbox"/>
                                </td>
                                <td>${project.id}</td>
                                <td>${project.name}</td>
                                <td>${project.shortName}</td>
                                <td>${project.description}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-1 my-auto py-2" style="border: 2px solid green">
                    <button id="createProject" class="btn btn-block btn-primary">Create</button>
                    <button id="changeProject" class="btn btn-block btn-primary">Change</button>
                    <button id="deleteProject" class="btn btn-block btn-primary" type="submit">Delete</button>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
    <div class="row mb-5">
        <div class="col-1"></div>
        <div class="col-10" style="border: 2px solid red">
            <div class="row">
                <div class="col-11" style="border: 2px solid blue">
                    <h4>Tasks</h4>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th></th>
                            <th>ID</th>
                            <th>Project</th>
                            <th>Name</th>
                            <th>Start</th>
                            <th>End</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sessionScope.tasks}" var="task">
                            <tr>
                                <td>
                                    <input name="tasksCB" class="form-check-input" type="checkbox"/>
                                </td>
                                <td>${task.id}</td>
                                <td>
                                    <c:forEach items="${sessionScope.projects}" var="project">
                                        <c:forEach items="${project.tasks}" var="task2">
                                            <c:if test="${task2.id eq task.id}">
                                                ${project.shortName}
                                            </c:if>
                                        </c:forEach>
                                    </c:forEach>
                                </td>
                                <td>${task.name}</td>
                                <td>${task.start}</td>
                                <td>${task.end}</td>
                                <td>${task.status.status}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-1 my-auto">
                    <button id="addTask" class="btn btn-block btn-primary">Add</button>
                    <button id="changeTask" class="btn btn-block btn-primary">Change</button>
                    <button id="deleteTask" class="btn btn-block btn-primary" type="submit">Delete</button>
                </div>
            </div>
        </div>
        <div class="col-1"></div>
    </div>
</div>
</body>
</html>
