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
package logic.strategy;

import utils.Gravity;
import exception.InvalidMove;
import logic.Board;
import logic.Position;
import logic.Token;

public class GravityTokenMove implements TokenMoveStrategy {

  public Position putToken(Board board, Token token, int column, int row)
      throws InvalidMove {
    Gravity gravedad = computeGravity(column, row, board.getWidth(),
        board.getHeight());
    Position posFinal = computeFinalGravityPosition(gravedad, board, column,
        row);

    return setToken(board, token, posFinal.getX(), posFinal.getY());
  }

  private Gravity computeGravity(int column, int row, int columns, int rows) {
    int superiorGravity = row;
    int inferiorGravity = (rows - 1) - row;
    int leftGravity = column;
    int rightGravity = (columns - 1) - column;

    int bestVertical = Integer.MAX_VALUE, bestHorizontal = Integer.MAX_VALUE;
    int gravHorizontal = 0, gravVertical = 0;

    if (superiorGravity < inferiorGravity) {

      bestVertical = superiorGravity;
      gravVertical = -1;
    } else {
      if (superiorGravity > inferiorGravity) {

        bestVertical = inferiorGravity;
        gravVertical = 1;
      }
    }

    if (leftGravity < rightGravity) {

      bestHorizontal = leftGravity;
      gravHorizontal = -1;
    } else {
      if (leftGravity > rightGravity) {

        bestHorizontal = rightGravity;
        gravHorizontal = 1;
      }
    }

    if (bestVertical != bestHorizontal) {

      if (bestVertical < bestHorizontal) {
        gravHorizontal = 0;
      } else {
        gravVertical = 0;
      }
    }

    return new Gravity(gravHorizontal, gravVertical);
  }

  private Position computeFinalGravityPosition(Gravity gravity, Board board,
      int column, int row) {
    int columnAux = column, rowAux = row;

    do {
      columnAux = columnAux + gravity.getHorizontalGravity();
      rowAux = rowAux + gravity.getVerticalGravity();
    } while ((columnAux != column || rowAux != row)
        && (board.isValidCell(columnAux, rowAux))
        && (board.getSlot(columnAux, rowAux) == Token.NONE));

    return new Position(columnAux - gravity.getHorizontalGravity(), rowAux
        - gravity.getVerticalGravity());
  }

  private Position setToken(Board board, Token token, int column, int row)
      throws InvalidMove {
    if (token != Token.NONE && board.isValidCell(column, row)) {

      if (board.getSlot(column, row) == Token.NONE) {

        board.setCell(column, row, token);
        board.setTokensInColumn(column, board.getTokensInColumn(column) + 1);
        board.setTokens(board.getTokens() + 1);

        return new Position(column, row);
      } else {
        throw new InvalidMove();
      }
    } else {
      throw new InvalidMove();
    }
  }
}
