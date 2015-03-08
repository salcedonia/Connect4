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
package logic;

import utils.BoardUtils;
import exception.InvalidMove;
import junit.framework.TestCase;
import logic.Board;
import logic.Token;
import logic.strategy.PopOutTokenMove;
import logic.strategy.ConnectFourTokenMove;

public class BoardTest extends TestCase {

    public void testGetSlot() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());
	assertEquals("Fails when the board is empty", board.getSlot(0, 8),
		Token.NONE);

	try {

	    board.putToken(Token.YELLOW, 0, 0);
	    assertEquals("Fails upon non empty board and YELLOW tokens",
		    board.getSlot(0, 5), Token.YELLOW);
	    assertEquals("Fails upon non empty board in empty slot",
		    board.getSlot(1, 5), Token.NONE);

	    board.putToken(Token.RED, 1, 0);
	    assertEquals("Fails upon non empty board and RED tokens",
		    board.getSlot(1, 5), Token.RED);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}
    }

    public void testPutFourInARowToken() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());

	try {

	    board.putToken(Token.YELLOW, 6, 0);
	    assertEquals("Fails when putting the first token",
		    board.getSlot(6, board.getHeight() - 1), Token.YELLOW);

	    board.putToken(Token.RED, 6, 0);
	    assertEquals("Fails when putting the second token",
		    board.getSlot(6, board.getHeight() - 2), Token.RED);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}
    }

    public void testPutComplicateToken() {
	String boardCompStr[] = { "R   ", "R   ", "R   ", "Y   ", "Y   ",
		"Y   ", "RYYY" };

	Board boardComp = BoardUtils.buildBoard(boardCompStr, 4, 7,
		new PopOutTokenMove());

	try {

	    boardComp.putToken(Token.RED, 0, 0);
	    boardComp.putToken(Token.YELLOW, 1, 0);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}

	String boardResStr[] = { "R   ", "R   ", "R   ", "R   ", "Y   ",
		"YY  ", "YYYY" };

	Board boardRes = BoardUtils.buildBoard(boardResStr, 4, 7,
		new PopOutTokenMove());
	assertEquals("Falla al desplazar las fichas de columna llena",
		boardComp, boardRes);
    }

    public void testIsColumnFull() {
	String boardStr[] = { "Y      ", "Y      ", "Y      ", "R      ",
		"R      ", "R      " };

	Board board = BoardUtils.buildBoard(boardStr, 7, 6,
		new ConnectFourTokenMove());

	assertFalse("Fails upon invalid column", board.isColumnFull(-1));
	assertTrue("Fails upon full column", board.isColumnFull(0));
	assertFalse("Fails upon not full column", board.isColumnFull(1));
    }

    public void testIsValidColumn() {
	Board board = new Board();

	assertFalse("Fails upon invalid column", board.isValidColumn(-1));
	assertFalse("Fails upon invalid column",
		board.isValidColumn(board.getWidth()));

	assertTrue("Fails upon valid column", board.isValidColumn(0));
	assertTrue("Fails upon valid column",
		board.isValidColumn(board.getHeight() - 1));
    }

    public void testIsValidRow() {
	Board board = new Board();

	assertFalse("Fails upon invalid row", board.isValidRow(-1));
	assertFalse("Fails upon invalid row",
		board.isValidRow(board.getHeight()));

	assertTrue("Fails upon valid row", board.isValidRow(0));
	assertTrue("Fails upon valid row",
		board.isValidRow(board.getHeight() - 1));
    }

    public void testFullBoard() {
	class FullBoard extends Board {

	    FullBoard() {
		_tokens = 42;
	    }
	}

	Board board1 = new FullBoard();
	assertTrue("Fails upon full board", board1.isBoardFull());

	Board board2 = new Board();
	assertFalse("Fails upon not full board", board2.isBoardFull());
    }

    public void testGetTokens() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());
	assertTrue("Fails upon empty board", board.getTokens() == 0);

	try {

	    board.putToken(Token.YELLOW, 0, 0);
	    assertTrue("Fails upon board with one token",
		    board.getTokens() == 1);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}
    }

    public void testGetTokensInColumn() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());
	assertTrue("Fails upon empty column", board.getTokensInColumn(0) == 0);

	try {

	    board.putToken(Token.YELLOW, 0, 0);
	    assertTrue("Fails upon a board with one token",
		    board.getTokensInColumn(0) == 1);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}
    }

    public void testYellowHorizontalFourInARow() {
	Board board1 = new Board(7, 6, new ConnectFourTokenMove());
	for (int row = 0; row < board1.getHeight(); row++) {
	    for (int column = 0; column < board1.getWidth() - 3; column++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());

		assertFalse("Fails upon empty board",
			board.fourTokensConnectedHorizontally(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.fourTokensConnectedHorizontally(Token.RED));

		try {

		    for (int i = 0; i < row; i++) {

			board.putToken(Token.RED, 0, 0);
			board.putToken(Token.YELLOW, 1, 0);
			board.putToken(Token.RED, 2, 0);
			board.putToken(Token.YELLOW, 3, 0);
			board.putToken(Token.RED, 4, 0);
			board.putToken(Token.YELLOW, 5, 0);
			board.putToken(Token.RED, 6, 0);
		    }

		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.YELLOW, column + 1, 0);
		    board.putToken(Token.YELLOW, column + 2, 0);
		    board.putToken(Token.YELLOW, column + 3, 0);

		    assertTrue(
			    "Does not detect four in a row horizontally for YELLOW",
			    board.fourTokensConnectedHorizontally(Token.YELLOW));
		    assertFalse(
			    "Detects four in a row horizontally for the opposite turn",
			    board.fourTokensConnectedHorizontally(Token.RED));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testRedHorizontalFourInARow() {
	Board board1 = new Board();
	for (int row = 0; row < board1.getHeight(); row++) {
	    for (int column = 0; column < board1.getWidth() - 3; column++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());

		assertFalse("Fails upon empty board",
			board.fourTokensConnectedHorizontally(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.fourTokensConnectedHorizontally(Token.RED));

		try {

		    for (int i = 0; i < row; i++) {

			board.putToken(Token.RED, 0, 0);
			board.putToken(Token.YELLOW, 1, 0);
			board.putToken(Token.RED, 2, 0);
			board.putToken(Token.YELLOW, 3, 0);
			board.putToken(Token.RED, 4, 0);
			board.putToken(Token.YELLOW, 5, 0);
			board.putToken(Token.RED, 6, 0);
		    }

		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.RED, column + 1, 0);
		    board.putToken(Token.RED, column + 2, 0);
		    board.putToken(Token.RED, column + 3, 0);

		    assertTrue(
			    "Does not detect four in a row horizontally for RED",
			    board.fourTokensConnectedHorizontally(Token.RED));
		    assertFalse(
			    "Detects four in a row horizontally for the opposite turn",
			    board.fourTokensConnectedHorizontally(Token.YELLOW));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testNoneHorizontalFourInARow() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());
	assertFalse("Detects four in a row horizontally for NONE",
		board.fourTokensConnectedHorizontally(Token.NONE));
    }

    public void testYellowVerticalFourInARow() {
	Board board1 = new Board(7, 6, new ConnectFourTokenMove());
	for (int column = 0; column < board1.getWidth(); column++) {
	    for (int times = 0; times < board1.getHeight() - 3; times++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());

		assertFalse("Fails upon empty board",
			board.fourTokensConnected(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.fourTokensConnected(Token.RED));

		try {

		    for (int i = 0; i < times; i++) {
			board.putToken(Token.RED, column, 0);
		    }

		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.YELLOW, column, 0);

		    assertTrue(
			    "Does not detect four in a row vertically for YELLOW",
			    board.fourTokensConnectedVertically(Token.YELLOW));
		    assertFalse(
			    "Detects four in a row vertically for the opposite turn",
			    board.fourTokensConnectedVertically(Token.RED));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testRedVerticalFourInARow() {
	Board board1 = new Board(7, 6, new ConnectFourTokenMove());
	for (int column = 0; column < board1.getWidth(); column++) {
	    for (int times = 0; times < board1.getHeight() - 3; times++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());

		assertFalse("Fails upon empty board",
			board.fourTokensConnected(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.fourTokensConnected(Token.RED));

		try {

		    for (int i = 0; i < times; i++) {
			board.putToken(Token.YELLOW, column, 0);
		    }

		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.RED, column, 0);

		    assertTrue(
			    "Does not detect four in a row vertically for RED",
			    board.fourTokensConnectedVertically(Token.RED));
		    assertFalse(
			    "Detects four in a row vertically for the opposite turn",
			    board.fourTokensConnectedVertically(Token.YELLOW));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testNoneVerticalFourInARow() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());
	assertFalse("Detects four in a row vertically for NONE",
		board.fourTokensConnectedVertically(Token.NONE));
    }

    public void testYellowLeftDiagonalFourInARow() {
	Board board1 = new Board(7, 6, new ConnectFourTokenMove());
	for (int column = 0; column < board1.getWidth() - 3; column++) {
	    for (int times = 0; times < board1.getHeight() - 3; times++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());

		assertFalse("Fails upon empty board",
			board.connectedLeftDiagonally(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.connectedLeftDiagonally(Token.RED));

		try {

		    board.putToken(Token.YELLOW, column + 1, 0);
		    board.putToken(Token.RED, column + 2, 0);
		    board.putToken(Token.YELLOW, column + 2, 0);
		    board.putToken(Token.RED, column + 3, 0);
		    board.putToken(Token.YELLOW, column + 3, 0);
		    board.putToken(Token.RED, column + 3, 0);

		    for (int i = 0; i < times; i++) {
			board.putToken(Token.RED, column, 0);
			board.putToken(Token.YELLOW, column + 1, 0);
			board.putToken(Token.RED, column + 2, 0);
			board.putToken(Token.YELLOW, column + 3, 0);
		    }

		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.YELLOW, column + 1, 0);
		    board.putToken(Token.YELLOW, column + 2, 0);
		    board.putToken(Token.YELLOW, column + 3, 0);

		    assertTrue(
			    "Does not detect four in a row left diagonally for YELLOW",
			    board.connectedLeftDiagonally(Token.YELLOW));
		    assertFalse(
			    "Detects four in a row left diagonally for the opposite turn",
			    board.connectedLeftDiagonally(Token.RED));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testRedLeftDiagonalFourInARow() {
	Board board1 = new Board(7, 6, new ConnectFourTokenMove());
	for (int column = 0; column < board1.getWidth() - 3; column++) {
	    for (int times = 0; times < board1.getHeight() - 3; times++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());

		assertFalse("Fails upon empty board",
			board.connectedLeftDiagonally(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.connectedLeftDiagonally(Token.RED));

		try {

		    board.putToken(Token.RED, column + 1, 0);
		    board.putToken(Token.YELLOW, column + 2, 0);
		    board.putToken(Token.RED, column + 2, 0);
		    board.putToken(Token.YELLOW, column + 3, 0);
		    board.putToken(Token.RED, column + 3, 0);
		    board.putToken(Token.YELLOW, column + 3, 0);

		    for (int i = 0; i < times; i++) {
			board.putToken(Token.YELLOW, column, 0);
			board.putToken(Token.RED, column + 1, 0);
			board.putToken(Token.YELLOW, column + 2, 0);
			board.putToken(Token.RED, column + 3, 0);
		    }

		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.RED, column + 1, 0);
		    board.putToken(Token.RED, column + 2, 0);
		    board.putToken(Token.RED, column + 3, 0);

		    assertTrue(
			    "Does not detect four in a row left diagonally for RED",
			    board.connectedLeftDiagonally(Token.RED));
		    assertFalse(
			    "Detects four in a row left diagonally for the opposite turn",
			    board.connectedLeftDiagonally(Token.YELLOW));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testNoneLeftDiagonalFourInARow() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());
	assertFalse("Detects four in a row left diagonally for NONE",
		board.connectedLeftDiagonally(Token.NONE));
    }

    public void testYellowRightDiagonalFourInARow() {
	Board board1 = new Board(7, 6, new ConnectFourTokenMove());
	for (int column = 0; column < board1.getWidth() - 3; column++) {
	    for (int times = 0; times < board1.getHeight() - 3; times++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());
		assertFalse("Fails upon empty board",
			board.connectedRightDiagonally(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.connectedRightDiagonally(Token.RED));

		try {

		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.RED, column + 1, 0);
		    board.putToken(Token.YELLOW, column + 1, 0);
		    board.putToken(Token.RED, column + 2, 0);

		    for (int i = 0; i < times; i++) {
			board.putToken(Token.RED, column, 0);
			board.putToken(Token.YELLOW, column + 1, 0);
			board.putToken(Token.RED, column + 2, 0);
			board.putToken(Token.YELLOW, column + 3, 0);
		    }

		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.YELLOW, column + 1, 0);
		    board.putToken(Token.YELLOW, column + 2, 0);
		    board.putToken(Token.YELLOW, column + 3, 0);

		    assertTrue(
			    "Does not detect four in a row right diagonally for YELLOW.",
			    board.connectedRightDiagonally(Token.YELLOW));
		    assertFalse(
			    "Detects four in a row right diagonally for the opposite turn",
			    board.connectedRightDiagonally(Token.RED));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testRedRightDiagonalFourInARow() {
	Board board1 = new Board(7, 6, new ConnectFourTokenMove());
	for (int column = 0; column < board1.getWidth() - 3; column++) {
	    for (int times = 0; times < board1.getHeight() - 3; times++) {

		Board board = new Board(7, 6, new ConnectFourTokenMove());
		assertFalse("Fails upon empty board",
			board.connectedRightDiagonally(Token.YELLOW));
		assertFalse("Fails upon empty board",
			board.connectedRightDiagonally(Token.RED));

		try {

		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.YELLOW, column, 0);
		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.YELLOW, column + 1, 0);
		    board.putToken(Token.RED, column + 1, 0);
		    board.putToken(Token.YELLOW, column + 2, 0);

		    for (int i = 0; i < times; i++) {
			board.putToken(Token.YELLOW, column, 0);
			board.putToken(Token.RED, column + 1, 0);
			board.putToken(Token.YELLOW, column + 2, 0);
			board.putToken(Token.RED, column + 3, 0);
		    }

		    board.putToken(Token.RED, column, 0);
		    board.putToken(Token.RED, column + 1, 0);
		    board.putToken(Token.RED, column + 2, 0);
		    board.putToken(Token.RED, column + 3, 0);

		    assertTrue(
			    "Does not detect four in a row right diagonally for YELLOW.",
			    board.connectedRightDiagonally(Token.RED));
		    assertFalse(
			    "Detects four in a row right diagonally for the opposite turn",
			    board.connectedRightDiagonally(Token.YELLOW));

		} catch (InvalidMove ex) {

		    fail("Fails upon invalid move");
		}
	    }
	}
    }

    public void testNoneRightDiagonalFourInARow() {
	Board board = new Board(7, 6, new ConnectFourTokenMove());
	assertFalse("Detects four in a row right diagonally for NONE",
		board.connectedRightDiagonally(Token.NONE));
    }
}
