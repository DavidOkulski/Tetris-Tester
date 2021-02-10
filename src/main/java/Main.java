
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame  tetrisFrame = new JFrame ("Tetris Game");
        tetrisFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        TetrisGame  game = new TetrisGame(tetrisFrame);
        game.startGame();
    }
}
