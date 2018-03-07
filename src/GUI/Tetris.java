/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Box.ListOfPoints;
import Box.Tetrimonoes.Tetrimono;

/**
 *
 * @author Nightspire
 */
public class Tetris {

    private Tetrimono tetrimono;
    private ListOfPoints listOfPoints;
    private BoxColour[][] points;

    private void addPoint(int i, int j) {
        points[i][j].setColor(tetrimono.getColor());
        listOfPoints.setCoordsTetrimonoes(i, j, tetrimono);
    }

    public void createPoint(BoxColour[][] points, ListOfPoints listOfPoints, Tetrimono tetrimono, int p, int k) {
        this.tetrimono = tetrimono;
        this.listOfPoints = listOfPoints;
        this.points = points;
        int mid = (k - 1) / 2;
        switch (tetrimono) {
            case TetrominoI:
                for (int j = mid - 1; j < mid + 3; j++) {
                    addPoint(p, j);
                }
                break;
            case TetrominoJ:
                for (int j = mid; j < mid + 3; j++) {
                    addPoint(p, j);
                }
                addPoint(p + 1, mid + 2);
                break;
            case TetrominoL:
                for (int j = mid; j < mid + 3; j++) {
                    addPoint(p, j);
                }
                addPoint(p + 1, mid);
                break;
            case TetrominoO:
                for (int i = p; i < 2 + p; i++) {
                    for (int j = mid; j < mid + 2; j++) {
                        addPoint(i, j);
                    }
                }
                break;
            case TetrominoS:
                for (int i = p; i < 2 + p; i++) {
                    for (int j = mid; j < mid + 3; j++) {
                        if (i == 0 + p && j == mid || i == 1 + p && j == mid + 2) {
                        } else {
                            addPoint(i, j);
                        }
                    }
                }
                break;
            case TetrominoT:
                for (int j = mid; j < mid + 3; j++) {
                    addPoint(p, j);
                }
                addPoint(p + 1, mid + 1);
                break;
            case TetrominoZ:
                for (int i = 0 + p; i < 2 + p; i++) {
                    for (int j = mid; j < mid + 3; j++) {
                        if (i == 1 + p && j == mid || i == 0 + p && j == mid + 2) {
                        } else {
                            addPoint(i, j);
                        }
                    }
                }
                break;
        }

    }

}
