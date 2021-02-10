package Piece;

public class SPiece extends Piece {
    public SPiece() {
        shape = theShape;
    }

    @Override
    public PieceType getType() {
        return PieceType.PIECE_S;
    }

    private static int[][][] theShape = { // S
            {
                    {0,1,1,0},
                    {1,1,0,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,0,0},
                    {0,1,1,0},
                    {0,0,1,0},
                    {0,0,0,0}
            },
            {
                    {0,0,0,0},
                    {0,1,1,0},
                    {1,1,0,0},
                    {0,0,0,0}
            },
            {
                    {1,0,0,0},
                    {1,1,0,0},
                    {0,1,0,0},
                    {0,0,0,0}
            }

    };
}
