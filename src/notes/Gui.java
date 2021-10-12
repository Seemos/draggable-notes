package notes;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class Gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1150016143897079156L;
	private Control control;
	private Gui self;
	private JPanel notePanel;
	private String mode;

	public Gui(Control control) {
		this.control = control;
		this.self = this;
		this.mode = "text";
		this.setSize(800, 600);
		this.setResizable(true);
		this.buildMenu();
		this.buildContent();
		this.setVisible(true);
	}

	public void clear() {
		for (Component component : notePanel.getComponents()) {
			notePanel.remove(component);
		}
		notePanel.revalidate();
		notePanel.repaint();
	}

	public void addNote(Note note) {
		String type = note.getType();
		if (type.equals("text")){
			notePanel.add((TextNote) note);
		}
		else if(type.equals("image")){
			notePanel.add((ImageNote) note);
		}
		notePanel.revalidate();
		notePanel.repaint();
	}

	public void removeNote(Note note) {
		control.removeNote(note);
		notePanel.remove(note);
		notePanel.revalidate();
		notePanel.repaint();
	}

	private void buildMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenuItem itemSave = new JMenuItem("Save");
		JMenuItem itemLoad = new JMenuItem("Open");
		itemSave.addActionListener(this);
		itemLoad.addActionListener(this);
		itemSave.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		itemLoad.setAccelerator(
				KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		menuFile.add(itemSave);
		menuFile.add(itemLoad);
		menuBar.add(menuFile);

		String[] modeSeletions = { "text", "image" };
		JComboBox<String> modeSelection = new JComboBox<>(modeSeletions);
		modeSelection.setActionCommand("changeMode");
		modeSelection.setSelectedIndex(0);
		modeSelection.addActionListener(this);
		menuBar.add(modeSelection);
		setJMenuBar(menuBar);
	}

	private void buildContent() {
		JPanel notePanel = new JPanel(null);
		notePanel.setName("notePanel");
		notePanel.setPreferredSize(new Dimension(2000, 2000));
		notePanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Note n = null;
					switch(mode) {
					case "text":
						n = new TextNote(self);
						break;
					case "image":
						n = new ImageNote(self);
						break;
					}
					n.setBounds(e.getX(), e.getY(), 300, 300);
					control.addNote(n);
					notePanel.add(n);
					notePanel.revalidate();
					notePanel.repaint();
				}
			}
		});
		JScrollPane pane = new JScrollPane(notePanel);
		this.setContentPane(pane);
		this.notePanel = notePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "changeMode") {
			mode = (String) ((JComboBox<String>) (e.getSource())).getSelectedItem();
		} else {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				try {
					if (e.getActionCommand() == "Save") {
						control.saveNotes(chooser.getSelectedFile().getPath());
					} else {
						control.loadNotes(chooser.getSelectedFile().getPath());
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
