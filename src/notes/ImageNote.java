package notes;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageNote extends Note {
	
	private static final long serialVersionUID = -6150447927525015675L;
	private static int numberOfNotes;
	private JPanel contentPanel;
	private BufferedImage image;
	private int noteID;

	public ImageNote(Gui gui) {
		this(gui, "", "", "");
	}

	public ImageNote(Gui gui, String title, String imgDirectory, String imgName) {
		super(gui);

		contentPanel = new JPanel();
		contentPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					JFileChooser chooser = new JFileChooser();
					chooser.setAcceptAllFileFilterUsed(true);
					if (chooser.showOpenDialog(contentPanel) == JFileChooser.APPROVE_OPTION) {
						try {
							contentPanel.removeAll();
							contentPanel.revalidate();
							contentPanel.repaint();
							loadImage(chooser.getSelectedFile());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			}
		});

		if(!"".equals(imgName)){
			contentPanel.setSize(280, 280);
			File imgFile = new File(imgDirectory, imgName);
			try {
				loadImage(imgFile);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		numberOfNotes++;
		this.noteID = numberOfNotes;
		this.type = "image";
		this.titleField.setText(title);
		this.add(contentPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void loadImage(File imageFile) throws IOException {
		image = ImageIO.read(imageFile);
		setImage();
	}
	
	private void setImage() {
		Image resized = resizeImage();
		JLabel label = new JLabel(new ImageIcon(resized));
		contentPanel.add(label, BorderLayout.CENTER);
		contentPanel.revalidate();
		contentPanel.repaint();
	}
	
	private Image resizeImage() {
		int hImage = image.getHeight();
		int wImage = image.getWidth();
		int hPanel = contentPanel.getHeight();
		int wPanel = contentPanel.getWidth();
		double factor = 0;

		if (hImage - hPanel > wImage - wPanel) {
			factor = (double) wImage / hImage;
			hImage = hPanel;
			wImage = (int) (wPanel * factor);
		} else {
			factor = (double) hImage / wImage;
			wImage = wPanel;
			hImage = (int) (hPanel * factor);
		}

		Image resizedImage = image.getScaledInstance(wImage, hImage, Image.SCALE_DEFAULT);
		return resizedImage;
	}
	
	public BufferedImage getImage() {
		return image;
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
		data[7] = data[0]+data[1]+".jpg";
		return data;
	}
}