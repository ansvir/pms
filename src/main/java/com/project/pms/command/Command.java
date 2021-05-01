package com.project.pms.command;

import com.project.pms.qualifiers.*;

import javax.inject.Inject;

public class Command {

    public enum CommandEnum {
        UPDATE_PROJECT,
        CREATE_PROJECT,
        UPDATE_TASK,
        CREATE_TASK,
        DELETE_PROJECT,
        DELETE_TASK,
        EMPTY
    }

    @Inject
    @EmptyCommandQualifier
    private ICommand emptyCommand;
    @Inject
    @CreateProjectCommandQualifier
    private ICommand createProjectCommand;
    @Inject
    @UpdateProjectCommandQualifier
    private ICommand updateProjectCommand;
    @Inject
    @DeleteProjectCommandQualifier
    private ICommand deleteProjectCommand;
    @Inject
    @CreateTaskCommandQualifier
    private ICommand createTaskCommand;
    @Inject
    @UpdateTaskCommandQualifier
    private ICommand updateTaskCommand;
    @Inject
    @DeleteTaskCommandQualifier
    private ICommand deleteTaskCommand;

    public CommandEnum getCommandEnum(String action) {
        return
                CommandEnum.
                        valueOf
                                (action.
                                        toUpperCase());
    }
    public ICommand getCommand(CommandEnum commandEnum) {
        switch (commandEnum) {
            case CREATE_PROJECT: return createProjectCommand;
            case UPDATE_PROJECT: return updateProjectCommand;
            case DELETE_PROJECT: return deleteProjectCommand;
            case CREATE_TASK: return createTaskCommand;
            case UPDATE_TASK: return updateTaskCommand;
            case DELETE_TASK: return deleteTaskCommand;
            default: return emptyCommand;
        }
    }
}
