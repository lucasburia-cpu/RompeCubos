package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class ElementoJuego implements Colisionable {
    private int x;
    private int y;
    private final int width;
    private final int height;

    protected ElementoJuego(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getX() { 
    	return x; 
    }

    @Override
    public int getY() {
    	return y;
    }

    @Override
    public int getWidth() {
    	return width; 
    }

    @Override
    public int getHeight() {
    	return height;
    }

    protected void setX(int x) {
    	this.x = x; 
    }

    protected void setY(int y) { this.y = y; }

    // cada objeto implementa la forma en que se dibuja
    public abstract void draw(ShapeRenderer shape);

    /** Gancho: por defecto un objeto no cambia con el tiempo. */
    public void update() { }
}
