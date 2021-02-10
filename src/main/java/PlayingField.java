import Piece.*;

//
// PlayingField.java
//
public class PlayingField implements MoveListener
{
    private static final int 	PLAY_WIDTH = 16;
    private static final int 	PLAY_HEIGHT = 25;

    private int theWidth;
    private int theHeight;
    private int pieceXStart;
    private int pieceYStart;

	private ResultCollector resultCollector;

    private PieceFactory theFactory;

    /* This array represents the board itself */
    private Piece.PieceType [][]			background;

    /* The current playing piece */
    private Piece		currentPiece;
    private Piece       nextPiece;

    private int		lineCount;
    private int     score;
    private int     level;
    private int     pieceCount;
    private int     passy;

    private PlayingField () {

    }

    PlayingField (PieceFactory f, ResultCollector res) {
        this(f,res,PLAY_WIDTH, PLAY_HEIGHT);
    }

    PlayingField (PieceFactory f, ResultCollector res, int width, int height) {
        assert width >=6;
        assert height >= 6;

        resultCollector = res;
        theFactory = f;
        theWidth = width;
        theHeight = height;

        background = new Piece.PieceType[theWidth][theHeight];
        pieceXStart = width / 2 - 2;
        pieceYStart = 0;
        passy = pieceYStart;

        init();
    }

    public void init ( ) {
        for ( int i = 0; i < theWidth; i++ ) {
            for ( int j = 0; j < theHeight; j++ ) {
                if ( i == 1 || i == 2 || i == (theWidth - 3) || i == (theWidth - 2)) {
                    background[i][j] = Piece.PieceType.PIECE_OBSTACLE;
                }
                else if ( ( i > 0 && i < (theWidth-1)) && (j == (theHeight - 3 ) || j == (theHeight - 2 )) ) {
                    background[i][j] = Piece.PieceType.PIECE_OBSTACLE;
                }
                else {
                    background[i][j] = Piece.PieceType.PIECE_NONE;
                }
            }
        }
        currentPiece = makeNewPiece();
        nextPiece = makeNewPiece();

        lineCount = 0;
        score = 0;
        level = 0;
    }

    public int getWidth () {
        return theWidth;
    }

    public int getHeight () {
        return theHeight;
    }

    public int getPieceXStart(){return pieceXStart;} //Added Helper Function

    public int getPieceYStart(){return pieceYStart;} //Added Helper Function

    public void setLevel(int newLevel){level=newLevel;} //Testing function that changes the level


    private Piece makeNewPiece () {
        Piece p = theFactory.createPiece();
        p.setX(pieceXStart);
        p.setY(pieceYStart);

        return p;
    }

    public Piece getCurrentPiece () {
        return currentPiece;
    }
    public Piece getNextPiece () {
        return nextPiece;
    }
    public Piece.PieceType getContents (int x, int y) {
        return background[x][y];
    }

    public int	getLineCount () {
        return lineCount;
    }
    public int  getScore() { return score;}
    public int  getLevel() { return level;}

    //
    // This method checks to see if the current piece
    // will fit into the x,y position passed in as parameters
    // We use this to decide if we are hitting the wall, or pieces
    // in the background
    //
    private boolean	pieceFits () {
        boolean itFits = true;

            for (int i = 0; i < Piece.PIECE_SIZE; i++) {
                for (int j = 0; j < Piece.PIECE_SIZE; j++) {
                    if ((background[currentPiece.getX() + i][currentPiece.getY() + j] != Piece.PieceType.PIECE_NONE) &&
                            (currentPiece.isCovering(i, j))) {
                        itFits = false;
                    }
                }
            }

        return itFits;
    }

    //
    // This method adds the current piece into the background.
    // For each square that makes up the piece, we set the
    // background color to the piece's color
    //
    private void addToBackground () {
        for ( int i =0;i < Piece.PIECE_SIZE; i++ ) {
            for ( int j=0; j<Piece.PIECE_SIZE; j++ ) {
                if ((currentPiece.isCovering(i,j) ) ) {
                    background[currentPiece.getX() + i][currentPiece.getY() + j] = currentPiece.getType();
                }
            }
        }
    }

