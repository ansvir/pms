package com.project.pms.command;

import com.project.pms.qualifiers.EmptyCommandQualifier;

import javax.inject.Inject;

public class ActionFactory {

    @Inject
    private Command command;

    @Inject
    @EmptyCommandQualifier
    private ICommand iCommand;

    public ICommand defineCommand(String stringCommand) {
        Command.CommandEnum commandEnum = command.getCommandEnum(stringCommand);
        iCommand = command.getCommand(commandEnum);
        return iCommand;
    }
}
