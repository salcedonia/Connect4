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
package gui.console;

import logic.Token;
import logic.game.GameObserver;

public class ConsoleInterface implements GameObserver {

    private String _string;
    private Token[][] _grid;
    private int _width;
    private int _height;

    public ConsoleInterface(int width, int height) {
	_height = height;
	_width = width;
	_grid = new Token[_width][_height];
	reserBoard();
    }

    public void displayBoard() {

	_string = new String();

	_string = _string + " ";
	displaySeparator();

	for (int x = 0; x < _height; x++) {
	    for (int y = 0; y < _width; y++) {
		if (y == 0) {
		    _string = _string + "    | " + _grid[y][x].toChar() + " | ";
		} else {
		    _string = _string + _grid[y][x].toChar() + " | ";
		}
	    }
	    _string = _string + "\n ";
	    displaySeparator();
	}
	System.out.print(_string);
	System.out.println();
    }

    public void displaySeparator() {
	for (int i = 0; i < _width; i++) {

	    if (i == 0) {
		_string = _string + "    --- ";
	    } else {
		if (i == _width) {
		    _string = _string + "\n";
		} else {
		    _string = _string + "--- ";
		}
	    }
	}
	_string = _string + "\n";
    }

    public void displayTurn(Token turn) {
	if (turn == Token.YELLOW) {
	    System.out.println("     Play Yellow");
	}
	if (turn == Token.RED) {
	    System.out.println("     Play Red");
	}
	System.out.println();
    }

    public void displayGameSeparator() {
	for (int i = 0; i <= _width; i++) {
	    System.out.print("----");
	}
	System.out.print("-----");

	System.out.println();
	System.out.println();
    }

    public String getLastString() {
	return _string;
    }

    public void movePerformedInBoard(Token color, int column, int row) {
	displayGameSeparator();

	if (_grid[column][row] != Token.NONE) {

	    // Moves the rest of tokens in the column one row less
	    for (int i = _height - 1; i >= 1; i--) {
		_grid[column][i] = _grid[column][i - 1];
	    }
	}

	_grid[column][row] = color;

	displayTurn(color.getOpposite());
	displayBoard();
    }

    public void gameStarted() {
	displayGameSeparator();
	displayTurn(Token.YELLOW);
	reserBoard();
	displayBoard();
    }

    public void gameOver(Token winner) {
	if (winner != Token.NONE) {
	    System.out.println("    GAME OVER: " + winner.toString() + " WIN");
	} else {
	    System.out.println("    GAME OVER: DRAW");
	}
    }

    public void reserBoard() {
	for (int column = 0; column < _width; column++) {
	    for (int row = 0; row < _height; row++) {
		_grid[column][row] = Token.NONE;
	    }
	}
    }

    public void newGameConfigured() {

    }
}
