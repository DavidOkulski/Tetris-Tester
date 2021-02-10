package Piece;

public class IPiece extends Piece {
    public IPiece() {
        shape = theShape;
    }

    @Override
    public PieceType getType() {
        return PieceType.PIECE_I;
    }

    private static int[][][] theShape = { // I
            {
                    {0,0,0,0},
                    {1,1,1,1},
                    {0,0,0,0},
                    {0,0,0,0},

            },
            {
                    {0,0,1,0},
                    {0,0,1,0},
                    {0,0,1,0},
                    {0,0,1,0}
            },
            {
                    {0,0,0,0},
                    {0,0,0,0},
                    {1,1,1,1},
                    {0,0,0,0}
            },
            {
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,1,0,0}
            }


    };
}
