package Simulation;

import GUI.BoxGUI;

public class Simulation extends Thread {

    private int seconds = 150;
    private final Object object;
    private boolean pause = false;
    private final BoxGUI box;

    public Simulation(BoxGUI box) {
        this.object = this;
        this.box = box;
        simulate();
    }

    private void simulate() {
        start();
        try {
            pauseThread();
        } catch (InterruptedException e) {
        }
    }

    public void setSeconds(int s) {
        seconds = s;
    }

    public void pauseThread() throws InterruptedException {
        pause = true;
    }

    private void checkForPaused() {
        synchronized (object) {
            while (pause) {
                try {
                    object.wait();
                } catch (InterruptedException e) {

                }
            }
        }
    }

    private void threadSleep() throws InterruptedException {
        Thread.sleep(seconds);
    }

    public void resumeThread() {
        synchronized (object) {
            pause = false;
            object.notifyAll();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                checkForPaused();
                threadSleep();
                box.doAction();
                box.checkLine();
            } catch (InterruptedException e) {
                break;
            }
        }

    }
}
