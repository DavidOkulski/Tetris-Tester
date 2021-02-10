package Piece;

public class OPiece extends Piece {
    public OPiece() {
        shape = theShape;
    }

    @Override
    public PieceType getType() {
        return PieceType.PIECE_O;
    }

    private static int[][][] theShape = { // O
            {
                    {0, 1, 1, 0},
                    {0, 1, 1, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            }
    };
}
