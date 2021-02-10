/*
 * TetrisGame.java
 *
 */

import Piece.PieceFactory;
import Piece.RESTPieceFactory;
import Piece.RandomPieceFactory;

import javax.swing.*;
import java.awt.event.*;

public class TetrisGame
{
    // This should not be in the source code, you shouldn't have to rebuild the client
    // if the location of the URL changes
    private static String   APIURL = "http://seng275.csc.uvic.ca:8080/tetris/games";
    // private static String   APIURL = "http://localhost:8080/tetris/games";
    private Timer	        timer;
    private PlayingField 	playField;
    private PieceFactory    theFactory;
	private ResultCollector theCollector;
    private Player          thePlayer;

    private JFrame          theFrame;

    private boolean		    gameOver;

    private int oldLevel;

    private static final int    START_DELAY = 1000;
    private static final int    MIN_DELAY = 75;

    private TetrisGameView  theView;


    /** Creates new form TetrisGame */
    TetrisGame(JFrame theFrame) {
        this.theFrame = theFrame;
        init();
    }

    public boolean isGameOver () {
        return gameOver;
    }

    private int calcDelay () {
        int levelFactor = 100 * playField.getLevel();
        if (levelFactor > START_DELAY) {
            levelFactor = START_DELAY;
        }
        return MIN_DELAY + (START_DELAY - levelFactor);
    }

    private void init() {
        gameOver = false;
        oldLevel = 0;
        // theFactory = new RandomPieceFactory();
        theFactory = new RESTPieceFactory(APIURL);
		theCollector = new RESTAPIResultCollector("auth.txt", APIURL);
        gameOver = false;

        playField = new PlayingField(theFactory, theCollector);
        thePlayer = new JFramePlayer(theFrame);
        thePlayer.setListener(playField);
        theView = new TetrisGameView(theFrame, this, playField);

    }

    public	void startGame() {
        playField.init();
        gameOver = false;
        theView.repaint();
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(calcDelay(), new TimerListener() );
        timer.start();
    }

    //
    // This private class gives us a way to react to timer.
    // Every second we want to drop the piece down one square.
    // The method "actionPerformed" gets called every time
    // the timer is up.
    //
    private class TimerListener implements ActionListener {
        public void actionPerformed( ActionEvent event ) {
            if (!gameOver) {
                gameOver = playField.timeout();
                int newLevel = playField.getLevel();
                if (oldLevel != newLevel) {
                    oldLevel = newLevel;
                    if (timer != null) {
                        timer.stop();
                    }
                    timer = new Timer(calcDelay(), new TimerListener() );
                    timer.start();
                }
            }
            theView.repaint();
        }
    }
}
