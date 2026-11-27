import java.awt.Graphics;
import javax.swing.JPanel;

public class HangmanPanel extends JPanel {
    int wrong = 0;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawLine(30, 250, 150, 250);
        g.drawLine(60, 250, 60, 20);
        g.drawLine(60, 20, 160, 20);
        g.drawLine(160, 20, 160, 55);

        if (wrong >= 1) {
            g.drawOval(135, 55, 50, 50);
        }
        if (wrong >= 2) {
            g.drawLine(160, 105, 160, 175);
        }
        if (wrong >= 3) {
            g.drawLine(160, 125, 130, 155);
            g.drawLine(160, 125, 190, 155);
        }
        if (wrong >= 4) {
            g.drawLine(160, 175, 135, 220);
            g.drawLine(160, 175, 185, 220);
        }
        if (wrong >= 5) {
            g.drawLine(146, 72, 154, 80);
            g.drawLine(154, 72, 146, 80);
            g.drawLine(166, 72, 174, 80);
            g.drawLine(174, 72, 166, 80);
        }
    }
}
