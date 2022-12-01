package moura.pedro;

import javax.swing.*;

public class StartGame extends JFrame {
    public static void main(String[] args) {
        new StartGame();
    }

    StartGame() {
        add(new Screen());
        setTitle("Snake game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}