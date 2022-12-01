package moura.pedro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Screen extends JPanel implements ActionListener {
    public static final int WIDTH_SCREEN = 1300;
    public static final int HEIGHT_SCREEN = 750;
    public static final int BLOCK_SIZE = 50;
    public static final int UNITS = WIDTH_SCREEN * HEIGHT_SCREEN / (BLOCK_SIZE * BLOCK_SIZE);
    public static final int BREAK = 200;
    public static final String FONT_NAME = "Ink Free";
    private final int [] axleX = new int[UNITS];
    private final int [] axley = new int[UNITS];
    private int BodySnake = 6;
    private int BlockEaten;
    private int blockX;
    private int blockY;
    private char direction = 'R'; // U - 'UP', L - 'LOW', R - 'RIGHT', L 'LEFT'
    private boolean status = false;
    Timer timer;
    Random random;


    Screen() {
        random = new Random ();
        setPreferredSize(new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN));
        setBackground(new Color (187, 255, 185));
        setFocusable(true);
        startGame ();
    }

    public void startGame (){
        createBlock();
        status = true;
        timer = new Timer (BREAK, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintScreen(g);
    }
    public void paintScreen (Graphics g){
        if (status) {
            g.setColor(Color.DARK_GRAY);
            g.fillOval(blockX, blockY, BLOCK_SIZE, BLOCK_SIZE);

            for (int i = 0; i < BodySnake; i++){
                if (i == 0){
                    g.setColor(Color.GREEN);
                    g.fillRect(axleX[0], axley[0], BLOCK_SIZE, BLOCK_SIZE);
                }else{
                    g.setColor(new Color(66, 220, 10));
                    g.fillRect(axleX[i], axley[i], BLOCK_SIZE,BLOCK_SIZE);
                }
            }
            g.setColor(Color.RED);
            g.setFont(new Font(FONT_NAME, Font.BOLD, 0));
            FontMetrics metrics = getFontMetrics((g.getFont()));
            g.drawString ("Points: " + BlockEaten, (BLOCK_SIZE - metrics.stringWidth("Points: " + BlockEaten)) / 2, g.getFont().getSize());
        }else{
            endGame(g);
        }
    }

    public void createBlock (){
        Random ran = new Random();
        blockX = ran.nextInt(WIDTH_SCREEN/BLOCK_SIZE)* BLOCK_SIZE;
        blockY = ran.nextInt(HEIGHT_SCREEN/BLOCK_SIZE)* BLOCK_SIZE;
    }

    public void endGame (Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font(FONT_NAME, Font.BOLD, 40));
        FontMetrics fontePontuacao = getFontMetrics(g.getFont());
        g.drawString("Points: " + BlockEaten, (WIDTH_SCREEN - fontePontuacao.stringWidth("Points: " + BlockEaten)) / 2, g.getFont().getSize());
        g.setColor(Color.red);
        g.setFont(new Font(FONT_NAME, Font.BOLD, 75));
        FontMetrics fonteFinal = getFontMetrics(g.getFont());
        g.drawString("\uD83D\uDE1D End Game.", (WIDTH_SCREEN - fonteFinal.stringWidth("End Game")) / 2, HEIGHT_SCREEN/ 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
