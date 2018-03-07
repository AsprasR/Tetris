/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Box;

import Box.Tetrimonoes.Tetrimono;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Nightspire
 */
public class CoordsTetrimonoes implements Serializable, Comparable<CoordsTetrimonoes> {
    private Coords coords;
    private Tetrimonoes tetrimono;
    
    public CoordsTetrimonoes( Coords coords, Tetrimonoes tetrimono ) {
        this.coords = coords;
        this.tetrimono = tetrimono;
    }
    
    public Coords getCoords() {
        return coords;
    }
    
    public Tetrimono getTetrimono() {
        return tetrimono.getTetrimono();
    }
    
    public Tetrimonoes getTetrimonoes() {
        return tetrimono;
    }
    
    public Color getColor() {
        return tetrimono.getTetrimono().getColor();
    }
    
    public void setTetrimono( Tetrimonoes states ) {
        this.tetrimono = states;
    }
    
    public void setCoords( Coords coords ) {
        this.coords = coords;
    }
    
    @Override
    public String toString() {
        return coords + " " + tetrimono;
    }   

    @Override
    public int compareTo(CoordsTetrimonoes o) {
        return coords.compareTo(o.coords);
    }
}
