package by.tc.nb.service.impl;

import by.tc.nb.bean.entity.Note;

import by.tc.nb.service.NoteBookService;
import by.tc.nb.service.exception.ServiceException;
import by.tc.nb.source.NoteBookProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class NoteBookServiceImpl implements NoteBookService {


    List<Note> notes= new ArrayList<>();
	List<Note> foundNotes = new ArrayList<>();

    @Override
    public void add(String note, String date) throws ServiceException {
        if (note == null || "".equals(note)) {
            throw new ServiceException("Wrong parameter!");
        }
        Note newNote = new Note(note, date);
        notes.add(newNote);
   
    }

    @Override
    public List<Note> findByContent(String note) throws ServiceException {
    	foundNotes.clear();
		for (Note n : notes) {
			if(n.getNote().contains(note)) {
				foundNotes.add(n);
			}
		}
		return foundNotes;
    }
    

    @Override
    public List<Note> findByDate(String date) throws ServiceException {
    	foundNotes.clear();
		for (Note note : notes) {
			if(note.getDate().equals(date)) {
				foundNotes.add(note);
			}
		}
		return foundNotes;
    }

	@Override
	public void writeNotebookInFile(String path) throws ServiceException, IOException, ClassNotFoundException {
		try {
			FileOutputStream writer = new FileOutputStream(path, true);
			ObjectOutputStream oos = new ObjectOutputStream(writer);
			oos.writeObject(notes);
			notes.clear();// delete recorded files from the memory
			oos.flush();
			oos.close();
			System.out.println("File created");
		} catch (IOException ex) {
			System.out.println("Incorrect path");
		}
	}
	

    @Override
    public void loadNoteBookFromFile(String path) throws ServiceException, IOException, ClassNotFoundException {
    	
    	try {
            FileInputStream reader = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(reader);

            for (Note i : (List<Note>) oin.readObject()) {
                notes.add(i);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");

        }
    }

    @Override
    public void show() throws ServiceException, IOException {
    	for(Note n:notes){
			System.out.print(n.getDate()+ " ");
			System.out.println(n.getNote());
		}
		if(notes.size()==0){
			System.out.println("There are no notes");
		}
		
		else{System.out.println("Notes count: " + notes.size());}
    }

}