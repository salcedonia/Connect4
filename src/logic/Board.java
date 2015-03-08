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
import logic.strategy.TokenMoveStrategy;

public class Board {

  protected Token[][] _grid;

  protected int _tokens;
  protected int[] _chipsInColumn;
  protected int _width;
  protected int _height;
  protected TokenMoveStrategy _strategy;

  public Board() {
    _height = 6;
    _width = 7;
    _grid = new Token[_width][_height];

    for (int column = 0; column < _width; column++) {
      for (int row = 0; row < _height; row++) {
        _grid[column][row] = Token.NONE;
      }
    }
    _chipsInColumn = new int[_width];

    for (int column = 0; column < _width; column++) {
      _chipsInColumn[column] = 0;
    }
    _tokens = 0;
  }

  public Board(int width, int height, TokenMoveStrategy strategy) {
    _height = height;
    _width = width;
    _strategy = strategy;

    _grid = new Token[_width][_height];

    for (int column = 0; column < _width; column++) {
      for (int row = 0; row < _height; row++) {
        _grid[column][row] = Token.NONE;
      }
    }
    _chipsInColumn = new int[_width];

    for (int column = 0; column < _width; column++) {
      _chipsInColumn[column] = 0;
    }
    _tokens = 0;
  }

  public void resetBoard() {
    for (int column = 0; column < _width; column++) {
      for (int row = 0; row < _height; row++) {
        _grid[column][row] = Token.NONE;
      }
    }
    _chipsInColumn = new int[_width];

    for (int column = 0; column < _width; column++) {
      _chipsInColumn[column] = 0;
    }
    _tokens = 0;
  }

  public Token getSlot(int column, int row) {
    if (isValidColumn(column) && isValidRow(row)) {
      return _grid[column][row];
    }
    return Token.NONE;
  }

  public void setCell(int column, int row, Token color) {
    _grid[column][row] = color;
  }

  public Position putToken(Token color, int column, int row) throws InvalidMove {
    return _strategy.putToken(this, color, column, row);
  }

  public boolean isColumnFull(int column) {
    return (isValidColumn(column)) && (_chipsInColumn[column] == _height);
  }

  public boolean isValidColumn(int column) {
    return (column >= 0) && (column < _width);
  }

  public boolean isValidRow(int row) {
    return (row >= 0) && (row < _height);
  }

  public boolean isValidCell(int column, int row) {
    return isValidColumn(column) && isValidRow(row);
  }

  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }
    if (!(o instanceof Board)) {
      return false;
    }

    Board m = (Board) o;
    for (int x = 0; x < _width; x++) {
      for (int y = 0; y < _height; y++) {
        if (!this._grid[x][y].equals(m._grid[x][y])) {
          return false;
        }
      }
    }
    return true;
  }

  public int getTokens() {
    return _tokens;
  }

  public int getTokensInColumn(int column) {
    return _chipsInColumn[column];
  }

  public int getWidth() {
    return _width;
  }

  public int getHeight() {
    return _height;
  }

  public void setTokens(int chips) {
    _tokens = chips;
  }

  public void setTokensInColumn(int column, int chips) {
    _chipsInColumn[column] = chips;
  }

  public Token[][] getGrid() {
    return _grid;
  }

  public void setGrid(Token[][] grid) {
    for (int i = 0; i < _width; i++) {
      for (int j = 0; j < _height; j++) {
        _grid[i][j] = grid[i][j];
      }
    }
  }

  public boolean isBoardFull() {
    return _tokens == _width * _height;
  }

  public boolean fourTokensConnected(Token color) {
    return fourTokensConnectedHorizontally(color)
        || fourTokensConnectedVertically(color)
        || fourTokensConnectedDiagonally(color);
  }

  public boolean fourTokensConnectedHorizontally(Token color) {
    int row = _height - 1, column = 0;

    if (color != Token.NONE) {

      while (row >= 0) {

        while (column + 3 < _width) {

          if (_grid[column][row] == color && _grid[column + 1][row] == color
              && _grid[column + 2][row] == color
              && _grid[column + 3][row] == color)
            return true;

          column++;
        }
        row--;
        column = 0;
      }
    }
    return false;
  }

  public boolean fourTokensConnectedVertically(Token color) {
    int column = 0, row = _height - 1;

    if (color != Token.NONE) {

      while (column < _width) {

        while (row - 3 >= 0) {

          if (_grid[column][row] == color && _grid[column][row - 1] == color
              && _grid[column][row - 2] == color
              && _grid[column][row - 3] == color) {
            return true;
          }
          row--;
        }

        row = _height - 1;
        column++;
      }
    }

    return false;
  }

  public boolean fourTokensConnectedDiagonally(Token color) {
    return connectedLeftDiagonally(color) || connectedRightDiagonally(color);
  }

  public boolean connectedRightDiagonally(Token color) {
    // X
    // X
    // X
    // X
    int row = _height - 4, column = 0;

    if (color != Token.NONE) {

      while (row >= 0) {

        while (column + 3 < _width) {

          if ((_grid[column][row] == color
              && _grid[column + 1][row + 1] == color
              && _grid[column + 2][row + 2] == color && _grid[column + 3][row + 3] == color)) {
            return true;
          }
          column++;
        }
        row--;
        column = 0;
      }
    }
    return false;
  }

  public boolean connectedLeftDiagonally(Token color) {
    // X
    // X
    // X
    // X
    int row = _height - 1, column = 0;

    if (color != Token.NONE) {

      while (row - 3 >= 0) {

        while (column + 3 < _width) {

          if ((_grid[column][row] == color
              && _grid[column + 1][row - 1] == color
              && _grid[column + 2][row - 2] == color && _grid[column + 3][row - 3] == color)) {
            return true;
          }
          column++;
        }
        row--;
        column = 0;
      }
    }
    return false;
  }
}
