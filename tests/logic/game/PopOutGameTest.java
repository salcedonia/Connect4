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
package logic.game;

import java.util.Vector;

import utils.BoardUtils;
import junit.framework.TestCase;
import logic.Position;
import logic.Token;
import logic.game.PopOutGame;
import logic.game.Game;
import logic.game.GameObserver;
import logic.strategy.PopOutTokenMove;

public class PopOutGameTest extends TestCase {

    public void testWinner() {
	class CurrentTurnWinner extends PopOutGame {

	    CurrentTurnWinner() {
		String board[] = { "    ", "    ", "    ", "Y   ", "YR  ",
			"YR  ", "YR  " };

		_board = BoardUtils.buildBoard(board, 4, 7,
			new PopOutTokenMove());
		_observers = new Vector<GameObserver>();
		_turn = Token.YELLOW;
		_winner = Token.YELLOW;
	    }
	}

	class OppositeTurnWinner extends PopOutGame {

	    OppositeTurnWinner() {
		String board[] = { "Y   ", "R   ", "R   ", "R   ", "YR  ",
			"RR  ", "YRRR" };

		_board = BoardUtils.buildBoard(board, 4, 7,
			new PopOutTokenMove());
		_observers = new Vector<GameObserver>();
		_turn = Token.YELLOW;
		_winner = Token.NONE;
	    }
	}

	Game game1 = new CurrentTurnWinner();
	assertTrue("Fails when YELLOW wins", game1.winner()
		.equals(Token.YELLOW));

	Game game2 = new OppositeTurnWinner();
	assertTrue("Returns a winner when the game has not yet finished", game2
		.winner().equals(Token.NONE));

	game2.setToken(new Position(0, 0));
	assertTrue("Fails when RED wins", game2.winner().equals(Token.RED));
    }

    public void testGameOver() {
	class GameOverWithTwoFourInARow extends PopOutGame {

	    GameOverWithTwoFourInARow() {
		String board[] = { "Y   ", "Y   ", "Y   ", "Y   ", "R   ",
			"R   ", "RRRR" };

		_board = BoardUtils.buildBoard(board, 4, 7,
			new PopOutTokenMove());
		_observers = new Vector<GameObserver>();
		_turn = Token.YELLOW;
	    }
	}

	class GameOverYellowWinner extends PopOutGame {

	    GameOverYellowWinner() {
		String board[] = { "    ", "    ", "    ", "Y   ", "YR  ",
			"YR  ", "YR  " };

		_board = BoardUtils.buildBoard(board, 4, 7,
			new PopOutTokenMove());
		_observers = new Vector<GameObserver>();
		_turn = Token.YELLOW;
	    }
	}

	class GameOverRedWinner extends PopOutGame {

	    GameOverRedWinner() {
		String board[] = { "    ", "    ", "    ", "Y   ", "Y   ",
			"Y   ", "RRRR" };

		_board = BoardUtils.buildBoard(board, 4, 7,
			new PopOutTokenMove());
		_observers = new Vector<GameObserver>();
		_turn = Token.RED;
	    }
	}

	Game game1 = new PopOutGame();
	game1.startGame();

	assertFalse("Return true when the game is not yet over",
		game1.isGameOver());

	Game game2 = new GameOverWithTwoFourInARow();
	assertFalse(
		"The game does not have to finish when there are two winners",
		game2.isGameOver());

	Game game3 = new GameOverYellowWinner();
	assertTrue("Fails when YELLOW has made four in a row",
		game3.isGameOver());

	Game game4 = new GameOverRedWinner();
	assertTrue("Fails when ROW has made four in a row", game4.isGameOver());
    }

    public void testPutToken() {
	Game game = new PopOutGame();
	game.startGame();

	game.setToken(new Position(4, 0));
	assertEquals("Changes the turn upon invalid move", game.getTurn(),
		Token.YELLOW);

	game.setToken(new Position(0, 0));
	assertEquals("Does not change the turn upon correct move",
		game.getTurn(), Token.RED);
    }

    public void testGetRows() {
	Game game = new PopOutGame();
	game.startGame();
	assertEquals(
		"Fails when returning the number of rows of the board in a Complicate game",
		game.getRows(), 7);
    }

    public void testGetColumns() {
	Game game = new PopOutGame();
	game.startGame();
	assertEquals(
		"Fails when returning the number of columns of the board in a Complicate game",
		game.getColumns(), 4);
    }
}
