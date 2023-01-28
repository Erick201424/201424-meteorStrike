package com.concurrencia.meteorstrike.models;

import java.util.Observable;
import java.util.Random;

public class Meteoro extends Observable implements Runnable {

    private int velocidad = 0;

    private Vector pos;
    private boolean status;
    private Random random;

    public Meteoro(int velocidad) {
        this.velocidad = velocidad;
        status = true;
        random = new Random(System.currentTimeMillis());
    }

    public void setPosicion(Vector pos) {
        this.pos = pos;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
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
            pos.setPosX(pos.getPosX()-35 + velocidad);
            setChanged();
            notifyObservers(pos);
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /*@Override
    public void run() {
        while (status) {
            pos.setPosX(pos.getPosX() + random.nextInt(50) + 10);
            //Notificar el cambio
            this.setChanged();
            this.notifyObservers(pos);
        }
        //Dormir hilo
        try {
            Thread.sleep(250L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }*/
}
