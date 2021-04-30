<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Menu</title>
    <script type="text/javascript" src="../static/js/jquery-3.6.0.js"></script>
    <link rel="stylesheet" href="../static/css/bootstrap.min.css"/>
    <script type="text/javascript" src="../static/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="../static/css/menu.css"/>
    <script type="text/javascript" src="../static/js/menu.js"></script>
    <script type="text/javascript">
        const createProject = "create_project";
        const updateProject = "update_project";
        const createTask = "create_task";
        const updateTask = "update_task";
        const deleteProject = "delete_project";
        const deleteTask = "delete_task";
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body>
<jsp:include page="navbar.jsp"/>
<form action="${pageContext.request.contextPath}/action" method="POST">
    <input id="action" type="hidden" name="command"/>
    <div class="container-fluid">
        <div class="row mt-2">
            <div class="col-2">
                <div id="warningMessage" class="alert alert-warning" role="alert">
                    Message here
                </div>
            </div>
            <div class="col-8 d-flex justify-content-center">
                <h1 class="text-light">Menu</h1>
            </div>
            <div class="col-2">
            </div>
        </div>
        <hr class="bg-light">
        <div class="row mb-5">
            <div class="col-1"></div>
            <div class="col-10">
                <div class="row">
                    <div class="col-11">
                        <h4 class="text-light">Projects</h4>
                        <table id="projectsTable" class="table table-light">
                            <thead>
                            <tr>
                                <th>Select</th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Alias</th>
                                <th>description</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${sessionScope.projects}" var="project">
                                <tr id="p-${project.id}">
                                    <td>
                                        <input id="pcb-${project.id}" name="projectsCB" class="custom-checkbox"
                                               type="checkbox" value="${project.id}"/>
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
                    <div class="col-1 pt-5">
                        <button id="createProject" type="button" class="btn btn-block btn-info">Create</button>
                        <button id="changeProject" type="button" class="btn btn-block btn-info">Change</button>
                        <button id="deleteProject" name="deleteProject" class="btn btn-block btn-info" type="submit">
                            Delete
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
        <div class="row mb-5">
            <div class="col-1"></div>
            <div class="col-10">
                <div class="row">
                    <div class="col-11">
                        <h4 class="text-light">Tasks</h4>
                        <table id="tasksTable" class="table table-light">
                            <thead>
                            <tr>
                                <th>Select</th>
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
                                <tr id="t-${task.id}">
                                    <td>
                                        <input id="tcb-${task.id}" name="tasksCB" class="custom-checkbox"
                                               type="checkbox" value="${task.id}"/>
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
                    <div class="col-1 pt-5">
                        <button id="addTask" type="button" class="btn btn-block btn-info">Add</button>
                        <button id="changeTask" type="button" class="btn btn-block btn-info">Change</button>
                        <button id="deleteTask" name="deleteTask" class="btn btn-block btn-info" type="submit">Delete
                        </button>
                    </div>
                </div>
            </div>
            <div class="col-1"></div>
        </div>
    </div>
    <div class="modal fade" id="projectModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="projectModalTitle">Project</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body flex-column justify-content-center">
                    <label for="modalProjectId">ID</label>
                    <div class="input-group mb-3">
                        <input id="modalProjectId" name="projectId" type="text" class="form-control" aria-label="ID"
                               aria-describedby="modalProjectId" disabled>
                    </div>
                    <label for="modalProjectName">Name</label>
                    <div class="input-group mb-3">
                        <input id="modalProjectName" name="projectName" type="text" class="form-control"
                               placeholder="Name"
                               aria-label="Name"
                               aria-describedby="modalProjectName">
                    </div>
                    <label for="modalProjectShort">Short name</label>
                    <div class="input-group mb-3">
                        <input id="modalProjectShort" name="projectShortName" type="text" class="form-control"
                               placeholder="Short name"
                               aria-label="Short name" aria-describedby="modalProjectShort">
                    </div>
                    <label for="modalProjectDescription">Description</label>
                    <div class="input-group">
                        <textarea id="modalProjectDescription" name="projectDescription" class="form-control"
                                  aria-label="Description"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button id="modalProjectSave" name="saveProject" type="submit" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="taskModal" tabindex="-1" role="dialog" aria-labelledby="modalCenterTitle"
         aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="taskModalTitle">Task</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body flex-column justify-content-center">
                    <label for="modalTaskId">ID</label>
                    <div class="input-group mb-3">
                        <input id="modalTaskId" name="taskId" type="text" class="form-control" aria-label="ID"
                               aria-describedby="modalTaskId" disabled>
                    </div>
                    <div class="form-group">
                        <label for="modalTaskProject">Project</label>
                        <select name="taskProject" class="form-control" id="modalTaskProject">
                            <c:forEach items="${sessionScope.projects}" var="project">
                                <option value="mtp-${project.id}">${project.shortName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <label for="modalTaskName">Name</label>
                    <div class="input-group mb-3">
                        <input id="modalTaskName" name="taskName" type="text" class="form-control" placeholder="Name"
                               aria-label="Name"
                               aria-describedby="modalTaskName">
                    </div>
                    <label for="modalTaskTime">Time (h)</label>
                    <div class="input-group mb-3">
                        <input id="modalTaskTime" name="taskTime" type="number" class="form-control" placeholder="Time"
                               aria-label="Short name" aria-describedby="modalTaskTime">
                    </div>
                    <label for="modalTaskStart">Start date</label>
                    <div class="input-group date" data-provide="datepicker">
                        <input id="modalTaskStart" name="taskStart" type="date" class="form-control">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-th"></span>
                        </div>
                    </div>
                    <label for="modalTaskEnd">End date</label>
                    <div class="input-group date" data-provide="datepicker">
                        <input id="modalTaskEnd" name="taskEnd" type="date" class="form-control">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-th"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="modalTaskStatus">Status</label>
                        <select name="taskStatus" class="form-control" id="modalTaskStatus">
                            <c:forEach items="${sessionScope.statuses}" var="status">
                                <option value="mts-${status.id}">${status.status}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button id="modalTaskSave" name="saveTask" type="submit" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
