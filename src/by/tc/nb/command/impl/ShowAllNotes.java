package by.tc.nb.command.impl;

import java.io.IOException;

import by.tc.nb.bean.AddNoteRequest;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;
import by.tc.nb.source.NoteBookProvider;

public class ShowAllNotes implements Command{

	@Override
	public Response execute(Request request) throws CommandException, ServiceException, IOException {
		
		Request req = null;
		if(request instanceof Request){
			req = request;
		}else{
			throw new CommandException("Wrong request");
		}
		
	
		ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
        nbService.show();
		
		Response response = new Response();
		response.setErrorStatus(false);
		response.setResultMessage("All OK!");
		
		return response;
	}

}
