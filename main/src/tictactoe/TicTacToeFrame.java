package tictactoe;

import tictactoe.listeners.MenuController;
import tictactoe.listeners.TicTacToeGameController;
import tictactoe.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static java.util.Arrays.asList;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import static tictactoe.GameLogic.NUMBER_OF_BUTTONS;
import static tictactoe.model.GameType.PLAYER_VS_CPU;
import static tictactoe.model.GameType.PLAYER_VS_PLAYER;
import static tictactoe.model.Operation.*;
import static tictactoe.util.Utils.isStringEmpty;

public class TicTacToeFrame {

    private static final String COLLISION_PLAYER_1_WITH_2 = "Player 1 name matches Player 2's\nDo you want to continue?";
    private static final String COLLISION_PLAYER_2_WITH_1 = "Player 2 name matches Player 1's\nDo you want to continue?";
    private static final String NAME_MATCH = "Name Match";

    private final String VERSION = "3.0";

    private JFrame window = new JFrame("Tic-Tac-Toe " + VERSION);

    private JMenuBar menuMain = new JMenuBar();
    private JMenuItem menuNewGame = new GameMenuItem(NEW_GAME);
    private JMenuItem menuInstruction = new GameMenuItem(SHOW_INSTRUCTIONS);
    private JMenuItem menuExit = new GameMenuItem(EXIT_MENU);
    private JMenuItem menuAbout = new GameMenuItem(SHOW_ABOUT);

    private JButton buttonPlayHumanVsHuman = new GameButton(PLAY_HUMAN_VS_HUMAN);
    private JButton buttonPlayHumanVsCPU = new GameButton(PLAY_HUMAN_VS_CPU);
    private JButton buttonQuit = new GameButton(QUIT);
    private JButton buttonSetPlayerNames = new GameButton(SET_PLAYER_NAMES);
    private JButton buttonContinueGame = new GameButton(CONTINUE_GAME);
    private JButton buttonTryAgain = new GameButton(TRY_AGAIN);

    private JButton buttonsOfGameBoard[] = new JButton[NUMBER_OF_BUTTONS];

    private JPanel panelStartNewGame = new JPanel();
    private JPanel panelMenuBar = new JPanel();
    private JPanel panelMain = new JPanel();
    private JPanel panelTop = new JPanel();
    private JPanel panelBottom = new JPanel();
    private JPanel panelTryAgain = new JPanel();
    private JPanel panelGameBoard = new JPanel();

    private JLabel labelTitle = new JLabel("Tic-Tac-Toe");
    private JLabel labelTurn = new JLabel();
    private JLabel labelStatus = new JLabel("", JLabel.CENTER);
    private JLabel labelModel = new JLabel("", JLabel.LEFT);
    private JTextArea textMessage = new JTextArea();

    private final int mainColorR = 190;
    private final int mainColorG = 50;
    private final int mainColorB = 50;
    private final int btnColorR = 70;
    private final int btnColorG = 70;
    private final int btnColorB = 70;
    
    private Color clrBtnWonColor = new Color(190, 190, 190);
    
    private int winCounterPlayer1 = 0;
    private int winCounterPlayer2 = 0;
    
    private String message;
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";
    private String tempPlayer2 = "Player 2";
    
    TicTacToeFrame() {    
        setupGameFrame();
    }

    private void setupGameFrame() {
        setupMainWindow();

        setLayoutForPanels();
        fillMenuBar();

        panelTryAgain.add(buttonTryAgain);
        panelTryAgain.add(buttonQuit);
        panelStartNewGame.add(buttonContinueGame);
        panelStartNewGame.add(buttonPlayHumanVsHuman);
        panelStartNewGame.add(buttonPlayHumanVsCPU);
        panelStartNewGame.add(buttonSetPlayerNames);

        buttonTryAgain.setEnabled(false);
        buttonContinueGame.setEnabled(false);

        //Setting textMessage Properties
        textMessage.setBackground(new Color(mainColorR - 30, mainColorG - 30, mainColorB - 30));
        textMessage.setForeground(Color.white);
        textMessage.setEditable(false);

        addListeners();
        setupGameBoard();

        //Adding everything needed to panelMenuBar and panelMain
        labelModel.setForeground(Color.white);
        panelMenuBar.add(labelModel);
        panelMenuBar.add(menuMain);
        panelMain.add(labelTitle);

        //Adding to window and Showing window
        window.add(panelMenuBar, BorderLayout.NORTH);
        window.add(panelMain, BorderLayout.CENTER);
        window.setVisible(true);
    }

    private void fillMenuBar() {
        menuMain.add(menuNewGame);
        menuMain.add(menuInstruction);
        menuMain.add(menuAbout);
        menuMain.add(menuExit);
    }

