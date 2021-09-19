package notes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class Gui extends JFrame implements ActionListener{
	
	private Control control;
	
	public Gui(Control control) {
		this.control = control;
		this.setSize(800, 600);
		this.setResizable(true);
		this.buildMenu();
		this.buildContent();
		this.setVisible(true);
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
		JPanel notePanel = new JPanel();
		notePanel.setPreferredSize(new Dimension(2000, 2000));
		JScrollPane pane = new JScrollPane(notePanel);
		this.add(pane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
