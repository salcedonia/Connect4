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
package main;

import application.*;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import gui.swing.ConfigureGameWindow;
import gui.swing.SplashScreen;

public class Main {

    static CommandLineParser _commandLineParser;

    public static void main(String[] args) {
	_commandLineParser = new CommandLineParser(args);
	if (_commandLineParser.areParametersValid()) {
	    if (_commandLineParser.isConsole()) {
		runConsole();
	    } else if (_commandLineParser.isGui()) {
		runGUI();
	    }
	}
    }

    public static void runConsole() {
	Application app = new Application();
	if (app.init(_commandLineParser.getGameType(),
		_commandLineParser.getGameMode(),
		_commandLineParser.getRedPlayer(),
		_commandLineParser.getYellowPlayer(),
		_commandLineParser.getWidth(), _commandLineParser.getHeight())) {
	    app.run();
	}
    }

    public static void runGUI() {
	setLookAndFeel();

	SplashScreen splash = new SplashScreen(5000);
	splash.showSplash();

	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    public void run() {

		// Shows the window for configuring a game
		ConfigureGameWindow optionsWindow = new ConfigureGameWindow(
			null, true, null);
		optionsWindow.showWindow();
	    }
	});
    }

    private static void setLookAndFeel() {
	try {
	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (Exception e) {
	    try {
		UIManager.setLookAndFeel(UIManager
			.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	    } catch (InstantiationException e1) {
		e1.printStackTrace();
	    } catch (IllegalAccessException e1) {
		e1.printStackTrace();
	    } catch (UnsupportedLookAndFeelException e1) {
		e1.printStackTrace();
	    }
	}
    }
}
