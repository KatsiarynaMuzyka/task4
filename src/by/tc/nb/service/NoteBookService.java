package by.tc.nb.service;

import by.tc.nb.bean.entity.Note;
import by.tc.nb.service.exception.ServiceException;

import java.io.IOException;
import java.util.List;



public interface NoteBookService {
	
	
    void add(String note, String date) throws ServiceException;

    List<Note> findByContent(String note) throws ServiceException;

    List<Note> findByDate(String date) throws ServiceException;

    void writeNotebookInFile(String path) throws ServiceException, IOException, ClassNotFoundException;

    void loadNoteBookFromFile(String path) throws ServiceException, IOException, ClassNotFoundException;

    void show() throws ServiceException, IOException;

}