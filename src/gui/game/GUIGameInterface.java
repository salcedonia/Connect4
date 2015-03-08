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
package gui.game;

import gui.playagain.GUIPlayAgain;
import gui.swing.BoardPanel;
import gui.swing.MenuBar;
import gui.swing.StatusBarPanel;
import gui.swing.Window;

import java.io.IOException;

import application.Controller;
import application.GameType;
import logic.AskPlayAgain;
import logic.game.Game;
import logic.player.GUIHumanPlayer;

public class GUIGameInterface implements GameInterface {

    private BoardPanel _boardPanel;
    private StatusBarPanel _statusBarPanel;
    private MenuBar _menuBar;

    private Controller _controller;
    private Window _window;
    private GUIHumanPlayer _player = null;

    private GameType _gameType;
    private int _width;
    private int _height;

    public GUIGameInterface(GUIHumanPlayer player, GameType gameType,
	    int width, int height) {
	setPlayer(player);
	_gameType = gameType;
	_width = width;
	_height = height;
    }

    public void setGame(Game game) throws IOException {
	_controller = new Controller(game, _gameType);

	createViews();
	registerGameObservers(game);
	createWindow();
    }

    public AskPlayAgain getAskPlayAgain() {
	return new GUIPlayAgain();
    }

    private void createViews() throws IOException {
	_boardPanel = new BoardPanel(_player, _width, _height);
	_menuBar = new MenuBar(_controller);
	_statusBarPanel = new StatusBarPanel();
    }

    private void createWindow() {
	_window = new Window(_gameType);
	_window.init(_boardPanel, _statusBarPanel, _menuBar);
	_menuBar.setWindow(_window);
    }

    private void registerGameObservers(Game game) {
	game.addObserver(_menuBar);
	game.addObserver(_boardPanel);
	game.addObserver(_statusBarPanel);
    }

    public void setPlayer(GUIHumanPlayer player) {
	_player = player;
    }

    public void terminateGame() {
	_window.closeWindow();
    }
}
