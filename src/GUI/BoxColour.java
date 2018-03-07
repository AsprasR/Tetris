package GUI;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

public final class BoxColour extends JLabel {
    public BoxColour(Color color) {
        setColor(color);
    }

    public void setColor(Color color) {
        if (color == null) {
            setBorder(BorderFactory.createEmptyBorder());
            setBackground(Color.BLACK);
        } else {
            setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            setBackground(color);
        }
    }

}
