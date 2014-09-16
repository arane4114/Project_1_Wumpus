import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;


public class TextVersionPanel extends JPanel implements Observer {
	private JTextArea text;
	public TextVersionPanel(){
		text = new JTextArea();
		setLayout(null);
		text.setSize(500,500);
		this.setPreferredSize(new Dimension(500,500));
		add(text);
	}

	@Override
	public void update(Observable o, Object unused) {
		Model model = (Model) o;
		text.setText(model.toString());
	}

}
