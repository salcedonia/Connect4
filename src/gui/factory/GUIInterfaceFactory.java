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
package gui.factory;

import gui.game.GUIGameInterface;
import gui.game.GameInterface;
import application.GameType;
import logic.player.GUIHumanPlayer;
import logic.player.Player;

public class GUIInterfaceFactory implements InterfaceFactory {

    private GUIHumanPlayer _player = null;
    private GUIGameInterface _gameInterface = null;

    public Player createHumanPlayer(boolean rowSensitive) {
	if (_player == null) {
	    _player = new GUIHumanPlayer();
	}
	if (_gameInterface != null) {
	    _gameInterface.setPlayer(_player);
	}
	return _player;
    }

    public GameInterface createGameInterface(GameType gameType, int width,
	    int height) {
	_gameInterface = new GUIGameInterface(_player, gameType, width, height);
	return _gameInterface;
    }
}
