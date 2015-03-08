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

import utils.*;
import exception.InvalidMove;
import junit.framework.TestCase;
import logic.Board;
import logic.Position;
import logic.Token;
import logic.game.ConnectFourGame;
import logic.game.Game;
import logic.game.GameObserver;
import logic.strategy.ConnectFourTokenMove;

public class ConnectFourGameTest extends TestCase {

  public void testChangeTurn() {
    Game game = new ConnectFourGame();
    game.startGame();

    game.changeTurn();
    assertEquals("Does not change from YELLOW to RED", game.getTurn(),
        Token.RED);

    game.changeTurn();
    assertEquals("Does not change from RED to YELLOW", game.getTurn(),
        Token.YELLOW);
  }

  public void testGetBoard() {
    Game game = new ConnectFourGame();
    game.startGame();

    Board board = game.getBoard();
    assertTrue("Fails upon empty board", board.equals(game.getBoard()));

    game.setToken(new Position(0, 0));
    try {

      board.putToken(Token.YELLOW, 0, 0);
      assertTrue("Fails upon board containing one single token",
          board.equals(game.getBoard()));

    } catch (InvalidMove e) {
      fail("Fails upon invalid move");
    }
  }

  public void testGetTurn() {
    Game game = new ConnectFourGame();
    game.startGame();
    assertEquals("Initial turn does not correspond to YELLOW", game.getTurn(),
        Token.YELLOW);

    game.changeTurn();
    assertEquals("Initial turn does not correspond to RED", game.getTurn(),
        Token.RED);
  }

  public void testWinner() {
    Game p1 = new ConnectFourGame();
    p1.startGame();
    assertEquals("Does not return NONE when the game has not yet finished",
        p1.winner(), Token.NONE);

    // Four in a row in column 0
    p1.setToken(new Position(0, 0));
    p1.setToken(new Position(1, 0));
    p1.setToken(new Position(0, 0));
    p1.setToken(new Position(1, 0));
    p1.setToken(new Position(0, 0));
    p1.setToken(new Position(1, 0));
    p1.setToken(new Position(0, 0));
    assertTrue("Fails when winner is YELLOW", p1.winner().equals(Token.YELLOW));

    Game p2 = new ConnectFourGame();
    p2.startGame();

    // Yet another 4 in a row for the red tokens
    p2.setToken(new Position(0, 0));
    p2.setToken(new Position(1, 0));
    p2.setToken(new Position(0, 0));
    p2.setToken(new Position(1, 0));
    p2.setToken(new Position(0, 0));
    p2.setToken(new Position(1, 0));
    p2.setToken(new Position(2, 0));
    p2.setToken(new Position(1, 0));
    assertTrue("Fails when winner is RED", p2.winner().equals(Token.RED));
  }

  public void testGameOver() {
    class GameOverDraw extends ConnectFourGame {

      class FullBoard extends Board {

        FullBoard() {
          _tokens = 42;
        }
      }

      GameOverDraw() {
        _board = new FullBoard();
        _observers = new Vector<GameObserver>();
        _turn = Token.YELLOW;
      }
    }

    class GameOverYellow extends ConnectFourGame {

      GameOverYellow() {
        String board[] = { "       ", "       ", "Y      ", "YR     ",
            "YR     ", "YR     " };

        _board = BoardUtils.buildBoard(board, 7, 6, new ConnectFourTokenMove());
        _observers = new Vector<GameObserver>();
        _turn = Token.YELLOW;
      }
    }

    class GameOverRed extends ConnectFourGame {

      GameOverRed() {
        String board[] = { "       ", "       ", "       ", "       ",
            "       ", "RRRRYYY" };

        _board = BoardUtils.buildBoard(board, 7, 6, new ConnectFourTokenMove());
        _observers = new Vector<GameObserver>();
        _turn = Token.RED;
      }
    }

    Game game1 = new ConnectFourGame();
    game1.startGame();
    assertFalse("Return true when the game is not yet over", game1.isGameOver());

    Game game2 = new GameOverDraw();
    assertTrue("Fails upon DRAW", game2.isGameOver());

    Game game3 = new GameOverYellow();
    assertTrue("Fails upon 4 in a Row of YELLOW", game3.isGameOver());

    Game game4 = new GameOverRed();
    assertTrue("Fails upon 4 in a Row of RED", game4.isGameOver());
  }

  public void testPutToken() {

    Game game = new ConnectFourGame();
    game.startGame();

    game.setToken(new Position(-2, 0));
    assertEquals("Changes the turn upon invalid move", game.getTurn(),
        Token.YELLOW);

    game.setToken(new Position(0, 0));
    assertEquals("Does not change the turn upon valid move", game.getTurn(),
        Token.RED);
  }

  public void testGetRows() {
    Game game = new ConnectFourGame();
    game.startGame();
    assertEquals(
        "Fails when returning the number of rows of the board in a Four in a Row game",
        game.getRows(), 6);
  }

  public void testGetColumns() {
    Game game = new ConnectFourGame();
    game.startGame();
    assertEquals(
        "Fails when returning the number of columns of the board in a Four in a Row game",
        game.getColumns(), 7);
  }
}