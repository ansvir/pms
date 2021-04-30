$(document).ready(function() {
    let currentSelectedProjectId;
    let currentSelectedTaskId;
    const warningMessage = $('#warningMessage');
    warningMessage.hide();
    const projectModal = $('#projectModal');
    const taskModal = $('#taskModal');
    projectModal.modal({show: false});
    taskModal.modal({show: false});
    const action = $('#action');
    action.val(createProject);

    $('#projectsTable').on('click', function(e) {
        const tr = $(e.target).closest('tr')
        let trId = tr.attr('id');
        let id = trId.substring(2);
        const cb = $(`#pcb-${id}`);
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

    $('#tasksTable').on('click', function(e){
        const tr = $(e.target).closest('tr')
        let trId = tr.attr('id');
        let id = trId.substring(2);
        const cb = $(`#tcb-${id}`);
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

    $('#createProject').on('click', function() {
        action.val(createProject);
        $('#projectModalTitle').text("New Project");
        projectModal.modal('show');
    });

    $('#changeProject').on('click', function() {
        if (currentSelectedProjectId === undefined) {
            warningMessage.text("Choose a project, please!");
            warningMessage.show();
            setTimeout(function(){
                $('#warningMessage').hide()
            }, 4000)
            return;
        }
        action.val(updateProject);
        $.ajax({
            url: contextPath + '/project/id',
            data: {
                projectId: currentSelectedProjectId
            },
            success: function (responseText) {
                let response = JSON.parse(responseText);
                $('#modalProjectId').val(response.id);
                $('#modalProjectName').val(response.name);
                $('#modalProjectShort').val(response.shortName);
                $('#modalProjectDescription').val(response.description);
                $('#projectModalTitle').text(`Project ${response.shortName}`);
            }
        });
        projectModal.modal('show');
    });

    $('#addTask').on('click', function() {
        action.val(createTask);
        $('#taskModalTitle').text("New Task");
        taskModal.modal('show');
    });

    $('#changeTask').on('click', function() {
        if (currentSelectedTaskId === undefined) {
            warningMessage.text("Change a task, please!");
            warningMessage.show();
            setTimeout(function(){
                $('#warningMessage').hide()
            }, 4000)
            return;
        }
        action.val(updateTask);
        $.ajax({
            url: contextPath + '/task/id',
            data: {
                taskId: currentSelectedTaskId
            },
            success: function (responseText) {
                let response = JSON.parse(responseText);
                $('#modalTaskId').val(response[0].id);
                $('#modalTaskProject').val(`mtp-${response[1].id}`);
                $('#modalTaskName').val(response[0].name);
                $('#modalTaskTime').val(response[0].time);
                $('#modalTaskStart').val(response[3]);
                $('#modalTaskEnd').val(response[4]);
                $('#modalTaskStatus').val(`mts-${response[2]}`);
                $('#taskModalTitle').text(`Task ${response[0].name}`);
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
        $('#modalTaskProject').val(`mtp-1`);
        $('#modalTaskName').val('');
        $('#modalTaskTime').val('');
        $('#modalTaskStart').val('');
        $('#modalTaskEnd').val('');
        $('#modalTaskStatus').val(`mts-1`);
    });

    $('#deleteProject').on('click', function() {
        action.val(deleteProject);
    });

    $('#deleteTask').on('click', function() {
        action.val(deleteTask);
    })
});