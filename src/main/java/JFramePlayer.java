
import javax.swing.*;
import java.awt.event.ActionEvent;

public class JFramePlayer implements Player {

    private MoveListener moveListener;

    public JFramePlayer (JFrame theFrame ) {
        // theFrame.addKeyListener( new JFramePlayer.KeyPress() );
        theFrame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke('w'), "keypressed");
        theFrame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke('a'), "keypressed");
        theFrame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke('s'), "keypressed");
        theFrame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke('d'), "keypressed");
        theFrame.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(' '), "keypressed");

        theFrame.getRootPane().getActionMap().put("keypressed", new JFramePlayerKeyboardAction(this));
    }

    public void setListener (MoveListener m) {
        moveListener = m;
    }

    public void sendMove (Move m) {
        moveListener.nextMove(m);
    }

    private class JFramePlayerKeyboardAction extends AbstractAction {
        JFramePlayer thePlayer;
        JFramePlayerKeyboardAction(JFramePlayer p) {
            thePlayer = p;
        }
        public void actionPerformed (ActionEvent e) {
            String key = e.getActionCommand();
            if (key.equals("a"))
                thePlayer.sendMove(Move.moveLeft);
            else if (key.equals("d"))
                thePlayer.sendMove(Move.moveRight);
            else if (key.equals("s"))
                thePlayer.sendMove(Move.rotateRight);
            else if (key.equals("w"))
                thePlayer.sendMove(Move.rotateLeft);
            else if (key.equals(" "))
                thePlayer.sendMove(Move.moveDown);
        }
    }
}