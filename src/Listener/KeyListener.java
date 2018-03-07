package Listener;

import GUI.BoxGUI;
import Simulation.Simulation;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

    private final Simulation simulation;
    private final BoxGUI map;
    private boolean started = false;

    public KeyListener(BoxGUI map, Simulation simulation) {
        this.map = map;
        this.simulation = simulation;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        simulation.setSeconds(150);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                simulation.setSeconds(50);
                break;
            case KeyEvent.VK_SPACE:
                if (!started) {
                    started = true;
                    simulation.resumeThread();
                } else {
                    started = false;
                    try {
                        simulation.pauseThread();
                    } catch (InterruptedException ex) {
                    }
                }
                break;
            case KeyEvent.VK_LEFT:
                map.execute(0, -1);
                break;
            case KeyEvent.VK_RIGHT:
                map.execute(0, 1);
                break;
            case KeyEvent.VK_Z:
                map.rotate(true);
                break;
            case KeyEvent.VK_X:
                map.rotate(false);
                break;
            default:
                break;
        }
    }

}
