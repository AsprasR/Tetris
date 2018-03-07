package Box;

import java.awt.Color;
import java.io.Serializable;
import java.util.Random;

public class Tetrimonoes implements Serializable {

    public enum Tetrimono {
        TetrominoI(Color.WHITE),
        TetrominoJ(Color.BLUE),
        TetrominoL(Color.GREEN),
        TetrominoO(Color.WHITE),
        TetrominoS(Color.BLUE),
        TetrominoT(Color.WHITE),
        TetrominoZ(Color.GREEN);

        private Color color;

        Tetrimono(Color color) {
            this.color = color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public static Tetrimono getRandomType() {
            Random r = new Random();
            return values()[r.nextInt(values().length)];
        }

    }

    private Tetrimono tetrimono;

    public Tetrimonoes(Tetrimono tetrimono) {
        this.tetrimono = tetrimono;
    }

    public Tetrimono getTetrimono() {
        return tetrimono;
    }

    public void setTetrimono(Tetrimono tetrimono) {
        this.tetrimono = tetrimono;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Tetrimonoes)) {
            return false;
        }
        Tetrimonoes that = (Tetrimonoes) obj;
        return tetrimono.equals(that.tetrimono);
    }

    @Override
    public int hashCode() {
        return tetrimono.hashCode();
    }

    @Override
    public String toString() {
        return tetrimono.toString();
    }
}
