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

import junit.framework.TestCase;
import logic.Position;
import logic.Token;
import logic.game.Game;
import logic.game.GravityGame;

public class GravityGameTest extends TestCase {

  public void testPutToken() {
    Game game = new GravityGame(10, 10);
    game.startGame();

    game.setToken(new Position(-1, 0));
    assertEquals("Changes the turn upon an invalid move", game.getTurn(),
        Token.YELLOW);

    game.setToken(new Position(0, 0));
    assertEquals("Does not change the turn upon valid move", game.getTurn(),
        Token.RED);
  }

  public void testGetRows() {
    Game game = new GravityGame(5, 5);
    game.startGame();
    assertEquals(
        "Fails when returning the number of rows of the board in a Gravity game",
        game.getRows(), 5);
  }

  public void testGetColumns() {
    Game game = new GravityGame(5, 5);
    game.startGame();
    assertEquals(
        "Fails when returning the number of columns of the board in a Gravity game",
        game.getColumns(), 5);
  }
}
