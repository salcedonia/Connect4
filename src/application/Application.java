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
package application;

import gui.factory.ConsoleInterfaceFactory;
import gui.factory.GUIInterfaceFactory;
import gui.factory.InterfaceFactory;
import gui.game.GameInterface;

import java.io.IOException;

import logic.Token;
import logic.factory.PopOutFactory;
import logic.factory.ConnectFourFactory;
import logic.factory.GravityFactory;
import logic.factory.LogicFactory;
import logic.game.Game;
import logic.player.Player;

public class Application {

  private Game _game;
  private LogicFactory _logicFactory;
  private InterfaceFactory _interfaceFactory;
  private GameInterface _gameInterface;

  public boolean init(GameType gameType, GameMode gameMode,
      PlayerType redPlayerType, PlayerType yellowPlayerType, int columns,
      int rows) {
    try {

      createLogicFactory(gameType);
      createInterfaceFactory(gameMode);

      Player redPlayer = createRedPlayer(redPlayerType);
      Player yellowPlayer = createYellowPlayer(yellowPlayerType);

      configureGame(columns, rows, redPlayer, yellowPlayer);
      configureGameInterface(gameType);

      _game.setAskPlayAgain(_gameInterface.getAskPlayAgain());

    } catch (IOException e) {
      return false;
    }
    return true;
  }

  public void run() {
    _game.run();
  }

  private void createLogicFactory(GameType gameType) {
    if (gameType == GameType.CONNECT4) {
      _logicFactory = new ConnectFourFactory();
    } else {
      if (gameType == GameType.POP_OUT) {
        _logicFactory = new PopOutFactory();
      } else {
        _logicFactory = new GravityFactory();
      }
    }
  }

  private void createInterfaceFactory(GameMode gameMode) {
    if (gameMode == GameMode.CONSOLE) {
      _interfaceFactory = new ConsoleInterfaceFactory();
    } else {
      _interfaceFactory = new GUIInterfaceFactory();
    }
  }

  private Player createRedPlayer(PlayerType playerType) {
    if (playerType == PlayerType.HUMAN) {
      return _logicFactory.createHumanPlayer(_interfaceFactory);
    }
    return _logicFactory.createComputerPlayer(Token.RED);
  }

  private Player createYellowPlayer(PlayerType playerType) {
    if (playerType == PlayerType.HUMAN) {
      return _logicFactory.createHumanPlayer(_interfaceFactory);
    }
    return _logicFactory.createComputerPlayer(Token.YELLOW);
  }

  private void configureGame(int columns, int rows, Player redPlayer,
      Player yellowPlayer) {
    _game = _logicFactory.createGame(columns, rows);
    _game.setPlayers(redPlayer, yellowPlayer);
  }

  private void configureGameInterface(GameType gameType) throws IOException {
    int width = _game.getColumns();
    int height = _game.getRows();

    _gameInterface = _interfaceFactory.createGameInterface(gameType, width,
        height);
    _gameInterface.setGame(_game);
  }

  public void terminateGame() {
    _gameInterface.terminateGame();
  }
}
