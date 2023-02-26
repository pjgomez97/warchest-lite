# Warchest Lite

This is a simplified version of the board game Warchest, done in Java.

Warchest is a game about controlling zones and making strategies, just like Chess or Shōgi. The
game is played with a 9x9 board with 9 types of pieces and the goal of the game is to have 6
controlled zones before your opponent. For this matter, you will place and move your units and
attack your opponent’s units.

## Technologies
* Java 17
* Maven

## Requirements
* Java SE 17 installed

## How to use

Just run the jar file with 'java -jar [filename]'

## Fix wrong terminal output

Some shells are not compatible with the colors used to display the game board, which can lead to strange characters or symbols displayed.
The colors can be disabled by setting the **WARCHEST_DISABLE_COLORS** environment variable.

This can be done using the following command:

#### Linux
``
export WARCHEST_DISABLE_COLORS=true
``

#### Windows
``
set WARCHEST_DISABLE_COLORS=true
``

## Controls

The game is controlled by terminal input. The possible commands are:

#### Game

* PLACE \<UNIT> \<SQUARE>
* CONTROL \<UNIT> \<SQUARE>
* MOVE \<ORIGIN> \<DESTINATION>
* RECRUIT \[ROYAL] \<UNIT>
* ATTACK \<ORIGIN> \<DESTINATION>
* INITIATIVE \<UNIT>

#### Other
* HELP
* EXIT