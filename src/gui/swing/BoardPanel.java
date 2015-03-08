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
package gui.swing;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import logic.Position;
import logic.Token;
import logic.game.GameObserver;
import logic.player.GUIHumanPlayer;

public class BoardPanel extends JPanel implements GameObserver {

    private static final long serialVersionUID = 1L;
    private static final String RESOURCES_PATH = "/gui/swing/img/";
    private static final String EMPTY_IMAGE = "empty.png";
    private static final String YELLOW_IMAGE = "yellow.png";
    private static final String RED_IMAGE = "red.png";

    private Image _noneImage;
    private Image _yelloImage;
    private Image _redImage;

    private GUIHumanPlayer _player;

    private Token[][] _grid;
    private int _width;
    private int _height;

    public BoardPanel(GUIHumanPlayer player, int columns, int rows)
	    throws IOException {
	_player = player;

	addMouseListener((MouseListener) new MouseEventsListener());
	loadImages();
	createReplicaBoard(columns, rows);
	setPreferredSize(new Dimension(_noneImage.getWidth(null) * _width,
		_noneImage.getHeight(null) * _height));
    }

    private void createReplicaBoard(int width, int height) {
	_width = width;
	_height = height;

	_grid = new Token[_width][_height];
	resetReplicaBoard();
    }

    public void resetReplicaBoard() {
	for (int column = 0; column < _width; column++) {
	    for (int row = 0; row < _height; row++) {
		_grid[column][row] = Token.NONE;
	    }
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);

	int coordX = 0, coordY = 0;
	Image imagen = null;

	for (int row = 0; row < _height; row++) {

	    for (int column = 0; column < _width; column++) {

		if (_grid[column][row] == Token.YELLOW) {

		    imagen = _yelloImage;
		    coordX = column * _yelloImage.getWidth(null);
		    coordY = row * _yelloImage.getHeight(null);
		}

		if (_grid[column][row] == Token.RED) {

		    imagen = _redImage;
		    coordX = column * _redImage.getWidth(null);
		    coordY = row * _redImage.getHeight(null);
		}

		if (_grid[column][row] == Token.NONE) {

		    imagen = _noneImage;
		    coordX = column * _noneImage.getWidth(null);
		    coordY = row * _noneImage.getHeight(null);
		}
		g.drawImage(imagen, coordX, coordY, null);
	    }
	}
    }

    private void loadImages() throws IOException {
	_noneImage = ImageIO.read(this.getClass().getResource(
		RESOURCES_PATH + EMPTY_IMAGE));
	_yelloImage = ImageIO.read(this.getClass().getResource(
		RESOURCES_PATH + YELLOW_IMAGE));
	_redImage = ImageIO.read(this.getClass().getResource(
		RESOURCES_PATH + RED_IMAGE));
    }

    private class MouseEventsListener extends MouseAdapter implements
	    MouseListener {

	public void mouseClicked(MouseEvent e) {
	    int x = e.getX() / _noneImage.getWidth(null);
	    int y = e.getY() / _noneImage.getHeight(null);

	    if (_player != null) {
		_player.asynchronousMove(new Position(x, y));
	    }
	}
    }

    public void movePerformedInBoard(Token token, int column, int row) {
	if (_grid[column][row] != Token.NONE) {

	    for (int fil = _height - 1; fil >= 1; fil--) {
		_grid[column][fil] = _grid[column][fil - 1];
	    }
	}
	_grid[column][row] = token;
	repaint();
    }

    public void gameStarted() {
	resetReplicaBoard();
	repaint();
    }

    public void gameOver(Token winner) {
	repaint();
    }

    public void newGameConfigured() {

    }
}
