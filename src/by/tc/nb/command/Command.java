package by.tc.nb.command;

import java.io.IOException;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.exception.ServiceException;

public interface Command {
	Response execute(Request request) throws CommandException, ServiceException, ClassNotFoundException, IOException;
}
