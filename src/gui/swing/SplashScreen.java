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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SplashScreen extends JWindow {

    private static final long serialVersionUID = 1L;
    private static final String RESOURCES_PATH = "/gui/swing/img/";

    int _duration;

    public SplashScreen(int duration) {
	_duration = duration;
    }

    public void showSplash() {
	JPanel content = (JPanel) getContentPane();
	content.setBackground(Color.white);

	try {

	    // Build the splash screen
	    JLabel label = new JLabel(new ImageIcon(ImageIO.read(this
		    .getClass()
		    .getResource(RESOURCES_PATH + "SplashScreen.png"))));

	    content.add(label, BorderLayout.CENTER);
	    content.setBorder(BorderFactory.createLineBorder(Color.white, 0));

	    // Set the window's bounds, centering the window
	    int width = 450;
	    int height = 300;
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (screen.width - width) / 2;
	    int y = (screen.height - height) / 2;
	    setBounds(x, y, width, height);

	    // Display it
	    setVisible(true);

	    // Wait a little while, maybe while loading resources
	    try {
		Thread.sleep(_duration);
	    } catch (Exception e) {
	    }

	    // Hides it
	    setVisible(false);

	} catch (IOException e1) {
	    e1.printStackTrace();
	}
    }
}