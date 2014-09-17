import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextVersionPanel extends JPanel implements Observer {
	private JTextArea text;
	private JTextArea textMsg;

	public TextVersionPanel() {
		this.setSize(600, 500);
		text = new JTextArea();
		setLayout(null);
		text.setSize(600, 300);
		this.setPreferredSize(new Dimension(500, 500));
		text.setFont(new Font("Courier", Font.BOLD, 24));
		add(text);

		textMsg = new JTextArea();
		setLayout(null);
		textMsg.setSize(600, 200);
		textMsg.setLocation(3, 305);
		textMsg.setFont(new Font("Courier", Font.BOLD, 17));
		add(textMsg, BorderLayout.SOUTH);
		textMsg.setLineWrap(true);
		textMsg.setWrapStyleWord(true);
	}

	@Override
	public void update(Observable o, Object unused) {
		Model model = (Model) o;
		if (model.isRunning()) {
			text.setText(model.toString());
		} else {
			text.setText(model.toStringShowAllRooms());
		}
		textMsg.setText(model.getCurrentState());

		if (model.getCell(model.getHunter()).getGoop()) {
			text.setForeground(Color.ORANGE);
			textMsg.setForeground(Color.ORANGE);
		} else if (model.getCell(model.getHunter()).getBlood()) {
			text.setForeground(Color.RED);
			textMsg.setForeground(Color.RED);
		} else if (model.getCell(model.getHunter()).getSlime()) {
			text.setForeground(Color.GREEN);
			textMsg.setForeground(Color.GREEN);
		} else {
			text.setForeground(Color.BLACK);
			textMsg.setForeground(Color.BLACK);
		}
	}

	public void forceRedraw() {
		repaint();
	}

}
