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

public class AboutUs {

    private static final String WINDOW_TITLE = "About";
    private static final String RESOURCES_PATH = "/gui/swing/img/";
    private static final String HEADER_IMAGE = "splashScreen.png";
    private static final String ACCEPT_IMAGE = "accept.png";

    public AboutUs() {

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
	optionPane.setMessage("");
	optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
	try {
	    optionPane.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + HEADER_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
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