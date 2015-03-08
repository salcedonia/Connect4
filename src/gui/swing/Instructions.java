/*
 *  This program developed in Java contains three versions of the class Connect4 board game:
 *    - Connect4
 *    - PopOut
 *    - Gravity
 *  Further information about the rules and features can be found here:
 *  http://en.wikipedia.org/wiki/Connect_Four
 *    
 *  Likewise, it allows users to play against other users or against a computer player.
 *  Last but not least, it is available in both graphic and console mode.
 *    
 *  Copyright (C) 2015  Javier Salcedo
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import application.GameType;

public class Instructions {

    private static final String WINDOW_TITLE = "Instructions";
    private static final String RESOURCES_PATH = "/gui/swing/img/";
    private static final String HEADER_IMAGE = "miniConnect4.png";
    private static final String ACCEPT_IMAGE = "accept.png";

    private static final String CONNECT4 = "CONNECT4 \n\n"
	    + "The Connect 4 is a board game in which players, \n"
	    + "alternating turns, drop tokens in a vertical board of \n"
	    + "7 columns and 6 rows. \n"
	    + "The goal is to connect four pieces of your color, \n"
	    + "either vertically, horizontally or diagonally.\n\n";

    private static final String POP_OUT = "POP OUT\n\n"
	    + "The Pop Out is a variation of Connect4 in which players, \n"
	    + "alternating turns, drop tokens in a vertical board of \n"
	    + "4 columns and 7 rows. \n"
	    + "The peculiarity of the game is that when a column \n"
	    + "is full, when a token is dropped on it, it pops out \n"
	    + "the token lying on the base of the column from the board. \n"
	    + "This can cause that there are four in a row for both players \n"
	    + "or make the contrary turn win. \n"
	    + "The game ends when only one turn has four in a row though. \n\n";

    private static final String GRAVITY = "GRAVITY\n\n"
	    + "The gravity is is a variation of Connect4 in which players, alternating turns, drop \n"
	    + "their chips in a vertical board of 10 columns and 10 rows by default, \n"
	    + "but the user can specify the number of columns and rows of the same \n"
	    + "with the condition that these values are comprised between 5 and 15 \n"
	    + "(inclusive values).\n"
	    + "The goal is to connect four pieces of your color, either vertically, \n"
	    + "horizontally or diagonally. "
	    + "The peculiarity of the game is that the tokens are now attracted to \n"
	    + "all sides of the board and will go to that side that is closer to them.\n"
	    + "Thus, it implies that the token can be attracted by two, three or four \n"
	    + "sides of the board at a certain time."
	    + "In that case, the resulting position for the token will be computed \n"
	    + "as the sum of all the gravity forces applied on the token.\n";

    private String _message = "";

    public Instructions(GameType gameType) {
	if (gameType == GameType.CONNECT4) {
	    _message = CONNECT4;
	}
	if (gameType == GameType.POP_OUT) {
	    _message = POP_OUT;
	}
	if (gameType == GameType.GRAVITY) {
	    _message = GRAVITY;
	}
    }

    public JButton getButton(final JOptionPane optionPane, String text,
	    Icon icon) {
	final JButton button = new JButton(text, icon);
	ActionListener actionListener = new ActionListener() {

	    public void actionPerformed(ActionEvent e) {
		optionPane.setValue(button.getText());
		System.out.println(button.getText());
	    }
	};
	button.addActionListener(actionListener);
	return button;
    }

    public void show() {

	JFrame frame = new JFrame();
	JOptionPane optionPane = new JOptionPane();
	optionPane.setMessage(_message);
	optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);
	try {
	    optionPane.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + HEADER_IMAGE))));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	Icon icon = null;
	try {
	    icon = new ImageIcon(ImageIO.read(this.getClass().getResource(
		    RESOURCES_PATH + ACCEPT_IMAGE)));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	JButton jButton = getButton(optionPane, "Close", icon);
	optionPane.setOptions(new Object[] { jButton });
	JDialog dialog = optionPane.createDialog(frame, WINDOW_TITLE);
	dialog.setVisible(true);
    }
}