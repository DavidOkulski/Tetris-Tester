package Piece;

public class JPiece extends Piece {
    public JPiece() {
        shape = theShape;
    }

    @Override
    public PieceType getType() {
        return PieceType.PIECE_J;
    }

    private static int[][][] theShape = { // J
            {
                    {1,0,0,0},
                    {1,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            },
            {
                    {0,1,1,0},
                    {0,1,0,0},
                    {0,1,0,0},
                    {0,0,0,0}
            },
            {
                    {0,0,0,0},
                    {1,1,1,0},
                    {0,0,1,0},
                    {0,0,0,0}
            },
            {
                    {0,1,0,0},
                    {0,1,0,0},
                    {1,1,0,0},
                    {0,0,0,0}
            }
    };
}
