/*
 * @ - Author: Abhishek Rane
 * 
 * @ - Author: Bryce Hammond
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
 * GUI panel for text view of game.
 */
public class TextVersionPanel extends JPanel implements Observer {
	private JTextArea text;
	private JTextArea textMsg;

	/*
	 * Initializes two text areas for a text view of the game
	 * and current state updates.
	 */
	public TextVersionPanel() {
		this.setSize(500, 500);
		text = new JTextArea();
		setLayout(null);
		text.setSize(500, 300);
		this.setPreferredSize(new Dimension(500, 500));
		text.setFont(new Font("Courier", Font.BOLD, 20));
		add(text);

		textMsg = new JTextArea();
		setLayout(null);
		textMsg.setSize(500, 200);
		textMsg.setLocation(3, 305);
		textMsg.setFont(new Font("Courier", Font.BOLD, 14));
		add(textMsg, BorderLayout.SOUTH);
		textMsg.setLineWrap(true);
		textMsg.setWrapStyleWord(true);
	}

	/*
	 * Updates both text areas with current game map and state
	 * changes colors to match current room.
	 */
	@Override
	public void update(Observable o, Object unused) {
		Model model = (Model) o;
		if (model.isRunning()) {
			text.setText(model.toString());
		} else {
			text.setText(model.toStringShowAllRooms());
		}
		textMsg.setText(model.getCurrentState());

		if (model.getCell(model.getHunter()).isGoop()) {
			text.setForeground(Color.ORANGE);
			textMsg.setForeground(Color.ORANGE);
		} else if (model.getCell(model.getHunter()).isBlood()) {
			text.setForeground(Color.RED);
			textMsg.setForeground(Color.RED);
		} else if (model.getCell(model.getHunter()).isSlime()) {
			text.setForeground(Color.GREEN);
			textMsg.setForeground(Color.GREEN);
		} else {
			text.setForeground(Color.BLACK);
			textMsg.setForeground(Color.BLACK);
		}
	}
}
