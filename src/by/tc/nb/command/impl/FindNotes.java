package by.tc.nb.command.impl;

import by.tc.nb.bean.AddNoteRequest;
import by.tc.nb.bean.FindNotesRequest;
import by.tc.nb.bean.FindNotesResponse;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.command.Command;
import by.tc.nb.command.exception.CommandException;
import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.ServiceFactory;
import by.tc.nb.service.exception.ServiceException;
import by.tc.nb.source.NoteBookProvider;

public class FindNotes implements Command {

	@Override
	public Response execute(Request request) throws CommandException, ServiceException {
		FindNotesRequest req = null;
		if (request instanceof FindNotesRequest) {
			req = (FindNotesRequest) request;
		} else {
			throw new CommandException("Wrong request");
		}

		
		ServiceFactory service = ServiceFactory.getInstance();
        NoteBookService nbService = service.getNoteBookService();
		
		
		FindNotesResponse response = new FindNotesResponse();
		
		
		if (req.getCommandName().equals("FIND_NOTES_BY_CONTENT")) {
			String note = req.getNote();
			response.setFindBook(nbService.findByContent(note));
		}

		else {
			String date = req.getDate();
			nbService.findByDate(date);
			
			response.setFindBook(nbService.findByDate(date));
		}

		
		response.setErrorStatus(false);
		response.setResultMessage("All OK!");
		
		return response;

	}

}
