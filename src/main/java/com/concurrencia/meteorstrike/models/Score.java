package com.concurrencia.meteorstrike.models;

import java.util.Observable;

public class Score  extends Observable implements Runnable {

    private int score;
    private boolean status;

    public Score() {
        this.score = score;
        status = true;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public void run() {

        while (status) {
            setChanged();
            notifyObservers(score);
            score += 10;
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
