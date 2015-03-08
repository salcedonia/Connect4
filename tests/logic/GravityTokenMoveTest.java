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

import utils.BoardUtils;
import exception.InvalidMove;
import junit.framework.TestCase;
import logic.Board;
import logic.Token;
import logic.strategy.GravityTokenMove;

public class GravityTokenMoveTest extends TestCase {

    public void testPutTokenInCentralSlot() {
	String tabComp[] = { "     ", "     ", "     ", "     ", "     " };

	Board tComp = BoardUtils.buildBoard(tabComp, 5, 5,
		new GravityTokenMove());

	assertTrue("Fails tokens", tComp.getTokens() == 0);
	assertTrue("Fails tokens in column", tComp.getTokensInColumn(0) == 0);

	try {

	    tComp.putToken(Token.RED, 2, 2);

	    assertTrue("Fails tokens", tComp.getTokens() == 1);
	    assertTrue("Fails tokens in column",
		    tComp.getTokensInColumn(2) == 1);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}

	String tabRes[] = { "     ", "     ", "  R  ", "     ", "     " };

	Board tRes = BoardUtils
		.buildBoard(tabRes, 5, 5, new GravityTokenMove());

	assertEquals("Fails when putting token in central slot", tComp, tRes);
    }

    public void testPutTokenSimpleCase() {
	String tabComp[] = { "     ", "     ", "     ", "     ", "     " };

	Board tComp = BoardUtils.buildBoard(tabComp, 5, 5,
		new GravityTokenMove());

	assertTrue("Fails tokens", tComp.getTokens() == 0);
	assertTrue("Fails tokens in column", tComp.getTokensInColumn(0) == 0);

	try {

	    tComp.putToken(Token.RED, 2, 1);

	    assertTrue("Fails tokens", tComp.getTokens() == 1);
	    assertTrue("Fails tokens in column",
		    tComp.getTokensInColumn(2) == 1);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}

	String tabRes[] = { "  R  ", "     ", "     ", "     ", "     " };

	Board tRes = BoardUtils
		.buildBoard(tabRes, 5, 5, new GravityTokenMove());
	assertEquals("Fails when putting token in simple case", tComp, tRes);
    }

    public void testPutTokenDoubleCase() {
	String tabComp[] = { "     ", "     ", "     ", "     ", "     " };

	Board tComp = BoardUtils.buildBoard(tabComp, 5, 5,
		new GravityTokenMove());

	assertTrue("Fails tokens", tComp.getTokens() == 0);
	assertTrue("Fails tokens in column", tComp.getTokensInColumn(0) == 0);

	try {

	    tComp.putToken(Token.RED, 1, 1);

	    assertTrue("Fails tokens", tComp.getTokens() == 1);
	    assertTrue("Fails tokens in column",
		    tComp.getTokensInColumn(1) == 0);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}

	String tabRes[] = { "R    ", "     ", "     ", "     ", "     " };

	Board tRes = BoardUtils
		.buildBoard(tabRes, 5, 5, new GravityTokenMove());

	assertEquals("Fails when putting token attracted by two sides", tComp,
		tRes);
    }

    public void testPutTokenTripleCase1() {
	String tabComp[] = { "     ", "     ", "     ", "     ", "     ",
		"     " };

	Board tComp = BoardUtils.buildBoard(tabComp, 5, 6,
		new GravityTokenMove());

	assertTrue("Fails tokens", tComp.getTokens() == 0);
	assertTrue("Fails tokens in column", tComp.getTokensInColumn(0) == 0);

	try {

	    tComp.putToken(Token.RED, 2, 2);

	    assertTrue("Fails tokens", tComp.getTokens() == 1);
	    assertTrue("Fails tokens in column",
		    tComp.getTokensInColumn(2) == 1);

	} catch (InvalidMove e) {

	    fail("Fails upon invalid move");
	}

	String tabRes[] = { "  R  ", "     ", "     ", "     ", "     ",
		"     " };

	Board tRes = BoardUtils
		.buildBoard(tabRes, 5, 6, new GravityTokenMove());

	assertEquals("Fails when putting token attracted by three sides",
		tComp, tRes);
    }

    public void testPutTokenTripleCase2() {
	String tabComp[] = { "      ", "      ", "      ", "      ", "      " };

	Board tComp = BoardUtils.buildBoard(tabComp, 6, 5,
		new GravityTokenMove());

	assertTrue("Fails tokens", tComp.getTokens() == 0);
	assertTrue("Fails tokens in column", tComp.getTokensInColumn(0) == 0);

	try {

	    tComp.putToken(Token.RED, 2, 2);

	    assertTrue("Fails tokens", tComp.getTokens() == 1);
	    assertTrue("Fails tokens in column",
		    tComp.getTokensInColumn(0) == 1);

	} catch (InvalidMove e) {
	    fail("Fails upon invalid move");
	}

	String tabRes[] = { "      ", "      ", "R     ", "      ", "      " };

	Board tRes = BoardUtils
		.buildBoard(tabRes, 6, 5, new GravityTokenMove());

	assertEquals("Fails when putting token attracted by three sides",
		tComp, tRes);
    }
}
