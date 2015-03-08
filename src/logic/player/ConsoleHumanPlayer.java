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
package logic.player;

import java.util.Scanner;

import logic.Position;
import logic.Token;

public class ConsoleHumanPlayer implements Player {

    private boolean _rowSensitive;
    private Scanner _scanner;

    public ConsoleHumanPlayer(Scanner scanner, boolean rowSensitive) {
	_rowSensitive = rowSensitive;
	_scanner = scanner;
    }

    public Position getMove(Token[][] board) {
	int column = getColumn(board.length), row = 0;

	if (_rowSensitive) {
	    row = getRow(board[0].length);
	}
	return new Position(column, row);
    }

    public int getColumn(int width) {
	String string = null;
	int column = 0;

	do {
	    try {

		System.out.printf("    Enter the column (0-%1$d): ", width - 1);

		string = _scanner.nextLine();
		column = Integer.parseInt(string);

		if (!isValidColumn(column, width)) {
		    System.err.println("    -> Invalid column.");
		    System.err.println();
		}
		System.out.println();

	    } catch (NumberFormatException ex) {

		System.err.println("    -> Invalid column format.");
		System.err.println();
		column = -1;
	    }

	} while (!isValidColumn(column, width));

	return column;
    }

    public int getRow(int height) {
	String string = null;
	int row = 0;

	do {
	    try {

		System.out.printf("    Enter the row (0-%1$d): ", height - 1);

		string = _scanner.nextLine();
		row = Integer.parseInt(string);

		if (!isValidRow(row, height)) {
		    System.err.println("    -> Invalid row.");
		    System.err.println();
		}
		System.out.println();

	    } catch (NumberFormatException ex) {

		System.err.println("    -> Invalid row format.");
		System.err.println();
		row = -1;
	    }

	} while (!isValidRow(row, height));

	return row;
    }

    public boolean isValidColumn(int column, int width) {
	return (column >= 0 && column < width);
    }

    public boolean isValidRow(int row, int height) {
	return (row >= 0 && row < height);
    }
}
