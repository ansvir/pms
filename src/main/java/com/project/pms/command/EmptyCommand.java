package com.project.pms.command;

import com.project.pms.qualifiers.EmptyCommandQualifier;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@EmptyCommandQualifier
@Transactional
public class EmptyCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
