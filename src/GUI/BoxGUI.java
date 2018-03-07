/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Box.Coords;
import Box.ListOfPoints;
import Box.Tetrimonoes.Tetrimono;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author dev
 */
public final class BoxGUI extends JPanel {

    private final BoxColour[][] points;
    private final int col, row, size;
    private final Dimension boxSize;
    private ListOfPoints listOfPoints, finishedPoints;
    private final Tetris tetris;
    private final BoxNext boxNext;
    private boolean status, started, continued;
    //  private final Score score;
    private Tetrimono tetrimono;
    private int rotate;

    public BoxGUI(ListOfPoints listOfPoints, Score score, Tetris tetris, BoxNext boxNext, int col, int rows, int size) {
        this.col = col;
        this.row = rows;
        this.listOfPoints = listOfPoints;
        this.size = size;
        //      this.score = score;
        score.setBackground(Color.BLACK);
        this.boxNext = boxNext;
        this.tetris = tetris;
        finishedPoints = new ListOfPoints();
        started = false;
        continued = false;
        points = new BoxColour[rows][col];
        boxSize = new Dimension(this.size, this.size);

        setLayout(new GridLayout(rows, col));
        setBox();
    }

    public synchronized boolean getStatus() {
        return status;
    }

    public synchronized void setStatus(boolean status) {
        this.status = status;
    }

    private synchronized void setBox() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                points[i][j] = new BoxColour(null);
                points[i][j].setOpaque(true);
                points[i][j].setPreferredSize(boxSize);
                add(points[i][j]);
            }
        }
    }

    private synchronized void setColors(ListOfPoints listOfPoints, boolean clear) {
        for (int i = 0; i < listOfPoints.size(); i++) {
            int x = listOfPoints.getCoords(i).getX();
            int y = listOfPoints.getCoords(i).getY();
            if (clear) {
                points[x][y].setColor(null);
            } else {
                points[x][y].setColor(listOfPoints.getTetrimonoes(i).getTetrimono().getColor());
            }
        }
    }

    public synchronized void checkLine() {
        int numberOfLines = finishedPoints.checkLine(row, col, points);
        if (numberOfLines != 0) {
            for (int i = 0; i < finishedPoints.size(); i++) {
                int x = finishedPoints.getCoords(i).getX();
                int y = finishedPoints.getCoords(i).getY();
                if (finishedPoints.changeTetrimonoes(finishedPoints, i, x + numberOfLines, y, row, col, false)
                        && finishedPoints.getMaxDeletedRow() >= x) {
                    Color previous = points[x][y].getBackground();
                    points[x][y].setColor(null);
                    points[x + numberOfLines][y].setColor(previous);
                }

            }

        }
    }

    public synchronized boolean execute(int a, int b) {
        boolean finish = false;
        ListOfPoints temp = (ListOfPoints) ListOfPoints.deepClone(listOfPoints);
        for (int i = 0; i < temp.size(); i++) {
            int x = temp.getCoords(i).getX();
            int y = temp.getCoords(i).getY();
            if (temp.changeTetrimonoes(finishedPoints, i, x + a, y + b, row, col, finish)) {
            } else {
                if (a == 1 && b == 0) {
                    finishedPoints.setFinish(listOfPoints);
                }
                finish = true;
                break;
            }
        }
        //  System.out.println("spr " + listOfPoints);
        //  System.out.println("tmp " + listOfPoints);
        // System.out.println("finish" + finishedPoints);
        if (!finish) {
            setColors(listOfPoints, true);
            setColors(temp, false);
            listOfPoints = (ListOfPoints) ListOfPoints.deepClone(temp);
        }
        return finish;
    }

    public synchronized void rotate(boolean isLeft) {
        if (started) {
            if (listOfPoints.getTetrimonoes(0).getTetrimono().equals(Tetrimono.TetrominoO)) {
                return;
            }
            boolean finish = false;
            ListOfPoints temp = (ListOfPoints) ListOfPoints.deepClone(listOfPoints);
            Coords secondHighest;
            if (rotate % 180 == 0) {
                secondHighest = temp.findMiddle(true);
            } else {
                secondHighest = temp.findMiddle(false);
            }
            int secX = secondHighest.getX();
            int secY = secondHighest.getY();
            for (int i = 0; i < temp.size(); i++) {
                int x = temp.getCoords(i).getX() - secX;
                int y = temp.getCoords(i).getY() - secY;
                boolean isTetS = listOfPoints.getTetrimonoes(0).getTetrimono().equals(Tetrimono.TetrominoS);
                boolean isTetZ = listOfPoints.getTetrimonoes(0).getTetrimono().equals(Tetrimono.TetrominoZ);
                if (isLeft && !isTetS && !isTetZ
                        || rotate % 180 != 0 && isTetS
                        || rotate % 180 == 0 && isTetZ) {
                    if (temp.changeTetrimonoes(finishedPoints, i, -y + secX, x + secY, row, col, finish)) {
                    } else {
                        finish = true;
                        break;
                    }
                } else {
                    if (temp.changeTetrimonoes(finishedPoints, i, y + secX, -x + secY, row, col, finish)) {
                    } else {
                        finish = true;
                        break;
                    }
                }
            }
            if (!finish) {
                rotate += 90;
                setColors(listOfPoints, true);
                setColors(temp, false);
                listOfPoints = (ListOfPoints) ListOfPoints.deepClone(temp);
            }
        }
    }

    public synchronized void doAction() {
        if (!continued) {
            if (!started) {
                tetrimono = Tetrimono.getRandomType();
                tetris.createPoint(points, listOfPoints, tetrimono, 0, col - 1);
                started = true;
            } else {
                tetris.createPoint(points, listOfPoints, tetrimono, 0, col - 1);
            }
            tetrimono = boxNext.addRandom();
            continued = true;
            rotate = 0;
        } else {
            if (execute(1, 0)) {
                continued = false;
            }
        }
    }

}
