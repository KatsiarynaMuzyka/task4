package by.tc.nb.bean;

import java.util.List;

import by.tc.nb.bean.entity.Note;

public class FindNotesResponse extends Response {
	private List<Note> findnotes;

	public List<Note> getFindBook() {
		return findnotes;
	}

	public void setFindBook(List<Note> findnotes) {
		this.findnotes = findnotes;
	}

	
}
