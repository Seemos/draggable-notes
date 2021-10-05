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

	public ImageNote(Gui gui) {
		this(gui, "", "");
	}

	public ImageNote(Gui gui, String title, String imagePath) {
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

		numberOfNotes++;
		this.type = "image";
		this.titleField.setText(title);
		this.add(contentPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public Image getImage() {
		return image;
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
}