package olympiaRL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow implements KeyListener {
    private JFrame window = new JFrame();
    private JPanel panel = new JPanel();
    private LocalMap localMap;
    private boolean isKeyPressed = false;
    private static TilesetAtlas tilesetAtlas = new TilesetAtlas();

    public static final int VIEW_X = 80;
    public static final int VIEW_Y = 45;
    private static final int WINDOW_WIDTH = 1280;
    private static final int WINDOW_HEIGHT = 720;

    public GameWindow(){}

    public void init(){
        initPanel();
        initTestArea();
        initWindow();

    }

    private void initWindow(){
        window.addKeyListener(this);
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        window.setResizable(false);
        window.pack();
        window.setVisible(true);
    }
    private void initPanel(){
        panel.setLayout(new GridLayout(VIEW_Y, VIEW_X));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        //panel.setBackground(Color.black);

    }
    private void initTestArea(){
        localMap = new LocalMap(VIEW_X, VIEW_Y);
        localMap.testMap();
        updateScreen();
    }
    private void updateScreen(){
        for (int y = 0; y < VIEW_Y; y++) {
            for (int x = 0; x < VIEW_X; x++) {
                JLabel cell = new JLabel();
                if(localMap.isCharacterPresent(x, y)){
                    cell.setIcon(tilesetAtlas.drawTile(localMap.getCharacter(x, y).getToken(), localMap.getCharacter(x, y).getColor()));
                } else {
                    cell.setIcon(tilesetAtlas.drawTile(localMap.getTerrain()[y][x].getType(), localMap.getTerrain()[y][x].getColor()));
                }
                cell.setVisible(true);
                panel.add(cell);
            }
        }
    }
    private void clear(){
        panel.removeAll();
        panel.revalidate();
        panel.repaint();
    }



    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(!isKeyPressed) {
            long startTime = System.currentTimeMillis();
            switch (e.getKeyCode()) {
                case 87: {
                    localMap.getPlayer().moveUp();
                    break;
                }
                case 83: {
                    localMap.getPlayer().moveDown();
                    break;
                }
                case 65: {
                    localMap.getPlayer().moveLeft();
                    break;
                }
                case 68: {
                    localMap.getPlayer().moveRight();
                    break;
                }
                default: {
                    System.out.println("Unsupported Key");
                }
            }
            clear();
            updateScreen();
            long stopTime = System.currentTimeMillis();
            System.out.println("Player coordinates | X: " + localMap.getPlayer().getX() + ", Y: " + localMap.getPlayer().getY());
            System.out.println("Execution time: " + ((double) (stopTime - startTime) / 1000));
            isKeyPressed = true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        isKeyPressed = false;
    }
}
