package Box;

import java.io.Serializable;
import java.util.Objects;

public class Coords implements Serializable, Comparable<Coords> {

    private int x, y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coords) {
            Coords that = (Coords) o;
            if (x == that.x) {
                return y == that.y;
            }
            
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 79 * Objects.hashCode(this.x) + Objects.hashCode(this.y);
    }

    @Override
    public String toString() {
        return "P( " + x + "," + y + ")";
    }

    @Override
    public int compareTo(Coords o) {
        return o.x == x ? Integer.compare(y, o.y) : Integer.compare(o.x, x);
    }
    
}
