# Tic Tac Toe game to be refactored during the hands on meetings

TicTacToe-original folder contains the initial project that needed to be refactored

 
 # Smart actions after 26.08.2016 meeting (Tenerife)
 
 * 1) inGame and other game state stuff to be moved to GameLogic class
 2) when startNewGame, a new instance of GameLogic will be created and will be injected to both controllers
 3) eventually (improvement to notice 2) we can put a reset method to the GameLogic class, which should be a singleton
 