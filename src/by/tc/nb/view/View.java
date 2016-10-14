package by.tc.nb.view;

import java.io.IOException;
import java.util.Scanner;

import by.tc.nb.bean.AddNoteRequest;
import by.tc.nb.bean.FindNotesRequest;
import by.tc.nb.bean.FindNotesResponse;
import by.tc.nb.bean.Request;
import by.tc.nb.bean.Response;
import by.tc.nb.bean.WorkWithFileRequest;
import by.tc.nb.bean.entity.Note;
import by.tc.nb.controller.Controller;
import by.tc.nb.service.exception.ServiceException;

public class View {

	public static void main(String[] args) throws ClassNotFoundException, ServiceException, IOException {
		Controller controller = new Controller();
		
		Scanner in = new Scanner(System.in);
		
		String menu = ("Menu\n" + "[1] - Add new note\n"
						+ "[2] - Find notes in the notebook by content\n"
						+ "[3] - Find notes in the notebook by date\n"
						+ "[4] - Show notes in the notebook\n"
						+ "[5] - Save notebook in file\n"
						+ "[6] - Load notebook from file\n"
						+ "[0] - Exit");
		
		
		boolean b = true;
		while (b == true) {
			System.out.println(menu);

			int choice = in.nextInt();

			switch (choice) {
			case 0:
				System.out.println("Exit");
				b = false;
				break;

			case 1:
				AddNoteRequest request = new AddNoteRequest();
				request.setCommandName("ADD_NEW_NOTE");

				System.out.println("Enter your note");
				in = new Scanner(System.in);
				String note = in.nextLine();
				request.setNote(note);

				Response response = controller.doRequest(request);
				if (response.isErrorStatus() == true) {
					System.out.println(response.getErrorMessage());
				} else {

					System.out.println("Note was added");

				}
				break;

			case 2:
				FindNotesRequest findNotesRequest = new FindNotesRequest();
				findNotesRequest.setCommandName("FIND_NOTES_BY_CONTENT");

				System.out.println("Enter your note");
				in = new Scanner(System.in);
				String searchingNote = in.nextLine();
				findNotesRequest.setNote(searchingNote);

				FindNotesResponse findNoteResponse = (FindNotesResponse) controller.doRequest(findNotesRequest);

				if (findNoteResponse.getFindBook().size() == 0) {
					System.out.println("There are no notes!");
				} else {
					for (Note n : findNoteResponse.getFindBook()) {
						System.out.println(n.getDate() + " " + n.getNote());
					}
				}
				if (findNoteResponse.isErrorStatus() == true) {
					System.out.println(findNoteResponse.getErrorMessage());
				}
				break;

			case 3:
				FindNotesRequest findNotesRequestByDate = new FindNotesRequest();
				findNotesRequestByDate.setCommandName("FIND_NOTES_BY_DATE");

				System.out.println("Enter your date");
				in = new Scanner(System.in);
				String searchingDate = in.nextLine();
				findNotesRequestByDate.setDate(searchingDate);

				FindNotesResponse findNoteResponseByDate = (FindNotesResponse) controller
						.doRequest(findNotesRequestByDate);
				if (findNoteResponseByDate.getFindBook().size() == 0) {
					System.out.println("There are no notes!");
				} else {
					for (Note n : findNoteResponseByDate.getFindBook()) {
						System.out.println(n.getDate() + " " + n.getNote());
					}
				}
				if (findNoteResponseByDate.isErrorStatus() == true) {
					System.out.println(findNoteResponseByDate.getErrorMessage());
				}
				break;

			case 4:
				Request showRequest = new Request();
				showRequest.setCommandName("SHOW_NOTES");

				Response showResponse = controller.doRequest(showRequest);
				if (showResponse.isErrorStatus() == true) {
					System.out.println(showResponse.getErrorMessage());
				}
				break;

			case 5:
				WorkWithFileRequest workWithFileRequest = new WorkWithFileRequest();
				workWithFileRequest.setCommandName("SAVE_NOTEBOOK");
				System.out.println("Enter path");
				in = new Scanner(System.in);
				String path = in.nextLine();
				workWithFileRequest.setPath(path);
				Response saveResponse = controller.doRequest(workWithFileRequest);
				if (saveResponse.isErrorStatus() == true) {
					System.out.println(saveResponse.getErrorMessage());
				}
				break;

			case 6:
				WorkWithFileRequest workWithFileLoadRequest = new WorkWithFileRequest();
				workWithFileLoadRequest.setCommandName("LOAD_NOTEBOOK");
				System.out.println("Enter path");
				in = new Scanner(System.in);
				String pathLoad = in.nextLine();
				workWithFileLoadRequest.setPath(pathLoad);
				Response loadResponse = controller.doRequest(workWithFileLoadRequest);
				if (loadResponse.isErrorStatus() == true) {
					System.out.println(loadResponse.getErrorMessage());
				}
				break;

			default:
				System.out.println("Incorrect input");
				break;
			}

		}

	}

}
