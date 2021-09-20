package notes;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

public class TextNote extends Note{

	private static final long serialVersionUID = -5074932096984457022L;
	private static int numberOfNotes;
	private JTextArea contentArea;
	private int noteID;

	public TextNote(Gui gui) {
		this(gui, "", "");
	}
	
	public TextNote(Gui gui, String title, String content) {
		super(gui);
		
		contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		
		this.titleField.setText(title);
		this.contentArea.setText(content);
		
		numberOfNotes++;
		this.noteID = numberOfNotes;
		this.type = "text";
		this.add(contentArea, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public String[] getRecreationData() {
		String[] data = new String[8];
		data[0] = type;
		data[1] = Integer.toString(noteID);
		data[2] = Integer.toString(getX());
		data[3] = Integer.toString(getY());
		data[4] = Integer.toString(getHeight());
		data[5] = Integer.toString(getWidth());
		data[6] = titleField.getText();
		data[7] = contentArea.getText().replace("\n", "\\n");
		return data;
	}
}
