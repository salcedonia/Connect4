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
package utils;

import exception.InvalidMove;
import logic.*;
import logic.strategy.PopOutTokenMove;
import logic.strategy.ConnectFourTokenMove;
import logic.strategy.GravityTokenMove;
import logic.strategy.TokenMoveStrategy;

public class BoardUtils {

  public static String[] swapTokens(String grid[]) {
    String stringBoard[] = new String[grid.length];

    for (int i = 0; i < grid.length; i++) {
      stringBoard[i] = grid[i].replace('Y', 'R').replace('R', 'Y');
    }
    return stringBoard;
  }

  public static String[] getSymmetric(String board[]) {
    String symmetric[] = new String[board.length];

    for (int i = 0; i < board.length; i++) {
      symmetric[i] = (new StringBuilder(board[i])).reverse().toString();
    }
    return symmetric;
  }

  public static Board buildBoard(String grid[], int columns, int rows,
      TokenMoveStrategy strategy) {
    Board board = null;

    if (strategy instanceof PopOutTokenMove) {
      board = new Board(4, 7, strategy);
    } else {
      if (strategy instanceof ConnectFourTokenMove) {
        board = new Board(7, 6, strategy);
      } else {
        if (strategy instanceof GravityTokenMove) {
          board = new Board(columns, rows, strategy);
        }
      }
    }
    Token color;

    for (int row = board.getHeight() - 1; row >= 0; row--) {
      for (int column = 0; column < board.getWidth(); column++) {

        char token = grid[row].charAt(column);

        switch (token) {

        case ' ':
          color = Token.NONE;
          break;
        case 'Y':
          color = Token.YELLOW;
          break;
        case 'R':
          color = Token.RED;
          break;
        default:
          return null;
        }

        if (color != Token.NONE) {
          try {
            board.putToken(color, column, row);
          } catch (InvalidMove e) {
          }
        }
      }
    }
    return board;
  }

  public static String boardToString(Board board) {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < 6; i++) {
      stringBuilder.append('|');
      for (int j = 0; j < 7; j++) {
        switch (board.getSlot(j, i)) {

        case NONE:
          stringBuilder.append(' ');
          break;
        case YELLOW:
          stringBuilder.append('Y');
          break;
        case RED:
          stringBuilder.append('R');
          break;
        }
      }
      stringBuilder.append('|');
      stringBuilder.append('\n');
    }
    stringBuilder.append("\\-------/\n");
    return stringBuilder.toString();
  }
}
