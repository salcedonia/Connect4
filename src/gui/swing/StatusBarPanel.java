package gui.swing;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Token;
import logic.game.GameObserver;

public class StatusBarPanel extends JPanel implements GameObserver {

    private static final long serialVersionUID = 1L;
    private static final String RESOURCES_PATH = "/gui/swing/img/";
    private static final String YELLOW_IMAGE = "yellowSingle.png";
    private static final String RED_IMAGE = "redSingle.png";

    JLabel _labelYellow = new JLabel();
    JLabel _labelRed = new JLabel();
    JLabel _turnLabel = new JLabel();

    public StatusBarPanel() {

	setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.BOTH;
	c.anchor = GridBagConstraints.WEST;
	c.gridx = 0;
	c.gridy = 0;
	c.insets = new Insets(0, 0, 0, 10);
	_turnLabel = new JLabel("Now it's turn for...");
	Font font = new Font("inherit", Font.BOLD, 14);
	_turnLabel.setFont(font);
	add(_turnLabel, c);

	try {
	    _labelYellow.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + YELLOW_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	c.gridx = 1;
	add(_labelYellow, c);

	try {
	    _labelRed.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + RED_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	add(_labelRed, c);

	setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    public void gameStarted() {
	_labelYellow.setVisible(true);
	_labelRed.setVisible(false);
	_turnLabel.setText("Now it's turn for...");
    }

    public void gameOver(Token winner) {

	_turnLabel.setText("And the winner is...");

	if (winner == Token.YELLOW) {
	    _labelYellow.setVisible(true);
	    _labelRed.setVisible(false);
	} else if (winner == Token.RED) {
	    _labelYellow.setVisible(false);
	    _labelRed.setVisible(true);
	} else {
	    _turnLabel.setText("Draw!");
	}
    }

    public void movePerformedInBoard(Token color, int column, int row) {
	if (color.getOpposite() == Token.YELLOW) {
	    _labelYellow.setVisible(true);
	    _labelRed.setVisible(false);
	} else if (color.getOpposite() == Token.RED) {
	    _labelYellow.setVisible(false);
	    _labelRed.setVisible(true);
	}
    }
}
