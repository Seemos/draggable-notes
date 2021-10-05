package notes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Data {
	
	private ArrayList<Note> notes;
	private String activeDirectory;
	
	public Data() {
		notes = new ArrayList<>();
		activeDirectory = "";
	}
	
	public void clear() {
		this.notes = new ArrayList<>();
	}
	
	public void addNote(Note note) {
		this.notes.add(note);
	}
	
	public void removeNote(Note note) {
		if(!activeDirectory.equals("")) {
			String type = note.getRecreationData()[0];
			String id = note.getRecreationData()[1];
			File noteFile = new File(activeDirectory, type+id+".txt");
			noteFile.delete();
		}
		this.notes.remove(note);
	}
	
	public void saveNotes(String path) throws IOException {
		activeDirectory = path;
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
			else {
				String[] data = ((ImageNote)note).getRecreationData();
				String filename = data[0] + data[1] + ".txt";
				FileWriter writer = new FileWriter(new File(path, filename));
				for(String value : data) {
					writer.write(value + "\n");
				}
				File imageFile = new File(path, data[7]);
				ImageIO.write(((ImageNote)note).getImage(), "jpg", imageFile);
				writer.close();
			}
		}
	}
	
	public void loadNotes(String path, Gui gui) throws IOException{
		activeDirectory = path;
		ArrayList<File> files = getNoteFiles(path);
		for(File file : files) {
			FileReader reader = new FileReader(file);
	    	BufferedReader bReader = new BufferedReader(reader);
	    	ArrayList<String> lines = new ArrayList<>();
            for(String line; (line = bReader.readLine()) != null; ) {
                lines.add(line);
            }
            Note note = null;
            if(lines.get(0).equals("text")) {
            	String title = lines.get(6);
            	String content = lines.get(7).replace("\\n", "\n");
            	note = new TextNote(gui, title, content);
            }
            int x = Integer.parseInt(lines.get(2));
        	int y = Integer.parseInt(lines.get(3));
        	int h = Integer.parseInt(lines.get(4));
        	int w = Integer.parseInt(lines.get(5));
        	note.setBounds(x, y, w, h);
        	notes.add(note);
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
