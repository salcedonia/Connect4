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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.Token;
import application.Application;
import application.Controller;
import application.GameMode;
import application.GameType;
import application.PlayerType;

public class ConfigureGameWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String RESOURCES_PATH = "/gui/swing/img/";
    private static final String HEADER_IMAGE = "aboutUsSplash.png";
    private static final String YELLOW_IMAGE = "yellowSingle.png";
    private static final String RED_IMAGE = "redSingle.png";
    private static final String GAME_MODE_IMAGE = "gameMode.png";
    private static final String ACCEPT_IMAGE = "accept.png";

    private static final String WINDOW_TITLE = "Configure a new game";
    private static final String HUMAN = "Human";
    private static final String COMPUTER = "Computer";
    private static final String CONNECT4 = "Connect4";
    private static final String POP_OUT = "Pop Out";
    private static final String GRAVITY = "Gravity";
    private static final String WIDTH = "Width";
    private static final String HEIGHT = "Height";
    private static final String GAME_MODE = "Game Mode";
    private static final String YELLOW_PLAYER = "Yellow Player";
    private static final String RED_PLAYER = "Red Player";
    private static final String ACCEPT_BUTTON = "Accept";

    JFrame _parent;
    GameTypePanel _gameTypePanel;
    PlayerTypePanel _yellowPlayerTypePanel;
    PlayerTypePanel _redPlayerTypePanel;
    GravityDimensionsPanel _widthPanel;
    GravityDimensionsPanel _heightPanel;
    JPanel _buttonsPanel;

    public Window _window;
    public GameMode _gameMode = GameMode.GUI;
    public GameType _gameType = GameType.CONNECT4;
    public PlayerType _redPlayer = PlayerType.HUMAN;
    public PlayerType _yellowPlayer = PlayerType.HUMAN;
    public int _width = 10;
    public int _height = 10;

    Controller _controller;
    public boolean _isFirstConfiguration = true;

    public ConfigureGameWindow(Controller controller, boolean isFirstConfiguration, Window window) {
	super(WINDOW_TITLE);
	_parent = this;
	_isFirstConfiguration = isFirstConfiguration;
	_window = window;
	_controller = controller;
    }

    public void showWindow() {
	
	if (_isFirstConfiguration) {
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} else {
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	JPanel content = (JPanel) getContentPane();
	content.setLayout(new GridBagLayout());
	GridBagConstraints c = new GridBagConstraints();

	JLabel headerLabel = new JLabel();
	try {
	    headerLabel.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + HEADER_IMAGE))));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}
	c.fill = GridBagConstraints.HORIZONTAL;
	c.gridx = 0;
	c.gridy = 0;
	content.add(headerLabel, c);

	_gameTypePanel = new GameTypePanel(GAME_MODE);
	c.gridy = 1;
	content.add(_gameTypePanel, c);

	_widthPanel = new GravityDimensionsPanel(WIDTH);
	_widthPanel.setVisible(false);
	c.gridy = 2;
	content.add(_widthPanel, c);

	_heightPanel = new GravityDimensionsPanel(HEIGHT);
	_heightPanel.setVisible(false);
	c.gridy = 3;
	content.add(_heightPanel, c);

	c.gridy = 4;
	content.add(new JSeparator(SwingConstants.HORIZONTAL), c);

	JPanel playersPanel = new JPanel();
	playersPanel.setLayout(new GridBagLayout());

	_yellowPlayerTypePanel = new PlayerTypePanel(YELLOW_PLAYER,
		Token.YELLOW);
	c.fill = GridBagConstraints.VERTICAL;
	c.gridx = 0;
	c.gridy = 0;
	playersPanel.add(_yellowPlayerTypePanel, c);

	c.gridx = 1;
	playersPanel.add(new JSeparator(SwingConstants.VERTICAL), c);

	_redPlayerTypePanel = new PlayerTypePanel(RED_PLAYER, Token.RED);
	c.gridx = 2;
	playersPanel.add(_redPlayerTypePanel, c);

	c.gridy = 5;
	content.add(new JSeparator(SwingConstants.HORIZONTAL), c);

	c.gridx = 0;
	c.gridy = 6;
	content.add(playersPanel, c);

	c.fill = GridBagConstraints.HORIZONTAL;

	_buttonsPanel = new JPanel();
	_buttonsPanel.setLayout(new GridLayout(1, 1));
	JButton acceptButton = new JButton(ACCEPT_BUTTON);
	acceptButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		if (!_isFirstConfiguration) {
		    
		    // Disposes the previous game
		    _window.closeWindow();
		}
		closeWindow();
		final Application app = new Application();
		if (app.init(_gameType, _gameMode, _redPlayer, _yellowPlayer,
			_width, _height)) {

		    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
			protected Void doInBackground() throws Exception {

			    app.run();
			    return null;
			}
		    };
		    worker.execute();
		}
	    }
	});

	try {
	    acceptButton.setIcon(new ImageIcon(ImageIO.read(this.getClass()
		    .getResource(RESOURCES_PATH + ACCEPT_IMAGE))));
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	c.gridy = 7;
	content.add(new JSeparator(SwingConstants.HORIZONTAL), c);

	_buttonsPanel.add(acceptButton, BorderLayout.EAST);
	_buttonsPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
		Color.BLACK));
	_buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
	c.fill = GridBagConstraints.NONE;
	c.gridy = 8;
	c.anchor = GridBagConstraints.EAST;
	content.add(_buttonsPanel, c);

	content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

	pack();
	setLocationRelativeTo(null);
	setVisible(true);
    }

    public void closeWindow() {
	setVisible(false);
    }

    class GameTypePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	public GameTypePanel(String labelString) {
	    super(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.NONE;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.ipadx = 0;
	    c.ipady = 0;
	    c.anchor = GridBagConstraints.WEST;

	    JLabel labelIcon = new JLabel();
	    try {
		labelIcon.setIcon(new ImageIcon(ImageIO.read(this.getClass()
			.getResource(RESOURCES_PATH + GAME_MODE_IMAGE))));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    add(labelIcon, c);

	    JLabel label = new JLabel(labelString);
	    label.setFont(new Font("inherit", Font.BOLD, 16));
	    c.insets = new Insets(0, 70, 0, 0);
	    add(label, c);

	    // Create the radio buttons.
	    JRadioButton connect4Button = new JRadioButton(CONNECT4);
	    connect4Button.setMnemonic(KeyEvent.VK_C);
	    connect4Button.setActionCommand(CONNECT4);
	    connect4Button.setSelected(true);

	    JRadioButton popOutButton = new JRadioButton(POP_OUT);
	    popOutButton.setMnemonic(KeyEvent.VK_P);
	    popOutButton.setActionCommand(POP_OUT);
	    popOutButton.setSelected(false);

	    JRadioButton gravityButton = new JRadioButton(GRAVITY);
	    gravityButton.setMnemonic(KeyEvent.VK_G);
	    gravityButton.setActionCommand(GRAVITY);
	    gravityButton.setSelected(false);

	    ButtonGroup gameTypeButtonGroup = new ButtonGroup();
	    gameTypeButtonGroup.add(connect4Button);
	    gameTypeButtonGroup.add(popOutButton);
	    gameTypeButtonGroup.add(gravityButton);

	    // Register a listener for the radio buttons.
	    connect4Button.addActionListener(this);
	    popOutButton.addActionListener(this);
	    gravityButton.addActionListener(this);

	    // Put the radio buttons in a column in a panel.
	    JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	    radioPanel.add(connect4Button);
	    radioPanel.add(popOutButton);
	    radioPanel.add(gravityButton);
	    radioPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

	    c.gridy = 1;
	    c.ipadx = 0;
	    c.insets = new Insets(0, 35, 0, 0);
	    add(radioPanel, c);
	    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	public void actionPerformed(ActionEvent e) {
	    JRadioButton button = (JRadioButton) e.getSource();
	    if (button.getText().equals(CONNECT4)) {
		_gameType = GameType.CONNECT4;
		_widthPanel.setVisible(false);
		_heightPanel.setVisible(false);
	    } else {
		if (button.getText().equals(POP_OUT)) {
		    _gameType = GameType.POP_OUT;
		    _widthPanel.setVisible(false);
		    _heightPanel.setVisible(false);
		} else {
		    _gameType = GameType.GRAVITY;
		    _widthPanel.setVisible(true);
		    _heightPanel.setVisible(true);
		}
	    }

	    _parent.repaint();
	    _parent.pack();
	}
    }

    class PlayerTypePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	Token _color;

	public PlayerTypePanel(String labelString, Token color) {
	    super(new GridBagLayout());
	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.NONE;
	    c.gridx = 0;
	    c.gridy = 0;
	    c.anchor = GridBagConstraints.WEST;

	    JLabel labelCircle = new JLabel();
	    _color = color;
	    String imagePath = RESOURCES_PATH + YELLOW_IMAGE;
	    if (_color == Token.RED) {
		imagePath = RESOURCES_PATH + RED_IMAGE;
	    }

	    try {
		labelCircle.setIcon(new ImageIcon(ImageIO.read(this.getClass()
			.getResource(imagePath))));
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    add(labelCircle, c);

	    c.gridx = 0;
	    c.insets = new Insets(0, 40, 0, 0);
	    JLabel label = new JLabel(labelString);
	    label.setFont(new Font("inherit", Font.BOLD, 16));
	    add(label, c);

	    // Create the radio buttons.
	    JRadioButton humanButton = new JRadioButton(HUMAN);
	    humanButton.setMnemonic(KeyEvent.VK_H);
	    humanButton.setActionCommand(HUMAN);
	    humanButton.setSelected(true);

	    JRadioButton computerButton = new JRadioButton(COMPUTER);
	    computerButton.setMnemonic(KeyEvent.VK_C);
	    computerButton.setActionCommand(COMPUTER);
	    computerButton.setSelected(false);

	    ButtonGroup playerTypeButtonGroup = new ButtonGroup();
	    playerTypeButtonGroup.add(humanButton);
	    playerTypeButtonGroup.add(computerButton);

	    // Register a listener for the radio buttons.
	    humanButton.addActionListener(this);
	    computerButton.addActionListener(this);

	    // Put the radio buttons in a column in a panel.
	    JPanel radioPanel = new JPanel(new GridLayout(0, 1));
	    radioPanel.add(humanButton);
	    radioPanel.add(computerButton);

	    c.gridx = 0;
	    c.gridy = 1;
	    c.insets = new Insets(0, 15, 0, 0);
	    add(radioPanel, c);
	    radioPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
	    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}

	public void actionPerformed(ActionEvent e) {
	    JRadioButton button = (JRadioButton) e.getSource();
	    if (button.getText().equals(HUMAN)) {
		if (_color == Token.RED) {
		    _redPlayer = PlayerType.HUMAN;
		} else {
		    _yellowPlayer = PlayerType.HUMAN;
		}
	    } else {

		if (button.getText().equals(COMPUTER)) {
		    if (_color == Token.RED) {
			_redPlayer = PlayerType.COMPUTER;
		    } else {
			_yellowPlayer = PlayerType.COMPUTER;
		    }
		}
	    }
	}
    }

    class GravityDimensionsPanel extends JPanel implements ChangeListener {

	private static final long serialVersionUID = 1L;
	static final int MIN_VALUE = 5;
	static final int MAX_VALUE = 15;
	static final int INIT_VALUE = 10;

	String _measure;

	public GravityDimensionsPanel() {
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public GravityDimensionsPanel(String measure) {

	    _measure = measure;
	    JLabel label = new JLabel(measure);
	    label.setFont(new Font("inherit", Font.BOLD, 12));

	    JSlider dimensions = new JSlider(JSlider.HORIZONTAL, MIN_VALUE,
		    MAX_VALUE, INIT_VALUE);

	    dimensions.addChangeListener(this);
	    dimensions.setMajorTickSpacing(1);
	    dimensions.setMinorTickSpacing(1);
	    dimensions.setPaintTicks(true);
	    dimensions.setPaintLabels(true);
	    Font font = new Font("inherit", Font.PLAIN, 8);
	    dimensions.setFont(font);
	    dimensions.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

	    add(label);
	    add(dimensions);

	    setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}

	public void stateChanged(ChangeEvent e) {
	    JSlider source = (JSlider) e.getSource();
	    if (!source.getValueIsAdjusting()) {
		if (_measure.equals(WIDTH)) {
		    _width = (int) source.getValue();

		} else if (_measure.equals(HEIGHT)) {
		    _height = (int) source.getValue();
		}
	    }
	}
    }
}
