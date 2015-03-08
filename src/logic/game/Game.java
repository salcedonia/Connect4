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
package logic.game;

import java.util.*;

import exception.InvalidMove;
import logic.AskPlayAgain;
import logic.Board;
import logic.Position;
import logic.Token;
import logic.player.Player;

abstract public class Game {

    protected Token _turn;
    protected Board _board;
    protected Vector<GameObserver> _observers;
    protected Token _winner;
    protected int _boardWidth;
    protected int _boardHeight;

    protected Player _redPlayer;
    protected Player _yellowPlayer;

    protected AskPlayAgain _askPlayAgain;
    protected boolean _terminationRequested;

    protected Game() {
	_observers = new Vector<GameObserver>();
    }

    protected abstract Board createBoard();

    protected void startGame() {
	_board = createBoard();
	_turn = Token.YELLOW;
	_winner = Token.NONE;
	synchronized (this) {

	    _terminationRequested = false;
	}
	notifyGameStarted();
    }

    protected boolean setToken(Position position) {
	try {

	    if (position != null) {
		Position p = _board.putToken(_turn, position.getX(),
			position.getY());
		notifyMove(p.getX(), p.getY());

		if (isGameOver()) {

		    if (_board.fourTokensConnected(_turn)) {
			_winner = _turn;
		    } else if (_board.fourTokensConnected(_turn.getOpposite())) {
			_winner = _turn.getOpposite();
		    }
		    notifyGameOver(_winner);

		} else {
		    changeTurn();
		}
		return true;
	    }

	} catch (InvalidMove ex) {
	}
	return false;
    }

    abstract protected boolean isGameOver();

    protected Token winner() {
	return _winner;
    }

    protected void changeTurn() {
	_turn = _turn.getOpposite();
    }

    protected void notifyGameStarted() {
	for (GameObserver observer : _observers) {
	    observer.gameStarted();
	}
    }

    protected void notifyMove(int column, int row) {
	for (GameObserver observer : _observers) {
	    observer.movePerformedInBoard(_turn, column, row);
	}
    }

    protected void notifyGameOver(Token winner) {
	for (GameObserver observer : _observers) {
	    observer.gameOver(winner);
	}
    }

    protected void gameOverFromUser() {
	notifyGameOver(Token.NONE);
    }

    protected Token getTurn() {
	return _turn;
    }

    protected void setTurn(Token turn) {
	_turn = turn;
    }

    protected Board getBoard() {
	return _board;
    }

    protected void setBoard(Board board) {
	_board = board;
    }

    public void addObserver(GameObserver observer) {
	if (!_observers.contains(observer)) {
	    _observers.add(observer);
	}
    }

    public void removeObserver(GameObserver observer) {
	_observers.removeElement(observer);
    }

    public abstract int getRows();

    public abstract int getColumns();

    public void setPlayers(Player redPlayer, Player yellowPlayer) {
	_redPlayer = redPlayer;
	_yellowPlayer = yellowPlayer;
    }

    public void run() {

	do {
	    startGame();
	    do {

		if (_turn == Token.YELLOW) {
		    setToken(_yellowPlayer.getMove(_board.getGrid()));
		} else {
		    if (_turn == Token.RED) {
			setToken(_redPlayer.getMove(_board.getGrid()));
		    }
		}
		if (_terminationRequested) {
		    notifyGameOver(_winner);
		    break;
		}

	    } while (!isGameOver());
	} while (_askPlayAgain.playAgain());
    }

    public void setAskPlayAgain(AskPlayAgain askPlayAgain) {
	_askPlayAgain = askPlayAgain;
    }

    public synchronized void requestTerminate() {
	_terminationRequested = true;
    }
}
