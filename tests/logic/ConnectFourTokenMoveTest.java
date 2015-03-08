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

import exception.InvalidMove;
import junit.framework.TestCase;
import logic.Board;
import logic.Token;
import logic.strategy.ConnectFourTokenMove;

public class ConnectFourTokenMoveTest extends TestCase {

  public void testPutToken() {
    Board board = new Board(7, 6, new ConnectFourTokenMove());

    try {

      board.putToken(Token.YELLOW, 6, 0);
      assertEquals("Fails when putting the first token",
          board.getSlot(6, board.getHeight() - 1), Token.YELLOW);

      board.putToken(Token.RED, 6, 0);
      assertEquals("Fails when putting the second token",
          board.getSlot(6, board.getHeight() - 2), Token.RED);

      assertTrue("Fails tokens", board.getTokens() == 2);
      assertTrue("Fails tokens in column", board.getTokensInColumn(6) == 2);

    } catch (InvalidMove e) {

      fail("Fails upon invalid move");
    }
  }
}
