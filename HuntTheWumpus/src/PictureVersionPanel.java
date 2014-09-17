import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PictureVersionPanel extends JPanel implements Observer {
	private BufferedImage bloodImage;
	private BufferedImage goopImage;
	private BufferedImage groundImage;
	private BufferedImage slimeImage;
	private BufferedImage slimePitImage;
	private BufferedImage theHunterImage;
	private BufferedImage wumpusImage;

	public PictureVersionPanel() {
		this.setPreferredSize(new Dimension(600, 500));
		try {
			bloodImage = ImageIO.read(new File("Blood.png"));
			goopImage = ImageIO.read(new File("Goop.png"));
			groundImage = ImageIO.read(new File("Ground.png"));
			slimeImage = ImageIO.read(new File("Slime.png"));
			slimePitImage = ImageIO.read(new File("SlimePit.png"));
			theHunterImage = ImageIO.read(new File("TheHunter.png"));
			wumpusImage = ImageIO.read(new File("Wumpus.png"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Cannot load 1 or more image files",
				    "Loading error",
				    JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	@Override
	public void update(Observable o, Object unused) {
		Model model = (Model) o;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

	}

	public void forceRedraw() {
		repaint();
	}

}