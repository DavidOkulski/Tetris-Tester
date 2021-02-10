import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Piece.*;

class PieceTest {

    static Piece p;

    @BeforeAll
     static void createPiece () {
        p = new IPiece();
        p.setX(22);
        p.setY(12);
    }

    @Test
    void getTotalPieces() {
        assertEquals(Piece.getTotalPieces(), 7);
    }

    @Test
    void getX() {
        Piece p = new IPiece();
        p.setX(3);
        assertEquals(p.getX(), 3);

    }

    @Test
    void getY() {
        Piece p = new IPiece();
        p.setY(5);
        assertEquals(p.getY(), 5);
    }

    @Test
    void moveLeft() {
        Piece p = new IPiece();
        p.setX(3);
        p.moveLeft();
        assertEquals (2, p.getX());
    }

    @Test
    void moveRight() {
        Piece p = new IPiece();
        p.setX(3);
        p.moveRight();
        assertEquals (4, p.getX());
    }

    @Test
    void moveDown() {
        Piece p = new IPiece();
        p.setY(3);
        p.moveDown();
        assertEquals (4, p.getY());
    }

    @Test
    void moveUp() {
        Piece p = new IPiece();
        p.setY(3);
        p.moveUp();
        assertEquals (2, p.getY());
    }

    @Test
    void getType() {
        Piece p;
        p = new IPiece();
        assertEquals(p.getType(), Piece.PieceType.PIECE_I);
        p = new TPiece();
        assertEquals(p.getType(), Piece.PieceType.PIECE_T);
        p = new LPiece();
        assertEquals(p.getType(), Piece.PieceType.PIECE_L);
        p = new JPiece();
        assertEquals(p.getType(), Piece.PieceType.PIECE_J);
        p = new OPiece();
        assertEquals(p.getType(), Piece.PieceType.PIECE_O);
        p = new SPiece();
        assertEquals(p.getType(), Piece.PieceType.PIECE_S);
        p = new ZPiece();
        assertEquals(p.getType(), Piece.PieceType.PIECE_Z);
    }

    @Test
    void testMoveThreeTimes() {
        Piece p = new IPiece(); 
        p.setY(4);
        p.moveDown();
        p.moveDown(); 
        p.moveDown();
        // 4 + 3 = 7
        assertEquals (7, p.getY());
    }

    @Test
    void rotateLeft() {
        Piece p = new IPiece();
        p.rotateLeft();
        assertEquals(1, p.getPieceRotation());
        p.rotateLeft();
        assertEquals(2, p.getPieceRotation());
        p.rotateLeft();
        assertEquals(3, p.getPieceRotation());
        p.rotateLeft();
        assertEquals(0, p.getPieceRotation());
    }

    @Test
    void rotateRight() {
        Piece p = new IPiece();
        p.rotateRight();
        assertEquals(3, p.getPieceRotation());
        p.rotateRight();
        assertEquals(2, p.getPieceRotation());
        p.rotateRight();
        assertEquals(1, p.getPieceRotation());
        p.rotateRight();
        assertEquals(0, p.getPieceRotation());

    }

    @Test
    void isCovering() {
        Piece p = new IPiece();
        assertEquals(false, p.isCovering(0,0)); // Case 1: It's blank
        assertEquals(true,  p.isCovering(0,1)); // Case 2: It's covered by the piece
    }
}
