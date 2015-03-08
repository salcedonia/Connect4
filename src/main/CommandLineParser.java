package main;

import application.GameMode;
import application.GameType;
import application.PlayerType;
import jargs.gnu.CmdLineParser;

public class CommandLineParser {

    private static final String INTERFACE_OPTION_STRING = "interface";
    private static final String GAME_OPTION_STRING = "game";
    private static final String WIDTH_OPTION_STRING = "width";
    private static final String HEIGHT_OPTION_STRING = "height";
    private static final String YELLOW_OPTION_STRING = "yellow";
    private static final String RED_OPTION_STRING = "red";

    private static final char INTERFACE_OPTION_CHAR = 'i';
    private static final char GAME_OPTION_CHAR = 'g';
    private static final char WIDTH_OPTION_CHAR = 'w';
    private static final char HEIGHT_OPTION_CHAR = 'h';
    private static final char YELLOW_OPTION_CHAR = 'y';
    private static final char RED_OPTION_CHAR = 'r';

    private static final String HUMAN = "human";
    private static final String COMPUTER = "computer";
    private static final String CONSOLE = "console";
    private static final String GUI = "gui";
    private static final String CONNECT4 = "connect4";
    private static final String POP_OUT = "popout";
    private static final String GRAVITY = "gravity";

    CmdLineParser _cmdLineParser = new CmdLineParser();
    GameMode _gameMode = GameMode.GUI;
    GameType _gameType = GameType.CONNECT4;
    PlayerType _redPlayer = PlayerType.HUMAN;
    PlayerType _yellowPlayer = PlayerType.HUMAN;
    int _width = 10;
    int _height = 10;
    boolean _validParameters = true;

    public CommandLineParser(String[] args) {

	CmdLineParser.Option gameModeOption = _cmdLineParser.addStringOption(
		INTERFACE_OPTION_CHAR, INTERFACE_OPTION_STRING);
	CmdLineParser.Option gameTypeOption = _cmdLineParser.addStringOption(
		GAME_OPTION_CHAR, GAME_OPTION_STRING);
	CmdLineParser.Option widthOption = _cmdLineParser.addIntegerOption(
		WIDTH_OPTION_CHAR, WIDTH_OPTION_STRING);
	CmdLineParser.Option heightOption = _cmdLineParser.addIntegerOption(
		HEIGHT_OPTION_CHAR, HEIGHT_OPTION_STRING);
	CmdLineParser.Option yellowPlayerOption = _cmdLineParser
		.addStringOption(YELLOW_OPTION_CHAR, YELLOW_OPTION_STRING);
	CmdLineParser.Option redPlayerOption = _cmdLineParser.addStringOption(
		RED_OPTION_CHAR, RED_OPTION_STRING);

	try {

	    _cmdLineParser.parse(args);

	    String gameMode = (String) _cmdLineParser
		    .getOptionValue(gameModeOption);

	    if (gameMode != null) {
		if (gameMode.equals(CONSOLE)) {
		    _gameMode = GameMode.CONSOLE;
		} else if (gameMode.equals(GUI)) {
		    _gameMode = GameMode.GUI;
		} else {
		    showUseMode("Invalid game mode: " + gameMode);
		    _validParameters = false;
		}
	    }

	    String gameType = (String) _cmdLineParser
		    .getOptionValue(gameTypeOption);
	    if (gameType != null) {

		if (gameType.equals(CONNECT4)) {
		    _gameType = GameType.CONNECT4;
		} else if (gameType.equals(POP_OUT)) {
		    _gameType = GameType.POP_OUT;
		} else if (gameType.equals(GRAVITY)) {
		    _gameType = GameType.GRAVITY;
		} else {
		    showUseMode("Invalid game type: " + gameType);
		    _validParameters = false;
		}
	    }

	    String yellowPlayer = (String) _cmdLineParser
		    .getOptionValue(yellowPlayerOption);
	    if (yellowPlayer != null) {

		if (yellowPlayer.equals(HUMAN)) {
		    _yellowPlayer = PlayerType.HUMAN;
		} else if (yellowPlayer.equals(COMPUTER)) {
		    _yellowPlayer = PlayerType.COMPUTER;
		} else {
		    showUseMode("Invalid yellow player: " + yellowPlayer);
		    _validParameters = false;
		}
	    }

	    String redPlayer = (String) _cmdLineParser
		    .getOptionValue(redPlayerOption);
	    if (redPlayer != null) {

		if (redPlayer.equals(HUMAN)) {
		    _redPlayer = PlayerType.HUMAN;
		} else if (redPlayer.equals(COMPUTER)) {
		    _redPlayer = PlayerType.COMPUTER;
		} else {
		    showUseMode("Invalid red player: " + redPlayer);
		    _validParameters = false;
		}
	    }

	    Integer width = (Integer) _cmdLineParser
		    .getOptionValue(widthOption);
	    Integer height = (Integer) _cmdLineParser
		    .getOptionValue(heightOption);

	    if (_validParameters) {

		// In gravity mode, the number of columns and rows are checked
		if (_gameType == GameType.GRAVITY) {

		    if (width != null) {
			if (isWidthValid(width)) {
			    _width = width;
			} else {
			    showUseMode("Width out of range: "
				    + width.toString());
			    _validParameters = false;
			}
		    } else {
			showUseMode("Mandatory Gravity width missing");
		    }

		    if (height != null) {
			if (isHeightValid(height)) {
			    _height = height;
			} else {
			    showUseMode("Height out of range: "
				    + height.toString());
			    _validParameters = false;
			}
		    } else {
			showUseMode("Mandatory Gravity height missing");
		    }
		} else {
		    if (_gameType == GameType.CONNECT4
			    || _gameType == GameType.POP_OUT) {
			if (width != null || height != null) {
			    showUseMode("With connect4 or complicate you cannot enter neither the number of columns nor the number of rows.");
			    _validParameters = false;
			}
		    }
		}
	    }

	} catch (CmdLineParser.OptionException e) {

	    _validParameters = false;
	    System.err.println();
	    System.err.println(e.getMessage());
	    System.err.println();
	    showUseMode("");
	}
    }

