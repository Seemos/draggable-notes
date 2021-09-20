package notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Data {
	
	private ArrayList<Note> notes;
	
	public Data() {
		notes = new ArrayList<>();
	}
	
	public void clear() {
		this.notes = new ArrayList<>();
	}
	
	public void addNote(Note note) {
		this.notes.add(note);
	}
	
	public void removeNote(Note note) {
		this.notes.remove(note);
	}
	
	public void saveNotes(String path) throws IOException {
		for(Note note : notes) {
			String type = note.getRecreationData()[0];
			if(type.equals("text")) {
				String[] data = ((TextNote)note).getRecreationData();
				String filename = data[0] + data[1] + ".txt";
				FileWriter writer = new FileWriter(new File(path, filename));
				for(String value : data) {
					writer.write(value + "\n");
				}
				writer.close();
			}
		}
	}
	
	public void loadNotes(String path, Gui gui) throws IOException{
		ArrayList<File> files = getNoteFiles(path);
		for(File file : files) {
			FileReader reader = new FileReader(file);
	    	BufferedReader bReader = new BufferedReader(reader);
	    	ArrayList<String> lines = new ArrayList<>();
            for(String line; (line = bReader.readLine()) != null; ) {
                lines.add(line);
            }
            if(lines.get(0).equals("text")) {
            	int x = Integer.parseInt(lines.get(2));
            	int y = Integer.parseInt(lines.get(3));
            	int h = Integer.parseInt(lines.get(4));
            	int w = Integer.parseInt(lines.get(5));
            	String title = lines.get(6);
            	String content = lines.get(7).replace("\\n", "\n");
            	TextNote note = new TextNote(gui, title, content);
            	note.setBounds(x, y, w, h);
            	notes.add(note);
            }
	    }
	}
	
	private ArrayList<File> getNoteFiles(String path) {
		File folder = new File(path);
		File[] files = folder.listFiles();
		ArrayList<File> noteFiles = new ArrayList<>();
		for (File file : files) {
		    if (file.isFile() && file.getName().endsWith(".txt")) {
		        noteFiles.add(file);
		    }
		}
		return noteFiles;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
}
