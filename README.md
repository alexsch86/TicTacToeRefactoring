# Tic Tac Toe game to be refactored during the hands on meetings

TicTacToe-original folder contains the initial project that needed to be refactored

 
 # Smart actions after 26.08.2016 meeting (Tenerife)
 
 * 1) inGame and other game state stuff to be moved to GameLogic class  - ONGOING
 * 2) when startNewGame, a new instance of GameLogic will be created and will be injected to both controllers   - DONE
 * 3) eventually (improvement to notice 2) we can put a reset method to the GameLogic class, which should be a singleton  - DONE
 
 # Smart actions after 21.09.2016 meeting (Tenerife)
 
 * 1) Add players in game logic and remove players from frame
 * 2) Fix player vs player logic
 * 3) Try to extract frame into multiple view classes
 * 4) Create enum constants for each GUI operation and use switch in controller
 * 5) Extract all hard-coded strings into constants (first step for i18n)
 