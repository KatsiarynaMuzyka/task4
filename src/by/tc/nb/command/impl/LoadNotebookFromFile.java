package by.tc.nb.command.impl;

import java.io.IOException;

import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.WorkWithFileRequest;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;
import by.tc.nb.source.NoteBookProvider;

public class LoadNotebookFromFile implements Command {
	Response response = new Response();
	@Override
	public Response execute(Request request) throws CommandException, ClassNotFoundException, ServiceException{
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
			nbService.loadNoteBookFromFile(path);
		} catch (IOException e) {
			response.setErrorStatus(true);
			response.setErrorMessage("File not found");
		}
		
		
		response.setErrorStatus(false);
		response.setResultMessage("All OK!");
		
		
		return response;
	}

}
