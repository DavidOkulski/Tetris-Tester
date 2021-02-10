import java.awt.*;

import Piece.*;

public class PlayingFieldView extends javax.swing.JPanel {
    /* This represents the number of pixels each 'box' represents on the screen */
    final static int 	BOX_SIZE = 15;

    /* We draw the board inside a canvas, this is the
     * x,y offset inside the canvas */
    private	int 		x_offset;
    private	int 		y_offset;
    private PlayingField theField;
    private PieceView    thePieceView;

    PlayingFieldView ( PlayingField f, int xpos, int ypos) {
        theField = f;
        x_offset = xpos;
        y_offset = ypos;
        thePieceView = new PieceView();
    }

    //
    // This method draws the playing field and the current piece on the
    // graphics context g
    //
    public void draw ( Graphics g )
    {
        for ( int i = 0; i < theField.getWidth(); i++ )
        {
            for ( int j = 0; j < theField.getHeight(); j++ )
            {
                Piece.PieceType contents = theField.getContents(i,j);
                if (  contents != Piece.PieceType.PIECE_NONE)
                {
                    g.setColor ( PieceView.getColor(contents) );
                    g.fillRect (x_offset + BOX_SIZE*i + 1,
                            y_offset + BOX_SIZE*j + 1,
                            BOX_SIZE - 2,
                            BOX_SIZE - 2);
                }
            }
        }
        thePieceView.setPiece(theField.getCurrentPiece());
        thePieceView.draw (g, x_offset, y_offset);
    }
}
