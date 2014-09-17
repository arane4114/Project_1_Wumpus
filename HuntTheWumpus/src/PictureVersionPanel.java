import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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

	private Model model;

	private final int DELTA_X = 50;
	private final int DELTA_Y = 50;
	private final int X_BASE = 10;
	private final int Y_BASE = 5;

	public PictureVersionPanel() {
		this.setPreferredSize(new Dimension(500, 500));
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
					"Cannot load 1 or more image files.", "Loading error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}

	@Override
	public void update(Observable o, Object unused) {
		Model model = (Model) o;
		this.model = model;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.fillRect(X_BASE, Y_BASE, 500, 500);
		for (Point p : this.model.getVisibleRooms()) {

			g.drawImage(groundImage, ((p.x * DELTA_X) + X_BASE),
					((p.y * DELTA_Y) + Y_BASE), null);

			if (model.getCell(p).getWumpus()) {
				g.drawImage(wumpusImage, ((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), null);
			} else if (model.getCell(p).getPit()) {
				g.drawImage(slimePitImage, ((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), null);
			} else if (model.getCell(p).getGoop()) {
				g.drawImage(goopImage, ((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), null);
			} else if (model.getCell(p).getBlood()) {
				g.drawImage(bloodImage, ((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), null);
			} else if (model.getCell(p).getSlime()) {
				g.drawImage(slimeImage, ((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), null);
			}
			
			if (model.getCell(p).getHunter()) {
				g.drawImage(theHunterImage, ((p.x * DELTA_X) + X_BASE),
						((p.y * DELTA_Y) + Y_BASE), null);
			}
			
		}

	}
}