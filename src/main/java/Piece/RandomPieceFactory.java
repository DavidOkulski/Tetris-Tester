package Piece;

import java.util.Random;


public class RandomPieceFactory implements PieceFactory {
    protected Random theRand;

    RandomPieceFactory () {
        theRand = new Random(0x11223344);
    }

    public int gameId() {
        return 0;
    }

    public Piece createPiece() {
            int type = theRand.nextInt( Piece.getTotalPieces());
            Piece p = null;

            switch (type) {
                case 0:
                    p = new IPiece();
                    break;
                case 1:
                    p = new TPiece();
                    break;
                case 2:
                    p = new LPiece();
                    break;
                case 3:
                    p = new JPiece();
                    break;
                case 4:
                    p = new OPiece();
                    break;
                case 5:
                    p = new ZPiece();
                    break;
                case 6:
                    p = new SPiece();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        return p;
    }
}
