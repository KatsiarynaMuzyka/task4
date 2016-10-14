package by.tc.nb.command.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.WorkWithFileRequest;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;


public class WriteNotebookInFile implements Command {
	Response response = new Response();
	@Override
	public Response execute(Request request) throws CommandException, ClassNotFoundException, ServiceException, IOException {
		WorkWithFileRequest req = null;
		if(request instanceof WorkWithFileRequest){
			req = (WorkWithFileRequest)request;
		}else{
			throw new CommandException("Wrong request");
		}
		
		String path = req.getPath();
		
		
		ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
		
		try {
			nbService.writeNotebookInFile(path);
		} catch (FileNotFoundException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("File not found");
			return response;
		}
		
		
		response.setErrorStatus(false);
		response.setResultMessage("All OK!");
		
		
		return response;
	}

}
