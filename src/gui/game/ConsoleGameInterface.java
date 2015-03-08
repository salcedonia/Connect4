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
package gui.game;

import gui.console.ConsoleInterface;
import gui.playagain.ConsolePlayAgain;

import java.util.Scanner;

import logic.AskPlayAgain;
import logic.game.Game;

public class ConsoleGameInterface implements GameInterface {

    private ConsoleInterface _interface;
    private Scanner _scanner;
    private int _width;
    private int _height;

    public ConsoleGameInterface(Scanner scanner, int width, int height) {
	_scanner = scanner;
	_width = width;
	_height = height;
    }

    public void setGame(Game game) {
	_interface = new ConsoleInterface(_width, _height);
	game.addObserver(_interface);
    }

    public AskPlayAgain getAskPlayAgain() {
	return new ConsolePlayAgain(_scanner);
    }

    public void terminateGame() {
    }
}
