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
package gui.playagain;

import java.util.Scanner;

import logic.AskPlayAgain;

public class ConsolePlayAgain implements AskPlayAgain {

  private Scanner _scanner;

  public ConsolePlayAgain(Scanner scanner) {
    _scanner = scanner;
  }

  public boolean playAgain() {
    String userOption;
    boolean playAgain = false;

    do {

      System.out.println();
      System.out.print("    Do you want to play again? (y/n): ");
      userOption = _scanner.nextLine();

      // Allows upper and lower case
      userOption = userOption.toLowerCase();

      if (userOption.matches("s")) {
        playAgain = true;
        System.out.println();
      } else {
        if (userOption.matches("n")) {
          playAgain = false;
        } else {
          System.err.println("    -> Invalid option. ");
        }
      }

    } while (!userOption.matches("y") && !userOption.matches("n"));

    return playAgain;
  }
}
