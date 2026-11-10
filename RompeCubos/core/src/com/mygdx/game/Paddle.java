package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle extends ElementoJuego{
    private ControlMovimiento control;
	
	public Paddle(int x, int y, int ancho, int altura, ControlMovimiento control) {
		super(x, y, ancho, altura);
		this.control = control;
	}
	
	public void setControl(ControlMovimiento control) {
		this.control = control;
	}
	
    @Override
    public void update() {
        int x2 = control.siguienteX(getX(), getWidth());
        if (x2 > 0 && x2 + getWidth() < Gdx.graphics.getWidth()) {
            setX(x2);
        }
    }
    
    @Override
	public void draw (ShapeRenderer shape){
        shape.setColor(Color.BLUE);
        shape.rect(getX(), getY(), getWidth(), getHeight());
    }
    
    
}
