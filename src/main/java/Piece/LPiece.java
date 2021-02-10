package Piece;

public class LPiece extends Piece {
    public LPiece() {
        shape = theShape;
    }

    @Override
    public PieceType getType() {
        return PieceType.PIECE_L;
    }

    private static int[][][] theShape = { // L

            {
                    {0,0,1,0},
                    {1,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,1,1,0},
                    {0,0,0,0}
            },
            {
                    {0,0,0,0},
                    {1,1,1,0},
                    {1,0,0,0},
                    {0,0,0,0}
            },
            {
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,0,0,0}
            }
    };
}