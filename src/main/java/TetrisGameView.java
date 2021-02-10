import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import Piece.*;


import javax.swing.*;


public class TetrisGameView extends javax.swing.JPanel {
    private final static int	    GAME_WIDTH = 500;
    private final static int   	    GAME_HEIGHT = 400;
    private final static int        FRAME_INTERVAL = 100;

    private JFrame              theFrame;
    private TetrisGame          theGame;
    private PlayingField        theField;
    private PlayingFieldView    theFieldView;
    private Piece               theNextPiece;
    private PieceView           theNextPieceView;

    TetrisGameView (JFrame f, TetrisGame g, PlayingField p) {
        theGame = g;
        theField = p;
        theFrame = f;
        init();
    }

    private void init() {
        theFieldView = new PlayingFieldView(theField, 0,0);
        setOpaque(true);

        theFrame.setContentPane(this);
        theFrame.setSize(GAME_WIDTH,GAME_HEIGHT);
        theFrame.setVisible(true);

        setFocusable(true);
        setBackground( Color.black );
        setSize( GAME_WIDTH, GAME_HEIGHT );
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this::nextFrame, 0, FRAME_INTERVAL, TimeUnit.MILLISECONDS);
        theNextPieceView = new PieceView();
    }

    /**
     * Draws the next frame, i.e. refreshes the scores and game.
     */
    private void nextFrame() {
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Display some help
        g.setColor(Color.red);
        g.drawString("Testris!", 300, 20);
        g.setColor(Color.gray);
        g.drawString("A - move left",  260, 50);
        g.drawString("D - move right", 260, 65 );
        g.drawString("S - rotate left",260, 80 );
        g.drawString("W - rotate right",260, 95);
        g.drawString("<space> - drop", 260, 110);

        g.setColor(Color.white);
        g.drawString("Next Piece:", 260,130);

        if ( theGame.isGameOver() ) {
            g.setColor(Color.red);
            g.drawString("GAME OVER!", 260, 190);
        }

        // Display the number of lines
        g.setColor(Color.yellow);
        g.drawString("Level : " + theField.getLevel(), 260, 230 );
        g.drawString("Lines : " + theField.getLineCount(), 260, 245 );
        g.drawString("Score : " + theField.getScore(), 260, 260 );
        // Draw the playing field -- this includes the current piece,
        // the background, and the border
        theFieldView.draw( g );

        theNextPiece = theField.getNextPiece();
        theNextPieceView.setPiece(theNextPiece);
        theNextPieceView.draw(g, 180,150);
    }
}
