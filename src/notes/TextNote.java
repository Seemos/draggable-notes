package notes;

import java.awt.BorderLayout;

import javax.swing.JTextArea;

public class TextNote extends Note{

	private static final long serialVersionUID = -5074932096984457022L;

	public TextNote(Gui gui) {
		super(gui);
		
		JTextArea contentArea = new JTextArea();
		contentArea.setLineWrap(true);
		contentArea.setWrapStyleWord(true);
		
		this.type = "text";
		this.add(contentArea, BorderLayout.CENTER);
		this.setVisible(true);
	}

}
