import java.awt.*;

import Piece.Piece;

public class PieceView {
    private Piece thePiece;

    PieceView () {
    }

    public void setPiece (Piece p) {
        thePiece = p;
    }

    public static Color getColor ( Piece.PieceType t) {
        switch (t) {
            case PIECE_I:
                return Color.cyan;
            case PIECE_T:
                return Color.magenta;
            case PIECE_L:
                return Color.orange;
            case PIECE_J:
                return Color.blue;
            case PIECE_O:
                return Color.yellow;
            case PIECE_Z:
                return Color.red;
            case PIECE_S:
                return Color.green;
            case PIECE_OBSTACLE:
                return Color.darkGray;
            default:
                System.exit(0);
                break;
        }
        return Color.black;
    }

    public void draw( Graphics g, int x_offset, int y_offset ) {
        for ( int i = 0; i < 4; i++ )
        {
            for ( int j=0; j < 4; j++ )
            {
                if ( thePiece.isCovering(i,j))
                {
                    g.setColor( getColor(thePiece.getType()));
                    int actual_x = x_offset + thePiece.getX() * PlayingFieldView.BOX_SIZE;
                    int actual_y = y_offset + thePiece.getY() * PlayingFieldView.BOX_SIZE;

                    g.fillRect( actual_x + PlayingFieldView.BOX_SIZE*i + 1,
                            actual_y + PlayingFieldView.BOX_SIZE*j + 1,
                            PlayingFieldView.BOX_SIZE -2,
                            PlayingFieldView.BOX_SIZE - 2);
                }
            }
        }
    }
}
