package notes;

import java.io.IOException;

public class Control {

	private Gui gui;
	private Data data;

	public Control() {
		this.data = new Data();
		this.gui = new Gui(this);
	}

	public void addNote(Note note) {
		data.addNote(note);
	}

	public void removeNote(Note note) {
		data.removeNote(note);
	}

	public void saveNotes(String path) throws IOException {
		data.saveNotes(path);
	}

	public void loadNotes(String path) throws IOException {
		gui.clear();
		data.clear();
		data.loadNotes(path, gui);
		for (Note note : data.getNotes()) {
			gui.addNote(note);
		}
	}

	public static void main(String[] args) {
		Control c = new Control();
	}
}
