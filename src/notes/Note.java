package notes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Note extends JPanel{
		
	private static final long serialVersionUID = 3968723015592708693L;
	private Gui gui;
	private Note self;
	protected JTextField titleField;
	protected String type;

	public Note(Gui gui) {
		this.gui = gui;
		this.self = this;
		this.type = "empty";
		
		this.setBackground(new Color(244, 222, 162));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.setLayout(new BorderLayout(10, 10));
		
		DragHandler dh = new DragHandler();
		this.addMouseListener(dh);
		this.addMouseMotionListener(dh);
		
		JPanel titlePanel = new JPanel(new BorderLayout());
		JButton deleteButton = new JButton("X");
		titleField = new JTextField();
		deleteButton.addActionListener(deleteListener);
		titlePanel.add(deleteButton, BorderLayout.EAST);
		titlePanel.add(titleField, BorderLayout.CENTER);
		
		this.add(titlePanel, BorderLayout.NORTH);
	}
	
	public String getType() {
		return this.type;
	}
	
	public String[] getRecreationData() {
		String[] data = new String[1];
		data[0] = type;
		return data;
	}
	
	public class DragHandler extends MouseAdapter {
	    private int offX;
	    private int offY;

	    @Override
	    public void mousePressed(MouseEvent e) {
	        offX = e.getX();
	        offY = e.getY();
	    }

	    @Override
	    public void mouseDragged(MouseEvent e) {
	    	Component component = e.getComponent();
	        int deltaX = e.getPoint().x - offX;;
	        int deltaY = e.getPoint().y - offY;
	        int currX = component.getX();
	        int currY = component.getY();
	        currX += deltaX;
	        currY += deltaY;
	        component.setLocation(currX, currY);
	    }
	}
	
	private ActionListener deleteListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			gui.removeNote(self);
		}
	};
}
