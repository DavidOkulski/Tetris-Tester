import Piece.*;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class PlayingFieldTest {

    private class IPieceFactory implements PieceFactory {
        public Piece createPiece() {
            return new IPiece();
        }
        public int   gameId() {
            return 0;
        }
    }
	private class NullResultCollector implements ResultCollector {
		public boolean submitGameResult (int gameId, int score, int lineCount) {
			return true;
		}
	}


    @Test
    void init() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        assertEquals(Piece.PieceType.PIECE_I, theField.getCurrentPiece().getType());
        assertEquals(Piece.PieceType.PIECE_I, theField.getNextPiece().getType());
        assertEquals(0, theField.getLevel());
        assertEquals(0, theField.getScore());
        assertEquals(0, theField.getLineCount());
    }

    @Test
    void getWidth() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        assertEquals(16, theField.getWidth());
    }

    @Test
    void getHeight() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        assertEquals(25, theField.getHeight());
    }

    @Test
    void getCurrentPiece() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        assertTrue (theField.getNextPiece() instanceof IPiece );
    }

    @Test
    void getNextPiece() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        assertTrue (theField.getNextPiece() instanceof IPiece );
    }

    @Test
    void getContents() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        for( int i = 0;i<30;i++){theField.moveDown();}
        theField.timeout();
        assertEquals(Piece.PieceType.PIECE_OBSTACLE, theField.getContents(1,0)); // Obstacle
        assertEquals(Piece.PieceType.PIECE_NONE, theField.getContents(0,0));     // Piece None
        assertEquals(Piece.PieceType.PIECE_I, theField.getContents(8,21));       // IPiece

    }

    @Test
    void getLineCount() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        clearFourLines(theField);
        assertEquals(4,theField.getLineCount());
    }

    @Test
    void nextMove() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        int PieceXStart = theField.getPieceXStart();

        theField.nextMove(Move.moveDown);
        assertEquals(1, theField.getCurrentPiece().getY());             // Test moveDown
        theField.nextMove(Move.moveLeft);
        assertEquals(PieceXStart-1, theField.getCurrentPiece().getX()); // Test moveLeft
        theField.nextMove(Move.moveRight);
        assertEquals(PieceXStart, theField.getCurrentPiece().getX());             // Test moveRight
        theField.nextMove(Move.rotateLeft);
        assertEquals(1, theField.getCurrentPiece().getPieceRotation()); // Test rotateLeft
        theField.nextMove(Move.rotateRight);
        assertEquals(0, theField.getCurrentPiece().getPieceRotation()); // Test rotateRight

    }

    @Test
    void timeout() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        for( int i=0;i<20;i++) {
            for(int j =0;j<25;j++){ theField.moveDown();}
            assertEquals(false, theField.timeout());
        }
        theField.moveDown();
        assertEquals(true, theField.timeout());
    }

    @Test
    void moveDown() {
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        int PieceXStart = theField.getPieceYStart();
        theField.moveDown();
        assertEquals(1, theField.getCurrentPiece().getY());  // Test a single move down
        for(int j =0;j<25;j++){ theField.moveDown();}
        assertEquals(20, theField.getCurrentPiece().getY()); // Test an invalid move down

    }

    @Test
    void rotateLeft() {
        /*
        {0,0,0,0},      {0,0,1,0},      {0,0,0,0},     {0,1,0,0},
        {1,1,1,1},      {0,0,1,0},      {0,0,0,0},     {0,1,0,0},
        {0,0,0,0},      {0,0,1,0},      {1,1,1,1},     {0,1,0,0},
        {0,0,0,0},      {0,0,1,0},      {0,0,0,0},     {0,1,0,0},
         */
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        theField.rotateLeft();
        assertEquals(1, theField.getCurrentPiece().getPieceRotation());  // Test a simple rotateRight
        for(int i = 0;i<4;i++){ theField.rotateLeft();}
        assertEquals(1, theField.getCurrentPiece().getPieceRotation());  // Test a full rotateRight
        for(int i = 0;i<10;i++){ theField.moveLeft();}
        theField.rotateLeft();
        assertEquals(1, theField.getCurrentPiece().getPieceRotation());  // Test an Invalid rotateRight
    }

    @Test
    void rotateRight() {
        /*
        {0,0,0,0},      {0,0,1,0},      {0,0,0,0},     {0,1,0,0},
        {1,1,1,1},      {0,0,1,0},      {0,0,0,0},     {0,1,0,0},
        {0,0,0,0},      {0,0,1,0},      {1,1,1,1},     {0,1,0,0},
        {0,0,0,0},      {0,0,1,0},      {0,0,0,0},     {0,1,0,0},
         */
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        theField.rotateRight();
        assertEquals(3, theField.getCurrentPiece().getPieceRotation());  // Test a simple rotateRight
        for(int i = 0;i<4;i++){ theField.rotateRight();}
        assertEquals(3, theField.getCurrentPiece().getPieceRotation());  // Test a full rotateRight
        for(int i = 0;i<10;i++){ theField.moveLeft();}
        theField.rotateRight();
        assertEquals(3, theField.getCurrentPiece().getPieceRotation());  // Test an Invalid rotateRight
    }

    @Test
    void moveLeft() {
        /*  W = Wall, B = Blank, Width-X
            X = 0  1  2   3  4  5  6
                B  W  W  {0, 0, 0, 0},
                B  W  W  {1, 1, 1, 1},
                B  W  W  {0, 0, 0, 0},
                B  W  W  {0, 0, 0, 0},
         */
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        int PieceXStart = theField.getPieceXStart();
        int width = theField.getWidth();
        theField.moveLeft();
        assertEquals(PieceXStart-1, theField.getCurrentPiece().getX()); // Test to see if one move to the left works
        for(int i = 0;i<20;i++){ theField.moveLeft();}
        assertEquals(3, theField.getCurrentPiece().getX());            // Test to assure the moveLeft() stops at walls
    }

    @Test
    void moveRight() {

        /*  W = Wall, B = Blank, Width-X
            X = -7 -6 -5 -4   -3 -2 -1
                {0, 0, 0, 0},  W  W  B
                {1, 1, 1, 1},  W  W  B
                {0, 0, 0, 0},  W  W  B
                {0, 0, 0, 0},  W  W  B
         */
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        int PieceXStart = theField.getPieceXStart();
        int width = theField.getWidth();
        theField.moveRight();
        assertEquals(PieceXStart+1, theField.getCurrentPiece().getX()); // Test to see if one move to the right works
        for(int i = 0;i<width;i++){ theField.moveRight();}
        assertEquals(width-7, theField.getCurrentPiece().getX());       // Test to assure the moveRight() stops at walls
    }

    @Test
    void levelUp(){
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        for(int j=0;j<3;j++) { clearFourLines(theField);}
        assertEquals(1, theField.getLevel()); // 3x4=12 lines cleared 10-20: Level 1

        for(int j=0;j<3;j++) { clearFourLines(theField);}
        assertEquals(2,theField.getLevel()); // 6x4=24 lines cleared 20-30: Level 2

        for(int j=0;j<24;j++) { clearFourLines(theField);}
        assertEquals(12,theField.getLevel()); // 30x4=120 lines cleared 120-130: Level 12
    }
    @Test
    void multipleLines(){

        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        clearXLines(theField,1);
        System.out.println(theField.getScore());
        assertEquals(40, theField.getScore());  //1 line completed, on level 1: 40pts


        PlayingField theField2 = new PlayingField(factory, new NullResultCollector());
        clearXLines(theField2,2);
        assertEquals(100, theField2.getScore());  //2 lines completed, on level 1, 100pts


        PlayingField theField3 = new PlayingField(factory, new NullResultCollector());
        clearXLines(theField3,3);
        assertEquals(300, theField3.getScore());  //3 lines completed, on level 1, 300pts


        PlayingField theField4 = new PlayingField(factory, new NullResultCollector());
        clearFourLines(theField4);
        assertEquals(1200, theField4.getScore());  //4 lines completed, on level 1, 1200pts

    }

    @Test
    void levelScore(){
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        theField.setLevel(0);
        Piece p = theField.getCurrentPiece();
        clearFourLines(theField);
        assertEquals(1200, theField.getScore());  //4 lines completed on level 0 1200pts

        theField.setLevel(1);
        clearFourLines(theField);
        assertEquals(3600, theField.getScore());  //4 lines completed on level 1 1200pts+2400=3600

        theField.setLevel(9);
        clearFourLines(theField);
        assertEquals(15600, theField.getScore());  //4 lines completed on level 9 1200pts+2400+12000=15600

        theField.setLevel(100);
        clearFourLines(theField);
        assertEquals(136800, theField.getScore());  //4 lines completed on level 100 1200+2400+12000+121200=136800

    }

    /* A1ScoringTest()
     * This Test is to show the bug of incorrect scoring.
     * The incorrect scoring is that the game doesn't provide any points for hard-drops(Dropping by space bar)
     * Demonstrating the bug in the shortest test is it's purpose from the requirements of A1.
     *
     * No simple bug fix was found for this problem, from testing and looking at the code we determined that the programer
     * simply forgot this requirement. We fixed this issue by adding a check to see if the piece actually moved down when the function
     * moveDown() was called and if the piece moved down the score would be incremented by 1. (From the requirements on the wiki)
     */
    @Test
    void A1ScoringTest(){
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        theField.moveDown();
        assertEquals(1, theField.getScore()); // Test so see if the score increments after one down

        while(!theField.timeout()){
            for(int j =0;j<25;j++){ theField.moveDown();}
        }
        System.out.println(theField.getScore());
        assertEquals(210, theField.getScore()); // Test to see if the score is correct once the board fills up, Sum of 1-20=210
    }

    /* A1PieceTest()
     * This Test is to show the bug of an IPiece going off the screen.
     * The issue is when the piece is rotated to the left and moved to the left there is an array out of bounds error thrown
     * Demonstrating the bug in the shortest test is it's purpose from the requirements of A1.
     *
     * The Bug was incorrect indexing of the game board in init(), background[0][0] was PIECE_OBSTACLE instead of PIECE_NONE
     * causing an array out of bounds error when the piece was moved back onto the board. The board is following the specs given
     * in the wiki. The bug was fixed by fixing the background indexing in init().
     */
    @Test
    void A1IPieceTest(){
        PieceFactory factory = new IPieceFactory();
        PlayingField theField = new PlayingField(factory, new NullResultCollector());
        int width = theField.getWidth();
        theField.rotateLeft();
        for(int i=0; i<6;i++){theField.moveLeft();}
        Piece p = theField.getCurrentPiece();
        assertEquals(1, p.getX()); // To test if the piece went through the wall or stopped, before raising an array out of bounds error

    }

    private void clearFourLines(PlayingField theField){
        Piece p = theField.getCurrentPiece();
        for (int i = 1; i < 11; i++) {
            p = theField.getCurrentPiece();
            p.rotateLeft();
            p.setX(i);
            p.setY(18);
            theField.timeout();
        }

    }

    private void clearXLines(PlayingField theField, int numberOfTimes){
        Piece p = theField.getCurrentPiece();
        for(int i = 0; i<numberOfTimes;i++){
            p = theField.getCurrentPiece();
            p.setX(3);
            p.setY(20-i);
            theField.timeout();
            p = theField.getCurrentPiece();
            p.setX(7);
            p.setY(20-i);
            theField.timeout();
        }
        p = theField.getCurrentPiece();
        p.rotateLeft();
        p.setX(9);
        p.setY(18);
        theField.timeout();
        p = theField.getCurrentPiece();
        p.rotateLeft();
        p.setX(10);
        p.setY(18);
        theField.timeout();
    }

}
