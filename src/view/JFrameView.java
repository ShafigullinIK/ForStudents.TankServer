package view;

import model.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JFrameView extends JFrame {

    private static final int DEFAULT_WINDOW_X_SIZE = 600;
    private static final int DEFAULT_WINDOW_Y_SIZE = 800;
    private static final int cellSize = 50;
    private static final int upOtstup = 35;

    private Player player1;

    private Player player2;

    private Field field;

    private Game game;

    private BufferedImage screen;

    private Map<FieldCellType, BufferedImage> wallImageMap = new HashMap<>();
    private Map<Directions, BufferedImage> tankDirectionImages = new HashMap<>();



    private Graphics screenGraphics;

    public JFrameView(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.field = new Field(DEFAULT_WINDOW_X_SIZE / cellSize, DEFAULT_WINDOW_Y_SIZE / cellSize);
        initWindow();
        initGame();
        initImages();
        this.screenGraphics = this.getGraphics();
        addKeyListeners();
    }


    private void initWindow() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, DEFAULT_WINDOW_X_SIZE, DEFAULT_WINDOW_Y_SIZE + upOtstup);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void initGame() {
        game = new Game(player1, player2, field);
    }

    private void initImages() {
        screen = new BufferedImage(DEFAULT_WINDOW_X_SIZE, DEFAULT_WINDOW_Y_SIZE, BufferedImage.TYPE_3BYTE_BGR);
        try {
            BufferedImage tmp = ImageIO.read(new File("images\\breakable_wall.png"));
            wallImageMap.put(FieldCellType.BREAKABLE_WALL, tmp);
            tmp = ImageIO.read(new File("images\\unbreakable_wall.png"));
            wallImageMap.put(FieldCellType.UNBREAKABLE_WALL, tmp);
            tmp = ImageIO.read(new File("images\\water.png"));
            wallImageMap.put(FieldCellType.WATER, tmp);
            tmp = ImageIO.read(new File("images\\background.png"));
            wallImageMap.put(FieldCellType.BACKGROUND, tmp);

            tmp = ImageIO.read(new File("images\\tank_up.png"));
            tankDirectionImages.put(Directions.UP, tmp);
            tmp = ImageIO.read(new File("images\\tank_right.png"));
            tankDirectionImages.put(Directions.RIGHT, tmp);
            tmp = ImageIO.read(new File("images\\tank_down.png"));
            tankDirectionImages.put(Directions.DOWN, tmp);
            tmp = ImageIO.read(new File("images\\tank_left.png"));
            tankDirectionImages.put(Directions.LEFT, tmp);

        } catch (IOException e) {
            System.out.println("Проблема в InitImage()");
        }
    }

    private void addKeyListeners() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        drawAll();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    private void drawAll() {
        Graphics imageGraphics = screen.getGraphics();
        Cell[][] cells = field.getField();
        drawField(imageGraphics, cells);
        drawTanks(imageGraphics);
        screenGraphics.drawImage(screen, 0, upOtstup, null);

    }

    private void drawField(Graphics imageGraphics, Cell[][] cells) {
        BufferedImage unbr = wallImageMap.get(FieldCellType.UNBREAKABLE_WALL);
        BufferedImage br = wallImageMap.get(FieldCellType.BREAKABLE_WALL);
        BufferedImage water = wallImageMap.get(FieldCellType.WATER);
        BufferedImage background = wallImageMap.get(FieldCellType.WATER);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                switch (cells[i][j].getCellType()) {
                    case UNBREAKABLE_WALL:
                        imageGraphics.drawImage(unbr, i * cellSize, j * cellSize, cellSize, cellSize, null);
                        break;
                    case BREAKABLE_WALL:
                        imageGraphics.drawImage(br, i * cellSize, j * cellSize, cellSize, cellSize, null);
                        break;
                    case WATER:
                        imageGraphics.drawImage(water, i * cellSize, j * cellSize, cellSize, cellSize, null);
                        break;
                    default:
                        imageGraphics.drawImage(background, i * cellSize, j * cellSize, cellSize, cellSize, null);
                }
            }
        }
    }

    private void drawTanks(Graphics imageGraphics) {
        Tank tank1 = player1.getTank();
        Tank tank2 = player2.getTank();
        drawTank(imageGraphics, tank1);
        drawTank(imageGraphics, tank2);
    }

    private void drawTank(Graphics imageGraphics, Tank tank) {
        int x = tank.getTankPoint().getX();
        int y = tank.getTankPoint().getY();
        switch (tank.getTankDirection()) {
            case DOWN:
                BufferedImage t_d = tankDirectionImages.get(Directions.DOWN);
                imageGraphics.drawImage(t_d, x * cellSize, y * cellSize, cellSize, cellSize, null);
                break;
            case LEFT:
                BufferedImage t_l = tankDirectionImages.get(Directions.LEFT);
                imageGraphics.drawImage(t_l, x * cellSize, y * cellSize, cellSize, cellSize, null);
                break;
            case UP:
                BufferedImage t_u = tankDirectionImages.get(Directions.UP);
                imageGraphics.drawImage(t_u, x * cellSize, y * cellSize, cellSize, cellSize, null);
                break;
            case RIGHT:
                BufferedImage t_r = tankDirectionImages.get(Directions.DOWN);
                imageGraphics.drawImage(t_r, x * cellSize, y * cellSize, cellSize, cellSize, null);
                break;
        }
    }
}
