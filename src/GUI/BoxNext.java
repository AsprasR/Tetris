/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Box.ListOfPoints;
import Box.Tetrimonoes.Tetrimono;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author Nightspire
 */
public class BoxNext extends JPanel {

    private final static int ROW = 4, COL = 4;
    private final int side;
    private final BoxColour[][] points;
    //   private final Dimension boxSize;

    public BoxNext() {
        this.side = 101;
        points = new BoxColour[ROW][COL];
        setPreferredSize(new Dimension(side, side));
        setLayout(new GridLayout(ROW, COL));
        setBox();
    }

    private void setBox() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                points[i][j] = new BoxColour(null);
                points[i][j].setOpaque(true);
                //        points[i][j].setPreferredSize(boxSize);
                add(points[i][j]);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(Color.BLACK);
        g2d.fillRect(0, 0, side, side);
        g2d.setColor(Color.BLUE);
        int size = 30;
        int k = 3;
        int l = 2;
        int t = (side - k * size) / 2 - (k - 1) / 2;
        int z = (side - l * size) / 2 + l;
       // System.out.println(t);
        //System.out.println(z);
        for (int i = 0; i < k; i++) {
            g2d.fillRect(t, z, size, size);
            t += size + 1;
        }
        t = t - 1;
        g2d.fillRect(t - 2 * (size) - 1, z + size + 1, size, size);
       // System.out.println(z + size + 1);
    }

    public Tetrimono addRandom() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                points[i][j].setColor(null);
            }
        }
        return Tetrimono.getRandomType();
    }

}
