package tictactoe.listeners;

import tictactoe.TicTacToeFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListener implements ActionListener {
    
    private TicTacToeFrame ticTacToeFrame;

    private boolean inGame;
    
    public MenuListener(TicTacToeFrame ticTacToeFrame) {
        this.ticTacToeFrame = ticTacToeFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(ticTacToeFrame.isNewGamePushed(source) || ticTacToeFrame.isMenuInstructionPushed(source) || ticTacToeFrame.isMenuAboutPushed(source))	{
            ticTacToeFrame.clearPanelSouth();
            ticTacToeFrame.setDefaultLayout();

            if(ticTacToeFrame.isNewGamePushed(source))	{
                ticTacToeFrame.addNewGamePanelToTopPanel();
            } else {
                if(ticTacToeFrame.isMenuInstructionPushed(source))	{
                    ticTacToeFrame.displayInstructionPopup();
                    
                } else	{//About
                    ticTacToeFrame.displayAboutPopup();
                }
            }
            ticTacToeFrame.addTopPanelToMainPanel();
        }
        else if(ticTacToeFrame.isGameHumanVsHuman(source) || ticTacToeFrame.isGameHumanVersusComputer(source))	{
            if(inGame)	{
                inGame = ticTacToeFrame.startNewGameWhileGameRunning();
            } else	{
                ticTacToeFrame.startNewGame(source);
            }
        }
//        else if(source == btnContinue)	{
////            checkTurn();
//            showGame();
//        }
//        else if(source == btnSetName)	{
//            askUserForPlayerNames();
//        }
//        else if(source == mnuExit)	{
//            option = askMessage("Are you sure you want to exit?", "Exit Game", JOptionPane.YES_NO_OPTION);
//            if(option == JOptionPane.YES_OPTION)
//                System.exit(0);
//        }
//        else if(source == btnTryAgain)	{
//            newGame();
//            btnTryAgain.setEnabled(false);
//        }
//        else if(source == btnQuit)	{
//            quit();
//        }

        ticTacToeFrame.resetMainPanelVisibility();

    }


}
