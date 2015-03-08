## Synopsis

A classic Connect4 game developed in **Java** under **GPL3** license.

There are three different game modes:
* **Connect4:** the classic game in which players playing in alternate turns try to connect 4 tokens of their color either horizontally, vertically or diagonally in a board of 7 columns and 6 rows.
* **Pop Out:** a variation of the classic game in which players playing in alternate turns, are trying to connect 4 tokens of their color either horizontally, vertically or diagonally as well in a board of 4 columns and 6 rows. Nevertheless, when a column is full of tokens, players still can drop tokens on the column and displace the token which lies on the base slot out of the board. Thus, this means that you can make the other player win by mistake or two players can have 4 tokens connected at the same time. In this case, the game finishes when one and only one players connects 4 tokens in-a-row.
* **Gravity:** a variation of the classic game in which players playing in alternate turns, trying to connect 4 tokens of their color either horizontally, vertically or diagonally in a board whose dimensions can vary from 5 to 15 rows or columns respectively. In this case, tokens are attracted to the closest wall in which they are dropped. The resulting position of a token is computed as the sum of all the forces of gravity that the token is 

Players can be either **human** or **computer** and it can be played in both **graphic** or **console** mode.

## History

This game is centuries old, Captain James Cook used to play it with his fellow officers on his long voyages, and so it has also been called "Captain's Mistress". Milton Bradley (now owned by Hasbro) published a version of this game called "Connect Four" in 1974.

Other names for this this game are "Four-in-a-Row" and "Plot Four".

## Motivation

The project corresponded initially to the final assignment of *"Programming Systems Laboratory"* course at the **Faculty of Computer Science of Complutense University of Madrid** during the *academic course 2007/2008*.

After a long time, I've decided finally to take it out from the burden of memories, to blow the dust off it, to enhance it and lastly to make it public as part of my open source portfolio that I am putting together.

## Installation

Once you have cloned the repository, it is up to you how to run the application. You can compile the code and generate a .jar file and run the application from there or, eventually use an IDE as Eclipse or Netbeans. Your call.

By default, a new Connect4 swing game with both players as Human is created.
Nonetheless, you can configure your own game options via console by passing the following parameter to the program:  (-i|--interface) console

The following options are available:
* **(-g|--game)** selects the game type:
  * connect4
  * popout
  * gravity
Exceptionally, in case of gravity game, users can specify the dimensions of the board
* **(-w|width)**
* **(-r|rows)**
These dimensions are **10x10** by default for a gravity game.

## Tests

Usual business. Just simply make sure that **JUnit.jar** is in your classpath, then invoke the command line runner from the console as follows:
```
java -cp bin:lib/junit-4.4.jar org.junit.runner.JUnitCore tests.AllTests
```

