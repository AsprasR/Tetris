/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Box;

import Box.Tetrimonoes.Tetrimono;
import GUI.BoxColour;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Nightspire
 */
public class ListOfPoints implements Serializable {

    private final ArrayList<CoordsTetrimonoes> points;
    private int maxDeletedRow;

    public ListOfPoints() {
        points = new ArrayList<>();
    }

    public ListOfPoints(ListOfPoints listOfPoints) {
        this.points = listOfPoints.points;
    }

    public ArrayList<CoordsTetrimonoes> getCoordsTetrimonoes() {
        return points;
    }

    public void setCoordsTetrimonoes(int x, int y, Tetrimono tetrimono) {
        points.add(new CoordsTetrimonoes(new Coords(x, y), new Tetrimonoes(tetrimono)));
    }
    
    public int getMaxDeletedRow() {
        return maxDeletedRow;
    }

    public Tetrimonoes getTetrimonoes(int i) {
        return points.get(i).getTetrimonoes();
    }

    public void setTetrimonoes(int i, Tetrimono tetrimono) {
        points.get(i).setTetrimono(new Tetrimonoes(tetrimono));
    }

    public void setCoords(int i, Coords newCoords) {
        points.get(i).setCoords(newCoords);
    }

    public Coords getCoords(int i) {
        return points.get(i).getCoords();
    }

    public int checkLine(int row, int col, BoxColour[][] pointer) {
        sort();
        int numberOfLines = 0;
        int[] lines = new int[row];
        points.forEach((point) -> {
            lines[point.getCoords().getX()]++;
        });
        for (int i = 0; i < row; i++) {
            if (lines[i] == col) {
                removeCoords(i, pointer);
                numberOfLines++;
                maxDeletedRow = i;
            }
        }
        return numberOfLines;
    }
    
    public void sort() {
        Collections.sort(points, CoordsTetrimonoes::compareTo);
    }

    public void removeCoords(int i, BoxColour[][] pointer) {
        Iterator<CoordsTetrimonoes> it = points.iterator();
        while (it.hasNext()) {
            CoordsTetrimonoes point = it.next();
            if (point.getCoords().getX() == i) {
                it.remove();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
                pointer[point.getCoords().getX()][point.getCoords().getY()].setColor(null);
            }
        }
    }

    public Coords findMiddle(boolean isY) {
        Coords max = new Coords(0, 0);
        Coords sdMax = new Coords(0, 0);
        int valY, valX;
        for (int i = 0; i < points.size(); i++) {
            valY = points.get(i).getCoords().getY();
            valX = points.get(i).getCoords().getX();
            if (isY) {
                if (valY > max.getY()) {
                    sdMax.setCoords(max.getX(), max.getY());
                    max.setCoords(valX, valY);
                } else if (valY > sdMax.getY() && valY != max.getY()) {
                    sdMax.setCoords(valX, valY);
                }
            } else {
                if (valX > max.getX()) {
                    sdMax.setCoords(max.getX(), max.getY());
                    max.setCoords(valX, valY);
                } else if (valX > sdMax.getX() && valX != max.getX()) {
                    sdMax.setCoords(valX, valY);
                }
            }
        }
        return sdMax;
    }

    public boolean contains(Coords coords) {
        for (int i = 0; i < points.size(); i++) {
            if (coords.equals(points.get(i).getCoords())) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return points.size();
    }

    public void removeAll(ListOfPoints listOfPoints) {
        listOfPoints.getCoordsTetrimonoes().removeAll(points);
    }

    public void setFinish(ListOfPoints listOfPoints) {
        points.addAll(listOfPoints.getCoordsTetrimonoes());
        removeAll(listOfPoints);
    }

    public boolean changeTetrimonoes(ListOfPoints finishedPoints, int i, int newX, int newY, int row, int col, boolean finish) {
        if (newX < 0 || newY < 0 || newY >= col || newX >= row
                || finish || finishedPoints.contains(new Coords(newX, newY))) {
            return false;
        } else {
            setCoords(i, new Coords(newX, newY));
            return true;
        }
    }

    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < points.size(); i++) {
            word.append(points.get(i).getCoords()).append(" ");
            word.append(points.get(i).getTetrimonoes()).append(" ");
        }
        return word.toString();
    }
}
