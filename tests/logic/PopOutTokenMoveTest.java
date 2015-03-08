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

public class PopOutTokenMoveTest extends TestCase {

  public void testPutToken() {

    String boardStr1[] = { "R   ", "R   ", "R   ", "Y   ", "Y   ", "Y   ",
        "RYYY" };

    Board board1 = BoardUtils
        .buildBoard(boardStr1, 4, 7, new PopOutTokenMove());
    assertTrue("Fails tokens", board1.getTokens() == 10);
    assertTrue("Fails tokens in column", board1.getTokensInColumn(0) == 7);

    try {

      board1.putToken(Token.RED, 0, 0);
      assertTrue("Fails tokens", board1.getTokens() == 10);
      assertTrue("Fails tokens in column", board1.getTokensInColumn(0) == 7);

      board1.putToken(Token.YELLOW, 1, 0);
      assertTrue("Fails tokens", board1.getTokens() == 11);

    } catch (InvalidMove e) {

      fail("Fails upon invalid move");
    }

    String boardStr2[] = { "R   ", "R   ", "R   ", "R   ", "Y   ", "YY  ",
        "YYYY" };

    Board board2 = BoardUtils
        .buildBoard(boardStr2, 4, 7, new PopOutTokenMove());
    assertEquals("Fails upon displacing tokens in a full column", board1,
        board2);
  }
}
