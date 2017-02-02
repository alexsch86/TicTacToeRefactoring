package tictactoe.view;

import tictactoe.listeners.MenuController;
import tictactoe.listeners.TicTacToeGameController;
import tictactoe.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ListIterator;

import static java.util.Arrays.asList;
import static tictactoe.model.GameType.PLAYER_VS_CPU;
import static tictactoe.model.GameType.PLAYER_VS_PLAYER;
import static tictactoe.model.Operation.*;
import static tictactoe.util.Utils.isStringEmpty;
import static tictactoe.util.Utils.isStringNull;

public class TicTacToeFrame extends JFrame {

    private static final int NUMBER_OF_BUTTONS = 9;

    private static final String COLLISION_PLAYER_NAMES = "This player name matches the other player name\nDo you want to continue?";
    private static final String NAME_MATCH = "Name Match";

    private final String VERSION = "4.0";

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

    private final int buttonColorR = 70;
    private final int buttonColorG = 70;
    private final int buttonColorB = 70;
    
    private Color colorButtonWin = new Color(190, 190, 190);
    
    private int winCounterPlayer1 = 0;
    private int winCounterPlayer2 = 0;
    
    private String message;
    private String player1Name = "Player 1";
    private String player2Name = "Player 2";

    public TicTacToeFrame() {
        setupGameFrame();
    }

    public int getPositionOfButtonPressed(JButton jButton) {
        return asList(buttonsOfGameBoard).indexOf(jButton);
    }

