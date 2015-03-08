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

import application.GameType;

import java.awt.*;

import javax.swing.*;

public class Window extends JFrame {

    private static final long serialVersionUID = 1L;

    private Container _container;

    public Window(GameType gameType) {
	super(gameType.toString());
    }

    public void init(BoardPanel boardPanel, StatusBarPanel statusBarPanel,
	    MenuBar menuBar) {
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setJMenuBar(menuBar);

	_container = this.getContentPane();
	_container.setLayout(new BorderLayout());

	JPanel boardStatusPanel = new JPanel();
	boardStatusPanel.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();
	c.fill = GridBagConstraints.BOTH;
	c.gridx = 0;
	c.gridy = 0;
	boardStatusPanel.add(boardPanel, c);
	boardStatusPanel.setBackground(new Color(65, 111, 135));

	boardStatusPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40,
		40));

	_container.add(boardStatusPanel, BorderLayout.CENTER);
	_container.add(statusBarPanel, BorderLayout.SOUTH);

	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

    public void closeWindow() {
	dispose();
    }
}
