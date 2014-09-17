import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.Observable;
import java.util.Observer;


import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class PictureVersionPanel extends JPanel implements Observer {
	private JTextArea text;
	public PictureVersionPanel(){
	    text = new JTextArea();
	    text.setBackground(Color.RED);
	    text.setForeground(Color.WHITE);
	    setLayout(null);
	    text.setSize(600, 500);
	    text.setLocation(0, 0);
	    text.setFont(new Font("Courier", Font.BOLD, 23));
	    add(text);
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

}