    public void displayLayoutOfBoard()	{
        clearPanels();
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

    public void clearPanels()	{	//Removes all the possible panels
        panelMain.remove(labelTitle);
        panelMain.remove(panelTop);
        panelMain.remove(panelBottom);
        panelTop.remove(panelStartNewGame);
        panelTop.remove(textMessage);
        panelTop.remove(panelGameBoard);
        panelBottom.remove(labelTurn);
        panelBottom.remove(panelTryAgain);
    }

    private void setupGameFrame() {
        setPanels();
        fillMenuBar();
        setupComponents();

        addComponentsToPanels();
        addListeners();
        setupGameBoard();

        setupMainWindow();
    }

    private void setupMainWindow() {
        this.setTitle("Tic-Tac-Toe " + VERSION);
        this.setSize(535, 342);
        this.setLocation(350, 260);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.add(panelMenuBar, BorderLayout.NORTH);
        this.add(panelMain, BorderLayout.CENTER);
        this.setVisible(true);
    }

    private void setPanels() {
        panelMenuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelTryAgain.setLayout(new GridLayout(1, 2, 2, 2));
        panelStartNewGame.setLayout(new GridLayout(4, 1, 2, 10));
        panelGameBoard.setLayout(new GridLayout(3, 3, 2, 2));

        panelMenuBar.setBackground(new Color(140, 0, 0));
        panelTop.setBackground(new Color(190, 50, 50));
        panelBottom.setBackground(new Color(190, 50, 50));
        panelMain.setBackground(new Color(190, 50, 50));
        panelStartNewGame.setBackground(new Color(140, 0, 0));
        panelGameBoard.setBackground(Color.black);
    }

    private void fillMenuBar() {
        menuMain.add(menuNewGame);
        menuMain.add(menuInstruction);
        menuMain.add(menuAbout);
        menuMain.add(menuExit);
    }

    private void setupComponents() {
        buttonTryAgain.setEnabled(false);
        buttonContinueGame.setEnabled(false);

        textMessage.setBackground(new Color(200, 20, 20));
        textMessage.setForeground(Color.white);
        textMessage.setEditable(false);

        labelModel.setForeground(Color.white);
    }

    private void addComponentsToPanels() {
        panelTryAgain.add(buttonTryAgain);
        panelTryAgain.add(buttonQuit);
        panelStartNewGame.add(buttonContinueGame);
        panelStartNewGame.add(buttonPlayHumanVsHuman);
        panelStartNewGame.add(buttonPlayHumanVsCPU);
        panelStartNewGame.add(buttonSetPlayerNames);
        panelMenuBar.add(labelModel);
        panelMenuBar.add(menuMain);
        panelMain.add(labelTitle);

    }

    private void addListeners() {
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

    private void setupGameBoard() {
        ActionListener gameBoardListener = new TicTacToeGameController(this);

        for (int i = 0; i < NUMBER_OF_BUTTONS; i++) {
            buttonsOfGameBoard[i] = new JButton();
            buttonsOfGameBoard[i].setBackground(new Color(buttonColorR, buttonColorG, buttonColorB));
            buttonsOfGameBoard[i].addActionListener(gameBoardListener);
            panelGameBoard.add(buttonsOfGameBoard[i]);//	Playing Field is Compelte
        }
    }

    public void quit()	{
        clearPanels();
        setDefaultLayout();
        labelModel.setText("");
        buttonContinueGame.setEnabled(false);
        panelTop.add(panelStartNewGame);
        panelMain.add(panelTop);
    }

    public void assignNewPlayerNamesFromInput()	{
        String playerName = getInput("Enter player 1 name:", player1Name);
        boolean isValidPlayerName = isPlayerNameValid(playerName, player2Name);
        if(isValidPlayerName)	{
            player1Name = playerName;
        }

        playerName = getInput("Enter player 2 name:", player2Name);
        isValidPlayerName = isPlayerNameValid(playerName, player1Name);
        if(isValidPlayerName)	{
            player2Name = playerName;
        }
    }

    private boolean isPlayerNameValid(String playerName, String playerNameToCompare) {
        boolean tempIsValid = false;
        if (isStringEmpty(playerName)) {
            showMessageOnPopup("Invalid Name!");
        } else {
            tempIsValid = !areEqual(playerName, playerNameToCompare) || isCollisionOfNamesAcceptable();
        }
        return tempIsValid;
    }

    private boolean areEqual(String text1, String text2) {
        return !isStringNull(text1) && text1.equals(text2);
    }

    private boolean isCollisionOfNamesAcceptable() {
        return JOptionPane.YES_OPTION == askMessage(COLLISION_PLAYER_NAMES, NAME_MATCH, JOptionPane.YES_NO_OPTION);
    }

    private String getInput(String msg, String setText)	{
        return JOptionPane.showInputDialog(null, msg, setText);
    }

    private void showMessageOnPopup(String msg)	{
        JOptionPane.showMessageDialog(null, msg);
    }

    public void setDefaultLayout()	{
        panelMain.setLayout(new GridLayout(2, 1, 2, 5));
        panelTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    public int askMessage(String msg, String tle, int op)	{
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }

    public void startNewGame(GameType gameType) {
        buttonContinueGame.setEnabled(true);
        buttonTryAgain.setEnabled(false);

        if(gameType == PLAYER_VS_PLAYER)	{
            labelModel.setText(gameType.name());
        } else	{
            player2Name = "Computer";
            labelModel.setText(gameType.name());
        }

        winCounterPlayer1 = 0;
        winCounterPlayer2 = 0;

        resetBoard();
    }

    private void resetBoard()	{
        for(int i = 0; i < NUMBER_OF_BUTTONS; i++)	{
            buttonsOfGameBoard[i].setBackground(new Color(buttonColorR, buttonColorG, buttonColorB));
            buttonsOfGameBoard[i].setText("");
            buttonsOfGameBoard[i].setEnabled(true);
        }

        displayLayoutOfBoard();
        displayWinStatus();
    }

    private void displayWinStatus()	{
        labelStatus.setText(player1Name + ": " + winCounterPlayer1 + " | " + player2Name + ": " + winCounterPlayer2);
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

    public void refreshTableBoard(TableCharacter[] tableCharacters) {
        refreshButtonsText(tableCharacters);
        for(JButton jButton : buttonsOfGameBoard) {
            jButton.setEnabled(isTableButtonEmpty(jButton));
        }
    }

    private boolean isTableButtonEmpty(JButton jButton) {
        return areEqual(jButton.getText(), "");
    }

    private void refreshButtonsText(TableCharacter[] tableCharacters) {
        List<JButton> buttons = asList(buttonsOfGameBoard);
        for(ListIterator<JButton> iterator = buttons.listIterator(); iterator.hasNext(); ) {
            int position = iterator.nextIndex();
            iterator.next().setText(tableCharacters[position].getCharacter());
        }
    }

    public void markWinningPositionAndEnd(int[] winningCombination) {
        buttonsOfGameBoard[winningCombination[0]].setBackground(colorButtonWin);
        buttonsOfGameBoard[winningCombination[1]].setBackground(colorButtonWin);
        buttonsOfGameBoard[winningCombination[2]].setBackground(colorButtonWin);

        disableTableButtons();
    }

    private void disableTableButtons() {
        for(JButton jButton : buttonsOfGameBoard) {
            jButton.setEnabled(false);
        }
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

}