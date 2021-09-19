package notes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class Gui extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1150016143897079156L;
	private Control control;
	private Gui self;
	private JPanel notePanel;
	
	public Gui(Control control) {
		this.control = control;
		this.self = this;
		this.setSize(800, 600);
		this.setResizable(true);
		this.buildMenu();
		this.buildContent();
		this.setVisible(true);
	}
	
	public void removeNote(Note note) {
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
		itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		itemLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
		menuFile.add(itemSave);
		menuFile.add(itemLoad);
		menuBar.add(menuFile);
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
					Note n = new TextNote(self);
					n.setBounds(e.getX(), e.getY(), 300, 300);
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
		
	}
	
}
