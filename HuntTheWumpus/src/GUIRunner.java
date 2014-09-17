import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class GUIRunner extends JFrame {
	private Model gameModel;
	private MoveButtonPanel moveButtons;
	private ShootButtonPanel shootButtons;

	private JTabbedPane tabbedPanels;
	private TextVersionPanel textPanel = new TextVersionPanel();
	private PictureVersionPanel picturePanel = new PictureVersionPanel();
	private JButton resetButton = new JButton();

	public GUIRunner() {
		this.gameModel = new Model();
		this.gameModel.startNewGame();

		setTitle("Hunt The Wumpus");
		setSize(530, 638);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tabbedPanels = new JTabbedPane();
		tabbedPanels.add(picturePanel, "Graphical Veiw");
		tabbedPanels.add(textPanel, "Text Veiw");
		this.add(tabbedPanels, BorderLayout.CENTER);

		JPanel bottomLeft = new JPanel();
		JLabel moveLabel = new JLabel("Move");
		moveButtons = new MoveButtonPanel(gameModel);
		bottomLeft.add(moveLabel, BorderLayout.EAST);
		bottomLeft.add(moveButtons, BorderLayout.WEST);

		JPanel bottomRight = new JPanel();
		JLabel shootLabel = new JLabel("Shoot");
		shootButtons = new ShootButtonPanel(gameModel);
		bottomRight.add(shootLabel, BorderLayout.EAST);
		bottomRight.add(shootButtons, BorderLayout.WEST);

		resetButton.setText("Restart");
		resetButton.addActionListener(new resetButtonListener());

		JPanel bottom = new JPanel();
		bottom.add(bottomLeft, BorderLayout.EAST);
		bottom.add(resetButton, BorderLayout.CENTER);
		bottom.add(bottomRight, BorderLayout.WEST);
		this.add(bottom, BorderLayout.SOUTH);

		gameModel.addObserver((Observer) textPanel);
		gameModel.addObserver((Observer) picturePanel);

		setVisible(true);
		this.gameModel.forceUpdate();
	}


	private class resetButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			gameModel.startNewGame();
			gameModel.forceUpdate();
		}
	}

	public static void main(String[] args) {
		new GUIRunner();
	}

}
