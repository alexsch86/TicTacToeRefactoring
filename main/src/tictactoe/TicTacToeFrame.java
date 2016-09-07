package tictactoe;

import tictactoe.listeners.MenuController;
import tictactoe.listeners.TicTacToeGameController;
import tictactoe.model.BoardState;
import tictactoe.model.GameType;
import tictactoe.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static java.util.Arrays.asList;
import static tictactoe.GameLogic.NUMBER_OF_BUTTONS;
import static tictactoe.model.GameType.PLAYER_VS_CPU;
import static tictactoe.model.GameType.PLAYER_VS_PLAYER;

public class TicTacToeFrame {
    
    final String VERSION = "3.0";

    //Setting up ALL the variables
    JFrame window = new JFrame("Tic-Tac-Toe " + VERSION);

    JMenuBar mnuMain = new JMenuBar();
    JMenuItem mnuNewGame = new JMenuItem("New Game"),
            mnuInstruction = new JMenuItem("Instructions"),
            mnuExit = new JMenuItem("Exit"),
            mnuAbout = new JMenuItem("About");

    JButton btn1v1 = new JButton("Player vs Player"),
            btn1vCPU = new JButton("Player vs Computer"),
            btnQuit = new JButton("Quit"),
            btnSetName = new JButton("Set Player Names"),
            btnContinue = new JButton("Continue..."),
            btnTryAgain = new JButton("Try Again?");
    JButton buttonsOfTableBoard[] = new JButton[NUMBER_OF_BUTTONS];

    JPanel pnlNewGame = new JPanel(),
            pnlMenu = new JPanel(),
            pnlMain = new JPanel(),
            pnlTop = new JPanel(),
            pnlBottom = new JPanel(),
            pnlQuitNTryAgain = new JPanel(),
            pnlPlayingField = new JPanel();

    JLabel lblTitle = new JLabel("Tic-Tac-Toe"),
            lblTurn = new JLabel(),
            lblStatus = new JLabel("", JLabel.CENTER),
            lblMode = new JLabel("", JLabel.LEFT);
    JTextArea txtMessage = new JTextArea();

    final int X = 535, Y = 342,
            mainColorR = 190, mainColorG = 50, mainColorB = 50,
            btnColorR = 70, btnColorG = 70, btnColorB = 70;
    
    private Color clrBtnWonColor = new Color(190, 190, 190);
    
    private int winCounterPlayer1 = 0;
    private int winCounterPlayer2 = 0;
    private int option;
    
    private String message,
            player1Name = "Player 1", player2Name = "Player 2",
            tempPlayer2 = "Player 2";
    
