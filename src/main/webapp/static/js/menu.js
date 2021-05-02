$(document).ready(function () {
    let currentSelectedProjectId;
    let currentSelectedTaskId;
    let projects;
    let tasks;
    const body = $('body');
    const warningMessage = $('#warningMessage');
    warningMessage.hide();
    const projectModal = $('#projectModal');
    const taskModal = $('#taskModal');
    projectModal.modal({show: false});
    taskModal.modal({show: false});
    const action = $('#action');
    action.val(createProject);
    let statuses = [];

    $.ajax({
        url: contextPath + '/api/status/all',
        async: true,
        success: function (responseText) {
            statuses = responseText;
            console.log(statuses);
            statuses.forEach(status => {
                $('#modalTaskStatus').append(
                    `
            <option value="mts-${status.id}">${status.name}</option>
            `
                )
            });
        }
    });

    $.ajax({
        url: contextPath + '/api/project/all',
        async: true,
        success: function (responseText) {
            projects = responseText;
            projects.forEach(project => {
                $('#modalTaskProject').append(
                    `
            <option value="mtp-${project.id}">${project.shortName}</option>
            `
                )
            });
            const projectsTableTBody = $('#projectsTable tbody');
            projects.forEach(project => {
                projectsTableTBody.append(
                    `
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
                      `
                );
            });

            $.ajax({
                url: contextPath + '/api/task/all',
                success: function (responseText) {
                    tasks = responseText;
                    console.log(tasks);
                    const tasksTableTBody = $('#tasksTable tbody');
                    tasks.forEach(task => {
                        tasksTableTBody.append(
                            `
                                <tr id="t-${task.id}">
                                    <td>
                                        <input id="tcb-${task.id}" name="tasksCB" class="custom-checkbox"
                                               type="checkbox" value="${task.id}"/>
                                    </td>
                                    <td>${task.id}</td>
                                    <td>${getProjectShortNameById(task.projectId)}</td>
                                    <td>${task.name}</td>
                                    <td>${task.start}</td>
                                    <td>${task.end}</td>
                                    <td>${getStatusNameByStatusId(task.status)}</td>
                                </tr>
            `
                        );
                    });
                }
            });
        }
    });

    body.on('click', '#projectsTable', function (e) {
        const tr = $(e.target).closest('tr')
        let trId = tr.attr('id');
        let id = trId.substring(2);
        const cb = $(`
        #pcb-${id}`);
        if (cb.is(':checked')) {
            cb.prop('checked', false);
            tr.removeClass('tr-select-bg');
            currentSelectedProjectId = undefined;
        } else {
            cb.prop('checked', true);
            tr.addClass('tr-select-bg');
            currentSelectedProjectId = id;
        }
    });

    body.on('click', '#tasksTable', function (e) {
        const tr = $(e.target).closest('tr')
        let trId = tr.attr('id');
        let id = trId.substring(2);
        const cb = $(`
        #tcb-${id}`);
        if (cb.is(':checked')) {
            cb.prop('checked', false);
            tr.removeClass('tr-select-bg');
            currentSelectedTaskId = undefined;
        } else {
            cb.prop('checked', true);
            tr.addClass('tr-select-bg');
            currentSelectedTaskId = id;
        }
    });

    body.on('click', '#createProject',function () {
        action.val(createProject);
        $('#projectModalTitle').text("New Project");
        projectModal.modal('show');
    });

    body.on('click', '#changeProject', function () {
        if (currentSelectedProjectId === undefined) {
            warningMessage.text("Choose a project, please!");
            warningMessage.show();
            setTimeout(function () {
                $('#warningMessage').hide()
            }, 4000)
            return;
        }
        action.val(updateProject);
        $.ajax({
            url: contextPath + `/api/project/${currentSelectedProjectId}`,
            success: function (responseText) {
                let response = responseText;
                $('#modalProjectId').val(response.id);
                $('#modalProjectName').val(response.name);
                $('#modalProjectShort').val(response.shortName);
                $('#modalProjectDescription').val(response.description);
                $('#projectModalTitle').text(`
                Project ${response.shortName}`);
            }
        });
        projectModal.modal('show');
    });

    body.on('click', '#addTask', function () {
        action.val(createTask);
        $('#taskModalTitle').text("New Task");
        taskModal.modal('show');
    });

    body.on('click', '#changeTask', function () {
        if (currentSelectedTaskId === undefined) {
            warningMessage.text("Choose a task, please!");
            warningMessage.show();
            setTimeout(function () {
                $('#warningMessage').hide()
            }, 4000);
            return;
        }
        action.val(updateTask);
        $.ajax({
            url: contextPath + `/api/task/${currentSelectedTaskId}`,
            success: function (responseText) {
                let response = responseText;
                console.log(response);
                console.log($('#modalTaskStatus').val());
                $('#modalTaskId').val(response.id);
                $('#modalTaskProject').val(`mtp-${response.projectId}`);
                $('#modalTaskName').val(response.name);
                $('#modalTaskTime').val(response.time);
                $('#modalTaskStart').val(response.start);
                $('#modalTaskEnd').val(response.end);
                $('#modalTaskStatus').val(`mts-${response.status}`);
                console.log(response.status);
                console.log(getStatusNameByStatusId(response.status));
                $('#taskModalTitle').text(`
                Task ${response.name}`);
            }
        });
        taskModal.modal('show');
    });

    projectModal.on('hidden.bs.modal', function () {
        $('#modalProjectId').val('');
        $('#modalProjectName').val('');
        $('#modalProjectShort').val('');
        $('#modalProjectDescription').val('');
    });

    taskModal.on('hidden.bs.modal', function () {
        $('#modalTaskId').val('');
        $('#modalTaskProject').val(`
                mtp-1`);
        $('#modalTaskName').val('');
        $('#modalTaskTime').val('');
        $('#modalTaskStart').val('');
        $('#modalTaskEnd').val('');
        $('#modalTaskStatus').val(`
                mts-1`);
    });

    body.on('click', '#deleteProject', function () {
        action.val(deleteProject);
    });

    body.on('click', '#deleteTask',function () {
        action.val(deleteTask);
    });

    function getProjectShortNameById(id) {
        let projectShortName = "";
        projects.forEach(project => {
            if (project.id === id) {
                projectShortName = project.shortName;
            }
        });

        return projectShortName;
    }

    function getStatusNameByStatusId(id) {
        return statuses.find(status => {
            return status.id === id;
        }).name;
    }
});