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
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import application.Controller;
import logic.Token;
import logic.game.GameObserver;

public class MenuBar extends JMenuBar implements GameObserver {

    private static final long serialVersionUID = 1L;

    private static final String RESOURCES_PATH = "/gui/swing/img/";
    private static final String FILE_IMAGE = "file.png";
    private static final String HELP_IMAGE = "help.png";
    private static final String ABOUT_US_IMAGE = "aboutUs.png";
    private static final String TERMINATE_IMAGE = "terminate.png";
    private static final String INSTRUCTIONS_IMAGE = "instructions.png";
    private static final String CONFIGURE_IMAGE = "configure.png";

    private static final String CONFIGURE_GAME = "Configure Game";
    private static final String TERMINATE_GAME = "Terminate Game";
    private static final String ABOUT_US = "About";
    private static final String INSTRUCTIONS = "Instructions";
    private static final String FILE = "File";
    private static final String HELP = "Help";

    private Window _window;
    private JMenu _fileMenu;
    private JMenu _helpMenu;
    private JMenuItem _aboutUsMenuItem;
    private JMenuItem _configureGameMenuItem;
    private JMenuItem _terminateGameMenuItem;
    private JMenuItem _instructionsMenuItem;
    private Controller _controller;

    private boolean _isGameOver;

    public MenuBar(Controller controller) {
	setController(controller);

	_fileMenu = new JMenu(FILE);
	_fileMenu.setMnemonic('A');
	try {
	    _fileMenu.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + FILE_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	add(_fileMenu);

	_configureGameMenuItem = new JMenuItem(CONFIGURE_GAME);
	_configureGameMenuItem.setMnemonic('C');
	try {
	    _configureGameMenuItem
		    .setIcon(new ImageIcon(ImageIO.read(this.getClass()
			    .getResource(RESOURCES_PATH + CONFIGURE_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	_fileMenu.add(_configureGameMenuItem);

	_terminateGameMenuItem = new JMenuItem(TERMINATE_GAME);
	_terminateGameMenuItem.setMnemonic('T');
	try {
	    _terminateGameMenuItem
		    .setIcon(new ImageIcon(ImageIO.read(this.getClass()
			    .getResource(RESOURCES_PATH + TERMINATE_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	_fileMenu.add(_terminateGameMenuItem);

	_helpMenu = new JMenu(HELP);
	_helpMenu.setMnemonic('y');
	try {
	    _helpMenu.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + HELP_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	add(_helpMenu);

	_instructionsMenuItem = new JMenuItem(INSTRUCTIONS);
	_instructionsMenuItem.setMnemonic('I');
	try {
	    _instructionsMenuItem.setIcon(new ImageIcon(ImageIO.read(this
		    .getClass()
		    .getResource(RESOURCES_PATH + INSTRUCTIONS_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	_helpMenu.add(_instructionsMenuItem);

	_aboutUsMenuItem = new JMenuItem(ABOUT_US);
	_aboutUsMenuItem.setMnemonic('a');
	try {
	    _aboutUsMenuItem.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + ABOUT_US_IMAGE))));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	_helpMenu.add(_aboutUsMenuItem);

	_configureGameMenuItem.addActionListener(new MenuListener());
	_terminateGameMenuItem.addActionListener(new MenuListener());
	_instructionsMenuItem.addActionListener(new MenuListener());
	_aboutUsMenuItem.addActionListener(new MenuListener());

	_terminateGameMenuItem.setEnabled(true);
    }

    class MenuListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    String actionCommand = e.getActionCommand();
	    if (e.getSource() instanceof JMenuItem) {

		if (CONFIGURE_GAME.equals(actionCommand)) {
		    ConfigureGameWindow optionsWindow = new ConfigureGameWindow(
			    _controller, false, _window);
		    optionsWindow.showWindow();
		}

		if (TERMINATE_GAME.equals(actionCommand)) {
		    _isGameOver = true;
		    _controller.terminateGameRequest();
		}

		if (INSTRUCTIONS.equals(actionCommand)) {
		    if (!_isGameOver) {
			_controller.instructionsRequest();
		    }
		}

		if (ABOUT_US.equals(actionCommand)) {
		    if (!_isGameOver) {
			_controller.aboutUsRequest();
		    }
		}
	    }
	}
    }

    public void movePerformedInBoard(Token token, int column, int row) {
    }

    public void gameStarted() {
	_isGameOver = false;
	_terminateGameMenuItem.setEnabled(true);
    }

    public void gameOver(Token winner) {
	_isGameOver = true;
	_terminateGameMenuItem.setEnabled(true);
    }

    public void setController(Controller controller) {
	_controller = controller;
    }

    public void setWindow(Window window) {
	_window = window;
    }
}