    //
    // This method moves the contents of the playing field down
    // one row, starting at the row passed in as the parameter
    //
    private void moveRowsDown ( int row ) {
        for ( int i = row; i > 0; i-- ) {
            for ( int col = 3; col < theWidth - 3; col++ ) {
                background[col][i] = background[col][i - 1];
            }
        }

        // Insert a row of black at the top
        for ( int col = 3; col < theWidth - 3 ; col++ ) {
            background[col][0] = Piece.PieceType.PIECE_NONE;
        }
    }

    //
    // This method checks to see if there are any completed
    // lines in the playingfield.
    // If it finds any, it calls moveDown to "remove" the completed
    // line(s)
    //
    private void checkLines() {
        int numLines = 0;

        for ( int row = theHeight - 4 ; row >= 0; row-- ) {
            boolean	fullLine = true;
            for ( int col = 3; col < theWidth - 3; col++ ) {
                if ( background[col][row] == Piece.PieceType.PIECE_NONE ) {
                    fullLine = false;
                }
            }

            if ( fullLine ) {
                numLines++;
                moveRowsDown ( row );
                // We need to recheck the current row if we just moved
                // everything down...
                row++;
            }
        }

        if (numLines != 0) {
            switch (numLines) {
                case 1:
                    score += 40 * (level + 1);
                    break;
                case 2:
                    score += 100 * (level + 1);
                    break;
                case 3:
                    score += 300 * (level + 1);
                    break;
                case 4:
                    score += 1200 * (level + 1);
                    break;
                default:
                    System.out.println("Invalid line count");
                    System.exit(0);
            }
            int newLineCount = lineCount + numLines;
            if ((lineCount / 10) < (newLineCount / 10)) {
                level++;
            }
            lineCount = newLineCount;
        }
    }

    public void nextMove(Move theMove) {
        switch (theMove) {
            case gravityDown:
                // timeout gravityDown();
                break;
            case moveDown:
                moveDown();
                break;
            case moveLeft:
                moveLeft();
                break;
            case moveRight:
                moveRight();
                break;
            case rotateLeft:
                rotateLeft();
                break;
            case rotateRight:
                rotateRight();
                break;
            default:
                assert false;
        }
    }

    //
    // This method is called when the timer runs out.  We then try to move
    // the current piece down.  If it doesn't fit, we create a new piece
    //
    public boolean timeout () {
        boolean gameOver = false;

        currentPiece.moveDown();
        if ( !pieceFits () ) {
            currentPiece.moveUp();
            // Can't move the piece down, have to put the piece into the background
            // and make a new piece

            addToBackground();
            checkLines();

            currentPiece = nextPiece;
            nextPiece = makeNewPiece();

            // If we can't add a new piece, the game is over.
            if ( !pieceFits ()) {
                gameOver = true;
                submitGameResult();

            }
        }
        return gameOver;
    }

    // Helper function too see if a piece moved down
    // Added to increase code readability
    private boolean movedDown(){
        if(currentPiece.getY() != passy){
            passy = currentPiece.getY();
            return true;
        }
        return false;
    }

    // This is called in response to pressing the <space> key.
    // We drop the piece down one row.
    public void moveDown () {
        currentPiece.moveDown();
        if ( !pieceFits () ) {
            currentPiece.moveUp();
        }
        if(movedDown()){ score++; }
    }

    public void rotateLeft () {
        currentPiece.rotateLeft();
        if ( !pieceFits ()) {
            currentPiece.rotateRight();
        }
    }

    public void rotateRight() {
        currentPiece.rotateRight();
        if ( !pieceFits ()) {
            currentPiece.rotateLeft();
        }
    }

    public void moveLeft () {
        currentPiece.moveLeft();
        if ( !pieceFits () ) {
            currentPiece.moveRight();
        }
    }

    public void moveRight() {
        currentPiece.moveRight();
        if ( !pieceFits ()) {
           currentPiece.moveLeft();
        }
    }
    protected void submitGameResult() {
		boolean b = resultCollector.submitGameResult(theFactory.gameId(), score, lineCount);
		if (!b) {
			System.out.println("Failed to submit result.");
		}
   }
}
