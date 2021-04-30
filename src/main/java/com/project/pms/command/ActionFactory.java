package com.project.pms.command;

public class ActionFactory {
    public ICommand defineCommand(String stringCommand) {
        Command command = Command.valueOf(stringCommand.toUpperCase());
        return command.getCommand();
    }
}
