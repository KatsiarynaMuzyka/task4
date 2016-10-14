package by.tc.nb.controller;

import java.util.HashMap;
import java.util.Map;

import by.tc.nb.command.Command;
import by.tc.nb.command.impl.AddNewNote;
import by.tc.nb.command.impl.FindNotes;
import by.tc.nb.command.impl.LoadNotebookFromFile;
import by.tc.nb.command.impl.WriteNotebookInFile;
import by.tc.nb.command.impl.ShowAllNotes;

public class CommandHelper {

	private Map<String, Command> commands = new HashMap<String, Command>();

	public CommandHelper() {
		commands.put("ADD_NEW_NOTE", new AddNewNote());
		commands.put("FIND_NOTES_BY_CONTENT", new FindNotes());
		commands.put("FIND_NOTES_BY_DATE", new FindNotes());
		commands.put("SHOW_NOTES", new ShowAllNotes());
		commands.put("SAVE_NOTEBOOK", new WriteNotebookInFile());
		commands.put("LOAD_NOTEBOOK", new LoadNotebookFromFile());

	}

	public Command getCommand(String commandName) {
		Command command;

		command = commands.get(commandName);

		return command;

	}

}
