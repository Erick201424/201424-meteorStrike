package com.concurrencia.meteorstrike.models;

public class Vector {

    private int id;
    private int posX;
    private int posY;

    public Vector (int id, int posX, int posY) {
        this.id = id;
        this.posX = posX;
        this.posY = posY;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