    private void setLayoutForPanels() {
        panelMenuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTryAgain.setLayout(new GridLayout(1, 2, 2, 2));
        panelStartNewGame.setLayout(new GridLayout(4, 1, 2, 10));

        panelStartNewGame.setBackground(new Color(mainColorR - 50, mainColorG - 50, mainColorB - 50));
        panelMenuBar.setBackground(new Color((mainColorR - 50), (mainColorG - 50), (mainColorB - 50)));
        panelMain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
        panelTop.setBackground(new Color(mainColorR, mainColorG, mainColorB));
        panelBottom.setBackground(new Color(mainColorR, mainColorG, mainColorB));
    }

    private void setupMainWindow() {
        int x = 535;
        int y = 342;
        window.setSize(x, y);
        window.setLocation(350, 260);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void addListeners() {
        //Adding Action Listener to all the Buttons and Menu Items
        ActionListener menuListener = new MenuController(this);

        menuNewGame.addActionListener(menuListener);
        menuExit.addActionListener(menuListener);
        menuInstruction.addActionListener(menuListener);
        menuAbout.addActionListener(menuListener);
        buttonPlayHumanVsHuman.addActionListener(menuListener);
        buttonPlayHumanVsCPU.addActionListener(menuListener);
        buttonQuit.addActionListener(menuListener);
        buttonSetPlayerNames.addActionListener(menuListener);
        buttonContinueGame.addActionListener(menuListener);
        buttonTryAgain.addActionListener(menuListener);
    }

    public int getPosition(JButton jButton) {
        return Arrays.asList(buttonsOfGameBoard).indexOf(jButton);
    }
    
    private void setupGameBoard() {
        ActionListener gameBoardListener = new TicTacToeGameController(this);

        panelGameBoard.setLayout(new GridLayout(3, 3, 2, 2));
        panelGameBoard.setBackground(Color.black);

        for (int i = 0; i < NUMBER_OF_BUTTONS; i++) {
            buttonsOfGameBoard[i] = new JButton();
            buttonsOfGameBoard[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
            buttonsOfGameBoard[i].addActionListener(gameBoardListener);
            panelGameBoard.add(buttonsOfGameBoard[i]);//	Playing Field is Compelte
        }
    }

    public void displayLayoutOfBoard()	{
        //	*IMPORTANT*- Does not start out brand new (meaning just shows what it had before)
        clearPanelSouth();
        panelMain.setLayout(new BorderLayout());
        panelTop.setLayout(new BorderLayout());
        panelBottom.setLayout(new BorderLayout());
        panelTop.add(panelGameBoard);
        panelBottom.add(labelTurn, BorderLayout.WEST);
        panelBottom.add(labelStatus, BorderLayout.CENTER);
        panelBottom.add(panelTryAgain, BorderLayout.EAST);
        panelMain.add(panelTop, BorderLayout.CENTER);
        panelMain.add(panelBottom, BorderLayout.SOUTH);
        panelGameBoard.requestFocus();
    }
    
    private void resetBoard()	{	//	Sets all the game required variables to default
        for(int i = 0; i < NUMBER_OF_BUTTONS; i++)	{
            buttonsOfGameBoard[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
            buttonsOfGameBoard[i].setText("");
            buttonsOfGameBoard[i].setEnabled(true);
        }

        displayLayoutOfBoard();
        displayWinStatus();
    }
    
    public void quit()	{
        labelModel.setText("");
        buttonContinueGame.setEnabled(false);
        clearPanelSouth();
        setDefaultLayout();
        panelTop.add(panelStartNewGame);
        panelMain.add(panelTop);
    }
    
    public void askUserForPlayerNames()	{
        boolean tempIsValid = false;
        String playerName = getInput("Enter player 1 name:", player1Name);
        if(isStringEmpty(playerName)) {
            showMessageOnPopup("Invalid Name!");
        } else if(playerName.equals(player2Name))	{
            tempIsValid = JOptionPane.YES_OPTION ==
                    askMessage(COLLISION_PLAYER_1_WITH_2, NAME_MATCH, JOptionPane.YES_NO_OPTION);
        } else {
            tempIsValid = true;
        }
        if(tempIsValid)	{
            player1Name = playerName;
            tempIsValid = false;
        }

        playerName = getInput("Enter player 2 name:", player2Name);
        if(isStringEmpty(playerName)) {
            showMessageOnPopup("Invalid Name!");
        } else if(playerName.equals(player1Name))	{
            tempIsValid = JOptionPane.YES_OPTION ==
                askMessage(COLLISION_PLAYER_2_WITH_1, "Name Match", JOptionPane.YES_NO_OPTION);
        } else	{
            tempIsValid = true;
        }
        if(tempIsValid)	{
            player2Name = playerName;
            tempPlayer2 = playerName;
        }
    }

    public void setDefaultLayout()	{
        panelMain.setLayout(new GridLayout(2, 1, 2, 5));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void displayWinStatus()	{
        labelStatus.setText(player1Name + ": " + winCounterPlayer1 + " | " + player2Name + ": " + winCounterPlayer2);
    }
    
    public int askMessage(String msg, String tle, int op)	{
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }
    
    private String getInput(String msg, String setText)	{
        return JOptionPane.showInputDialog(null, msg, setText);
    }
    
    private void showMessageOnPopup(String msg)	{
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public void clearPanelSouth()	{	//Removes all the possible panels
        panelMain.remove(labelTitle);
        panelMain.remove(panelTop);
        panelMain.remove(panelBottom);
        panelTop.remove(panelStartNewGame);
        panelTop.remove(textMessage);
        panelTop.remove(panelGameBoard);
        panelBottom.remove(labelTurn);
        panelBottom.remove(panelTryAgain);
    }
    
    public void startNewGame(GameType gameType) {
        buttonContinueGame.setEnabled(true);
        buttonTryAgain.setEnabled(false);
        
        if(gameType == PLAYER_VS_PLAYER)	{
            player2Name = tempPlayer2;
            labelModel.setText(gameType.name());
        } else	{
            player2Name = "Computer";
            labelModel.setText(gameType.name());
        }
        
        winCounterPlayer1 = 0;
        winCounterPlayer2 = 0;
        
        resetBoard();
    }

    public GameType getTypeOfGame(Object source) {
        if(source == buttonPlayHumanVsHuman) {
            return PLAYER_VS_PLAYER;
        } else {
            return PLAYER_VS_CPU;
        }
    }

    public boolean startNewGameWhileGameRunning() {
        int option = askMessage("If you start a new game," +
                        "your current game will be lost..." + "\n" +
                        "Are you sure you want to continue?",
                "Quit Game?" , JOptionPane.YES_NO_OPTION
        );
        
        return option == JOptionPane.YES_OPTION;
    }

    public void refreshTableBoard(BoardState boardState) {
        refreshButtonsText(boardState);
        for(JButton jButton : buttonsOfGameBoard) {
            jButton.setEnabled(jButton.getText().equals(""));
        }
    }
    
    private void disableTableButtons() {
        for(JButton jButton : buttonsOfGameBoard) {
            jButton.setEnabled(false);
        }
    }

    private void refreshButtonsText(BoardState boardState) {
        List<JButton> buttons = asList(buttonsOfGameBoard);
        for(ListIterator<JButton> iterator = buttons.listIterator(); iterator.hasNext(); ) {
            int position = iterator.nextIndex();
            iterator.next().setText(boardState.getStateAtPosition(position).getCharacter());
        }
    }

    public void markWinningPositionAndEnd(int[] winningCombination) {
        buttonsOfGameBoard[winningCombination[0]].setBackground(clrBtnWonColor);
        buttonsOfGameBoard[winningCombination[1]].setBackground(clrBtnWonColor);
        buttonsOfGameBoard[winningCombination[2]].setBackground(clrBtnWonColor);
        
        disableTableButtons();
    }

    public Operation getOperation(Object source) {
        IOperation operation = (IOperation) source;
        return operation.getOperation();
    }

    public void addNewGamePanelToTopPanel() {
        panelTop.add(panelStartNewGame);
    }

    public void displayInstructionPopup() {
        message = 	"Instructions:\n\n" +
                "Your goal is to be the first player to get 3 X's or O's in a\n" +
                "row. (horizontally, diagonally, or vertically)\n" +
                player1Name + ": X\n" +
                player2Name + ": O\n";

        setTextMessageAndShowOnPanelTop();
    }

    public void displayAboutPopup() {
        message = 	"About:\n\n" +
                "Title: Tic-Tac-Toe\n" +
                "Creator: Blmaster\n" +
                "Version: " + VERSION + "\n";
        
        setTextMessageAndShowOnPanelTop();
    }

    private void setTextMessageAndShowOnPanelTop() {
        textMessage.setText(message);
        panelTop.add(textMessage);
    }

    public void addTopPanelToMainPanel() {
        panelMain.add(panelTop);
    }

    /**
     * see setVisible in Swing
     */
    public void resetMainPanelVisibility() {
        panelMain.setVisible(false);
        panelMain.setVisible(true);
    }

    public void displayPlayerHasWon(PlayerOrder winningPlayer) {
        showMessageOnPopup(winningPlayer.getTableCharacter() + " has won");
    }

    public void displayPlayersHaveTied() {
        showMessageOnPopup("Both players have tied!\nBetter luck next time.");
    }

    public void displayPlayerTurn(PlayerOrder player) {
        String whoTurn = player.getTableCharacter().getCharacter();
        labelTurn.setText("Turn: " + whoTurn);
    }

    public void resetTryAgainAndContinue() {
        buttonTryAgain.setEnabled(true);
        buttonContinueGame.setEnabled(false);
    }

    //-------------------END OF ACTION PERFORMED METHOD-------------------------//
}