    public TicTacToeFrame() {    //Setting game properties and layout and sytle...

        //Setting window properties:
        window.setSize(X, Y);
        window.setLocation(350, 260);
        //window.setResizable(false);
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Setting Menu, Main, Top, Bottom Panel Layout/Backgrounds
        pnlMenu.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));

        pnlNewGame.setBackground(new Color(mainColorR - 50, mainColorG - 50, mainColorB - 50));
        pnlMenu.setBackground(new Color((mainColorR - 50), (mainColorG - 50), (mainColorB - 50)));
        pnlMain.setBackground(new Color(mainColorR, mainColorG, mainColorB));
        pnlTop.setBackground(new Color(mainColorR, mainColorG, mainColorB));
        pnlBottom.setBackground(new Color(mainColorR, mainColorG, mainColorB));

        //Setting up Panel QuitNTryAgain
        pnlQuitNTryAgain.setLayout(new GridLayout(1, 2, 2, 2));
        pnlQuitNTryAgain.add(btnTryAgain);
        pnlQuitNTryAgain.add(btnQuit);

        //Adding menu items to menu bar
        mnuMain.add(mnuNewGame);
        mnuMain.add(mnuInstruction);
        mnuMain.add(mnuAbout);
        mnuMain.add(mnuExit);//	Menu Bar is Complete

        //Adding buttons to NewGame panel
        pnlNewGame.setLayout(new GridLayout(4, 1, 2, 10));
        pnlNewGame.add(btnContinue);
        pnlNewGame.add(btn1v1);
        pnlNewGame.add(btn1vCPU);
        pnlNewGame.add(btnSetName);

        //Setting Button propertied
        btnTryAgain.setEnabled(false);
        btnContinue.setEnabled(false);

        //Setting txtMessage Properties
        txtMessage.setBackground(new Color(mainColorR - 30, mainColorG - 30, mainColorB - 30));
        txtMessage.setForeground(Color.white);
        txtMessage.setEditable(false);
        
        setupGameMenu();
        setupGameBoard();

        //Adding everything needed to pnlMenu and pnlMain
        lblMode.setForeground(Color.white);
        pnlMenu.add(lblMode);
        pnlMenu.add(mnuMain);
        pnlMain.add(lblTitle);

        //Adding to window and Showing window
        window.add(pnlMenu, BorderLayout.NORTH);
        window.add(pnlMain, BorderLayout.CENTER);
        window.setVisible(true);
    }

    private void setupGameMenu() {
        //Adding Action Listener to all the Buttons and Menu Items
        ActionListener menuListener = new MenuController(this);

        mnuNewGame.addActionListener(menuListener);
        mnuExit.addActionListener(menuListener);
        mnuInstruction.addActionListener(menuListener);
        mnuAbout.addActionListener(menuListener);
        btn1v1.addActionListener(menuListener);
        btn1vCPU.addActionListener(menuListener);
        btnQuit.addActionListener(menuListener);
        btnSetName.addActionListener(menuListener);
        btnContinue.addActionListener(menuListener);
        btnTryAgain.addActionListener(menuListener);
    }

    public int getPosition(JButton jButton) {
        return Arrays.asList(buttonsOfTableBoard).indexOf(jButton);
    }
    
    private void setupGameBoard() {
        ActionListener gameBoardListener = new TicTacToeGameController(this);

        pnlPlayingField.setLayout(new GridLayout(3, 3, 2, 2));
        pnlPlayingField.setBackground(Color.black);

        for (int i = 0; i < NUMBER_OF_BUTTONS; i++) {
            buttonsOfTableBoard[i] = new JButton();
            buttonsOfTableBoard[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
            buttonsOfTableBoard[i].addActionListener(gameBoardListener);
            pnlPlayingField.add(buttonsOfTableBoard[i]);//	Playing Field is Compelte
        }
    }

    private void doLayout()	{	//	Shows the Playing Field
        //	*IMPORTANT*- Does not start out brand new (meaning just shows what it had before)
        clearPanelSouth();
        pnlMain.setLayout(new BorderLayout());
        pnlTop.setLayout(new BorderLayout());
        pnlBottom.setLayout(new BorderLayout());
        pnlTop.add(pnlPlayingField);
        pnlBottom.add(lblTurn, BorderLayout.WEST);
        pnlBottom.add(lblStatus, BorderLayout.CENTER);
        pnlBottom.add(pnlQuitNTryAgain, BorderLayout.EAST);
        pnlMain.add(pnlTop, BorderLayout.CENTER);
        pnlMain.add(pnlBottom, BorderLayout.SOUTH);
        pnlPlayingField.requestFocus();
//        checkTurn();        
    }
    
    private void resetBoard()	{	//	Sets all the game required variables to default
        for(int i = 0; i < NUMBER_OF_BUTTONS; i++)	{
            buttonsOfTableBoard[i].setBackground(new Color(btnColorR, btnColorG, btnColorB));
            buttonsOfTableBoard[i].setText("");
            buttonsOfTableBoard[i].setEnabled(true);
        }
//        win = false;
        doLayout();
        displayWinStatus();
    }
    
    private void quit()	{
        lblMode.setText("");
        btnContinue.setEnabled(false);
        clearPanelSouth();
        setDefaultLayout();
        pnlTop.add(pnlNewGame);
        pnlMain.add(pnlTop);
    }
    
    private void askUserForPlayerNames()	{
        String temp;
        boolean tempIsValid = false;
        temp = getInput("Enter player 1 name:", player1Name);
        if(temp == null)	{/*Do Nothing*/}
        else if(temp.equals(""))
            showMessage("Invalid Name!");
        else if(temp.equals(player2Name))	{
            option = askMessage("Player 1 name matches Player 2's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid = true;
        } else if(temp != null)	{
            tempIsValid = true;
        }
        if(tempIsValid)	{
            player1Name = temp;
            tempIsValid = false;
        }

        temp = getInput("Enter player 2 name:", player2Name);
        if(temp == null)	{/*Do Nothing*/}
        else if(temp.equals(""))
            showMessage("Invalid Name!");
        else if(temp.equals(player1Name))	{
            option = askMessage("Player 2 name matches Player 1's\nDo you want to continue?", "Name Match", JOptionPane.YES_NO_OPTION);
            if(option == JOptionPane.YES_OPTION)
                tempIsValid = true;
        } else if(temp != null)	{
            tempIsValid = true;
        }
        if(tempIsValid)	{
            player2Name = temp;
            tempPlayer2 = temp;
            tempIsValid = false;
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------------------
    public void setDefaultLayout()	{
        pnlMain.setLayout(new GridLayout(2, 1, 2, 5));
        pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlBottom.setLayout(new FlowLayout(FlowLayout.CENTER));
    }
    //-----------------------------------------------------------------------------------------------------------------------------------
    private void displayWinStatus()	{
        lblStatus.setText(player1Name + ": " + winCounterPlayer1 + " | " + player2Name + ": " + winCounterPlayer2);
    }
    
    private int askMessage(String msg, String tle, int op)	{
        return JOptionPane.showConfirmDialog(null, msg, tle, op);
    }
    
    private String getInput(String msg, String setText)	{
        return JOptionPane.showInputDialog(null, msg, setText);
    }
    
    private void showMessage(String msg)	{
        JOptionPane.showMessageDialog(null, msg);
    }
    
    public void clearPanelSouth()	{	//Removes all the possible panels
        //that pnlMain, pnlTop, pnlBottom
        //could have.
        pnlMain.remove(lblTitle);
        pnlMain.remove(pnlTop);
        pnlMain.remove(pnlBottom);
        pnlTop.remove(pnlNewGame);
        pnlTop.remove(txtMessage);
        pnlTop.remove(pnlPlayingField);
        pnlBottom.remove(lblTurn);
        pnlBottom.remove(pnlQuitNTryAgain);
    }
    
    public void startNewGame(GameType gameType) {
        btnContinue.setEnabled(true);
        if(gameType == PLAYER_VS_PLAYER)	{
            player2Name = tempPlayer2;
            lblMode.setText(gameType.name());
        } else	{
            player2Name = "Computer";
            lblMode.setText(gameType.name());
        }
        
        winCounterPlayer1 = 0;
        winCounterPlayer2 = 0;
        
        resetBoard();
    }

    public GameType getTypeOfGame(Object source) {
        if(source == btn1v1) {
            return PLAYER_VS_PLAYER;
        } else {
            return PLAYER_VS_CPU;
        }
    }

    public boolean startNewGameWhileGameRunning() {
        option = askMessage("If you start a new game," +
                        "your current game will be lost..." + "\n" +
                        "Are you sure you want to continue?",
                "Quit Game?" , JOptionPane.YES_NO_OPTION
        );
        
        return option == JOptionPane.YES_OPTION;
    }

    public void refreshTableBoard(BoardState boardState) {
        refreshButtonsText(boardState);
        for(JButton jButton : buttonsOfTableBoard) {
            jButton.setEnabled(jButton.getText().equals(""));
        }
    }
    
    private void disableTableButtons() {
        for(JButton jButton : buttonsOfTableBoard) {
            jButton.setEnabled(false);
        }
    }

    private void refreshButtonsText(BoardState boardState) {
        List<JButton> buttons = asList(buttonsOfTableBoard);
        for(ListIterator<JButton> iterator = buttons.listIterator(); iterator.hasNext(); ) {
            int position = iterator.nextIndex();
            iterator.next().setText(boardState.getStateAtPosition(position).getCharacter());
        }
    }

    public void markWinningPositionAndEnd(int[] winningCombination) {
        buttonsOfTableBoard[winningCombination[0]].setBackground(clrBtnWonColor);
        buttonsOfTableBoard[winningCombination[1]].setBackground(clrBtnWonColor);
        buttonsOfTableBoard[winningCombination[2]].setBackground(clrBtnWonColor);
        
        disableTableButtons();
    }

    public boolean isNewGamePushed(Object source) {
        return isMenuItemEqualToSource(mnuNewGame, source);
    }

    public boolean isMenuInstructionPushed(Object source) {
        return isMenuItemEqualToSource(mnuInstruction, source);
    }

    public boolean isMenuAboutPushed(Object source) {
        return isMenuItemEqualToSource(mnuAbout, source);
    }

    private boolean isMenuItemEqualToSource(JMenuItem jMenuItem, Object source) {
        return jMenuItem == source;
    }

    public void addNewGamePanelToTopPanel() {
        pnlTop.add(pnlNewGame);
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
        txtMessage.setText(message);
        pnlTop.add(txtMessage);
    }

    public void addTopPanelToMainPanel() {
        pnlMain.add(pnlTop);
    }

    /**
     * see setVisible in Swing
     */
    public void resetMainPanelVisibility() {
        pnlMain.setVisible(false);
        pnlMain.setVisible(true);
    }

    public boolean isGameHumanVsHuman(Object source) {
        return source == btn1v1;
    }

    public boolean isGameHumanVersusComputer(Object source) {
        return source == btn1vCPU;
    }

    public void displayPlayerHasWon(Player winningPlayer) {
        message = winningPlayer.getTableCharacter() + " has won";
        
        setTextMessageAndShowOnPanelTop();
    }

    public void displayPlayersHaveTied() {
        message = "Both players have tied!\nBetter luck next time.";

        setTextMessageAndShowOnPanelTop();
    }

    public void displayPlayerTurn(Player player) {
        String whoTurn = player.getTableCharacter().getCharacter();
        lblTurn.setText("Turn: " + whoTurn);
    }
//-------------------END OF ACTION PERFORMED METHOD-------------------------//
}