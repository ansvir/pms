package com.project.pms.command;

public enum Command {
    UPDATE_PROJECT {
        {
            this.command = new UpdateProjectCommand();
        }
    },
    CREATE_PROJECT {
        {
            this.command = new CreateProjectCommand();
        }
    },
    UPDATE_TASK {
        {
            this.command = new UpdateTaskCommand();
        }
    },
    CREATE_TASK {
        {
            this.command = new CreateTaskCommand();
        }
    },
    DELETE_PROJECT {
        {
            this.command = new DeleteProjectCommand();
        }
    },
    DELETE_TASK {
        {
            this.command = new DeleteTaskCommand();
        }
    };

    ICommand command;

    public ICommand getCommand() {
        return this.command;
    }
}
