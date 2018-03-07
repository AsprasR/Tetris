package GUI;

import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public final class Score extends JPanel {

    private final JLabel score, result, bestScore, bestResult;
    private int points, bestPoints;
    private final int size, font;
    private final String type;

    public Score() {
        size = 24;
        font = Font.PLAIN;
        type = "Serif";
        score = new JLabel("Score:", SwingConstants.LEFT);
        result = new JLabel("0");
        bestScore = new JLabel("Best Score:", SwingConstants.RIGHT);
        bestResult = new JLabel("0", SwingConstants.LEFT);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setFont(new Font(type, font, size));
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setFont(new Font(type, font, size));
        bestScore.setHorizontalAlignment(SwingConstants.CENTER);
        bestScore.setFont(new Font(type, font, size));
        bestResult.setHorizontalAlignment(SwingConstants.CENTER);
        bestResult.setFont(new Font(type, font, size));
        points = 0;
        bestPoints = 0;
        setLayout(new GridLayout(4, 0));
        add(score);
        add(result);
        add(bestScore);
        add(bestResult);
    }

    public void setPoints() {
        points += 3;
        result.setText(Integer.toString(points));
        if (points > bestPoints) {
            bestResult.setText(Integer.toString(points));
            bestPoints = points;
        }
    }

    public void resetPoints() {
        points = 0;
        result.setText("0");
    }

}
