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
package gui.playagain;

import java.awt.HeadlessException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import logic.AskPlayAgain;

public class GUIPlayAgain implements AskPlayAgain {

    private static final String RESOURCES_PATH = "/gui/swing/img/";
    private static final String HEADER_IMAGE = "miniConnect4.png";
    private static final String ACCEPT_IMAGE = "accept.png";
    private static final String CLOSE_IMAGE = "close.png";

    public boolean playAgain() {
	Icon greenIcon = null;
	try {
	    greenIcon = new ImageIcon(ImageIO.read(this.getClass().getResource(
		    RESOURCES_PATH + ACCEPT_IMAGE)));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	Icon redIcon = null;
	try {
	    redIcon = new ImageIcon(ImageIO.read(this.getClass().getResource(
		    RESOURCES_PATH + CLOSE_IMAGE)));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	Object iconArray[] = { greenIcon, redIcon };

	int userOption = 0;
	try {
	    userOption = JOptionPane.showOptionDialog(
		    null,
		    "Do you want to play again?",
		    "Game Over",
		    JOptionPane.YES_NO_OPTION,
		    JOptionPane.QUESTION_MESSAGE,
		    new ImageIcon(ImageIO.read(this.getClass().getResource(
			    RESOURCES_PATH + HEADER_IMAGE))), iconArray,
		    iconArray[1]);
	} catch (HeadlessException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	if (userOption == 0) {
	    return true;
	} else {
	    if (userOption == 1) {
		System.exit(0);
	    }
	}
	return false;
    }
}
