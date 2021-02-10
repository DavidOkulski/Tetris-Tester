//
// Piece.java
//

// I, O, T, S, Z, J, and L
// http://tetris.wikia.com/wiki/Tetromino
// We have added PIECE_NONE and PIECE_OBSTACLE

package Piece;

public abstract class Piece {
    public enum PieceType {
        PIECE_NONE, PIECE_I, PIECE_T, PIECE_L, PIECE_J, PIECE_O, PIECE_Z, PIECE_S, PIECE_OBSTACLE
    }
    /* Each piece is 4x4 */
    public final static int 	PIECE_SIZE	= 4;

    protected int         xpos;
    protected int         ypos;

    /* This keeps track of which rotation of the piece we are using */
    protected int 		pieceRotation;
    protected int       shape[][][];

    public Piece() {
        xpos = 0;
        ypos = 0;

        pieceRotation = 0;
    }
    abstract public PieceType getType();

    public static int getTotalPieces () {
        return 7;
    }

    public void setX (int x) {
        xpos = x;
    }
    public int getX () {
        return xpos;
    }

    public int getY () {
        return ypos;
    }

    public int getPieceRotation(){return pieceRotation; }

    public void setY(int y) {
        ypos = y;
    }

    public void moveLeft () {
        xpos--;
    }

    public void moveRight() {
        xpos++;
    }

    public void moveDown () {
        ypos++;
    }

    public void moveUp () {
        ypos--;
    }



    public void rotateLeft( ) {
        pieceRotation++;
        if ( pieceRotation >= shape.length ) {
            pieceRotation = 0;
        }
    }

    public void rotateRight( ) {
        pieceRotation--;
        if ( pieceRotation < 0 ) {
            pieceRotation = shape.length - 1;
        }
    }

    public boolean isCovering( int x, int y ) {
        return ( shape[pieceRotation][y][x] == 1 );
    }
}