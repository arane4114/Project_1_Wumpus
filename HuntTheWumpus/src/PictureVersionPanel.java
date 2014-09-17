import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

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
		text.setText(model.toString());
	}

}