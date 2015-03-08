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
package gui.factory;

import gui.game.ConsoleGameInterface;
import gui.game.GameInterface;

import java.util.Scanner;

import application.GameType;
import logic.player.ConsoleHumanPlayer;
import logic.player.Player;

public class ConsoleInterfaceFactory implements InterfaceFactory {

    private Scanner _scanner;

    public ConsoleInterfaceFactory() {
	_scanner = new Scanner(System.in);
    }

    public Player createHumanPlayer(boolean rowSensitive) {
	return new ConsoleHumanPlayer(_scanner, rowSensitive);
    }

    public GameInterface createGameInterface(GameType gameType, int width,
	    int height) {
	return new ConsoleGameInterface(_scanner, width, height);
    }
}
