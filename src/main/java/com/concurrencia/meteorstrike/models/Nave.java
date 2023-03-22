package com.concurrencia.meteorstrike.models;

import java.util.Observable;

public class Nave extends Observable implements Runnable {

    private Vector pos;
    private boolean status;

    public Nave() {
        status = true;
    }

    public void setPosicion(Vector pos) {
        this.pos = pos;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public void run() {
        while (status) {
            pos.setPosX(pos.getPosX() + 10);
            setChanged();
            notifyObservers(pos);

            try {
                Thread.sleep(150L); 
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
