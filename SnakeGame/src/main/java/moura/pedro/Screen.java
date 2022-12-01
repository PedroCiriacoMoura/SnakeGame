package moura.pedro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Screen extends JPanel implements ActionListener {
    public static final int WIDTH_SCREEN = 1300;
    public static final int HEIGHT_SCREEN = 750;
    public static final int BLOCK_SIZE = 50;
    public static final int UNITS = WIDTH_SCREEN * HEIGHT_SCREEN / (BLOCK_SIZE * BLOCK_SIZE);
    public static final int BREAK = 200;
    public static final String FONT_NAME = "Ink Free";
    private final int [] axleX = new int[UNITS];
    private final int [] axleY = new int[UNITS];
    private int BodySnake = 6;
    private int BlockEaten;
    private int blockX;
    private int blockY;
    private char direction = 'R'; // U - 'UP', D - 'DOWN', R - 'RIGHT', L 'LEFT'
    private boolean status = false;
    Timer timer;
    Random random;


    Screen() {
        random = new Random ();
        setPreferredSize(new Dimension(WIDTH_SCREEN, HEIGHT_SCREEN));
        setBackground(new Color (215, 255, 215));
        setFocusable(true);
        addKeyListener(new KeyReaderAdapter ());
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
            g.setColor(Color.RED);
            g.fillOval(blockX, blockY, BLOCK_SIZE, BLOCK_SIZE);

            for (int i = 0; i < BodySnake; i++){
                if (i == 0){
                    g.setColor(Color.GREEN);
                    g.fillRect(axleX[0], axleY[0], BLOCK_SIZE, BLOCK_SIZE);
                }else{
                    g.setColor(new Color(66, 220, 10));
                    g.fillRect(axleX[i], axleY[i], BLOCK_SIZE,BLOCK_SIZE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font(FONT_NAME, Font.BOLD, 40));
            FontMetrics fontePontuacao = getFontMetrics(g.getFont());
            g.drawString("Points: " + BlockEaten, (WIDTH_SCREEN - fontePontuacao.stringWidth("Points: " + BlockEaten)) / 2, g.getFont().getSize());
            g.setColor(Color.red);
            g.setFont(new Font(FONT_NAME, Font.BOLD, 75));
            FontMetrics fonteFinal = getFontMetrics(g.getFont());
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
        g.drawString("GAME OVER :/", (WIDTH_SCREEN - fonteFinal.stringWidth("End Game")) / 2, HEIGHT_SCREEN/ 2);
    }

    public void actionPerformed(ActionEvent e) {
        if (status){
            walk ();
            reackBlock();
            validateLimits ();
        }
        repaint();
    }

    private void walk (){
        for (int i = BodySnake; i > 0; i--){
            axleX[i] = axleX[i - 1];
            axleY[i] = axleY[i - 1];
        }

        switch (direction){
            case 'U':
                axleY[0] = axleY[0] - BLOCK_SIZE;
                break;
            case 'D':
                axleY[0] = axleY[0] + BLOCK_SIZE;
                break;
            case 'L':
                axleX[0] = axleX[0] - BLOCK_SIZE;
                break;
            case 'R':
                axleX[0] = axleX[0] + BLOCK_SIZE;
                break;
        }
    }

    public  void reackBlock (){
        if (axleX[0] == blockX && axleY[0] == blockY){
            BodySnake++;
            BlockEaten++;
            createBlock();
        }

        if (axleX[0] < 0 || axleY[0] > WIDTH_SCREEN){
            status = false;
        }

        if (axleY[0] < 0 || axleY[0] > HEIGHT_SCREEN){
            status = false;
        }

        if (!status){
            timer.stop();
        }
    }

    public void validateLimits (){
        for (int i = BodySnake; i >0; i--){
            if (axleX[0] == axleX[i] && axleY[0] == axleY[i]){
                status = false;
                break;
            }
        }
    }

    public class KeyReaderAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'L') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'R') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'U') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'D') {
                        direction = 'D';
                    }
                    break;
                default:
                    break;
            }
        }

    }
}