    public boolean areParametersValid() {
	return _validParameters;
    }

    public boolean isConsole() {
	return _gameMode == GameMode.CONSOLE;
    }

    public boolean isGui() {
	return _gameMode == GameMode.GUI;
    }

    public GameType getGameType() {
	return _gameType;
    }

    public GameMode getGameMode() {
	return _gameMode;
    }

    public PlayerType getYellowPlayer() {
	return _yellowPlayer;
    }

    public PlayerType getRedPlayer() {
	return _redPlayer;
    }

    public int getWidth() {
	return _width;
    }

    public int getHeight() {
	return _height;
    }

    private boolean isHeightValid(Integer height) {
	return (height >= 5 && height <= 15);
    }

    private boolean isWidthValid(Integer width) {
	return (width >= 5 && width <= 15);
    }

    private static void showUseMode(String message) {
	if (!message.matches("")) {
	    System.err.println(message);
	}
	System.err.println();
	System.err
		.println(" Correct use:\n" + "-----------------------\n"
			+ "    -> Main [-"
			+ INTERFACE_OPTION_CHAR
			+ "/--"
			+ INTERFACE_OPTION_STRING
			+ "] <"
			+ CONSOLE
			+ "/"
			+ GUI
			+ "> "
			+ "[-"
			+ GAME_OPTION_CHAR
			+ "/--"
			+ GAME_OPTION_STRING
			+ "] <"
			+ CONNECT4
			+ "/"
			+ POP_OUT
			+ "/"
			+ GRAVITY
			+ "> "
			+ "[-"
			+ RED_OPTION_CHAR
			+ "/--"
			+ RED_OPTION_STRING
			+ "] <"
			+ HUMAN
			+ "/"
			+ COMPUTER
			+ "> [-"
			+ YELLOW_OPTION_CHAR
			+ "/--"
			+ YELLOW_OPTION_STRING
			+ "] <"
			+ HUMAN
			+ "/"
			+ COMPUTER
			+ ">\n"
			+ "    -> With Gravity mode you can choose the number of columns and rows:\n "
			+ "		[-"
			+ WIDTH_OPTION_CHAR
			+ "/--"
			+ WIDTH_OPTION_STRING
			+ "] <5-15> [-"
			+ HEIGHT_OPTION_CHAR
			+ "/--"
			+ HEIGHT_OPTION_STRING
			+ "] <5-15> \n"
			+ "		[-"
			+ HEIGHT_OPTION_CHAR
			+ "/--"
			+ HEIGHT_OPTION_STRING
			+ "] <5-15> [-"
			+ WIDTH_OPTION_CHAR
			+ "/--"
			+ WIDTH_OPTION_STRING
			+ "] <5-15>\n\n"
			+ "NOTE: order of the parameters is irrelevant");
    }
}
