import javax.swing.*;

public class WindowGame {
    private JFrame window;
    //    public static final int WIDTH = 445, HEIGHT = 692;
    public static final int WIDTH = 500, HEIGHT = 692;
    private Board board;
    public WindowGame() {
        window = new JFrame("Tetris");
        window.setSize(WIDTH,HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        board = new Board();
        window.add(board);
        window.addKeyListener(board);
        window.setVisible(true);
        Board.startRboard();

    }

    public static void main(String[] args) {
        new WindowGame();
    }
}


