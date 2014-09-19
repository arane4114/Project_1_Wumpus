/*
 * @ - Author: Abhishek Rane

 * @ - Author: Bryce Hammond
 */
import java.awt.Dimension;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

/*
 * Creates a panel of buttons allowing the user to move in four directions.
 * Heavily references section code.
 */
public class MoveButtonPanel extends JPanel {
	private Model gameModel; // the panel to control

	private BasicArrowButton up, down, left, right; // arrow buttons

	/*
	 * Action listener for movement buttons.
	 */
	private class ButtonMoveListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == up) {
				gameModel.hunterMove('w');
			} else if (arg0.getSource() == down) {
				gameModel.hunterMove('s');
			} else if (arg0.getSource() == left) {
				gameModel.hunterMove('a');
			} else {
				gameModel.hunterMove('d');
			}
		}
	}
	
	/*
	 * Creates buttons and links them to listener.
	 */
	public MoveButtonPanel(Model gameModel) {
		super();

		this.gameModel = gameModel;
		ActionListener buttonListener = new ButtonMoveListener();

		this.setLayout(new GridLayout(2, 3)); // grid layout
		this.setPreferredSize(new Dimension(75, 50)); // set size

		/* Create the arrow buttons */
		up = new BasicArrowButton(SwingConstants.NORTH);
		down = new BasicArrowButton(SwingConstants.SOUTH);
		left = new BasicArrowButton(SwingConstants.WEST);
		right = new BasicArrowButton(SwingConstants.EAST);

		/* Ignore this part, swing is really stupid sometimes */
		up.setFocusable(false);
		down.setFocusable(false);
		left.setFocusable(false);
		right.setFocusable(false);

		/* Add the action listener to each button */
		up.addActionListener(buttonListener);
		down.addActionListener(buttonListener);
		left.addActionListener(buttonListener);
		right.addActionListener(buttonListener);

		/* Add each button (and empty panels) */
		add(new JPanel());
		add(up);
		add(new JPanel());
		add(left);
		add(down);
		add(right);
	}

}