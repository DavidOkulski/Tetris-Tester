package Piece;

public class TPiece extends Piece {
        public TPiece() {
            shape = theShape;
        }

        @Override
        public PieceType getType() {
            return PieceType.PIECE_T;
        }

        private static int[][][] theShape = { // T
                {
                        {0,1,0,0},
                        {1,1,1,0},
                        {0,0,0,0},
                        {0,0,0,0}
                },
                {
                        {0,1,0,0},
                        {0,1,1,0},
                        {0,1,0,0},
                        {0,0,0,0}

                },
                {
                        {1,1,1,0},
                        {0,1,0,0},
                        {0,0,0,0},
                        {0,0,0,0}

                },
                {
                        {0,1,0,0},
                        {1,1,0,0},
                        {0,1,0,0},
                        {0,0,0,0}

                }
        };
}
