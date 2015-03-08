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

public class Position {

  private int _X;
  private int _Y;

  public Position() {
    _X = 0;
    _Y = 0;
  }

  public Position(int x, int y) {
    _X = x;
    _Y = y;
  }

  public int getX() {
    return _X;
  }

  public int getY() {
    return _Y;
  }

  public void setX(int x) {
    _X = x;
  }

  public void setY(int y) {
    _Y = y;
  }

  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }
    if (!(o instanceof Position)) {
      return false;
    }
    Position p = (Position) o;
    return (p._X == _X && p._Y == _Y);
  }

  public Object clone() {
    Object ret = null;

    try {
      ret = super.clone();
    } catch (CloneNotSupportedException ex) {

    }

    return ret;
  }
}
