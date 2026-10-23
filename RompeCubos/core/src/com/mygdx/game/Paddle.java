package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.Input;
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
        int x2 = getX(); //= Gdx.input.getX();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) x2 = getX()-15;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) x2= getX()+15; 
       // y = Gdx.graphics.getHeight() - Gdx.input.getY(); 
        if (x2 > 0 && x2+ getWidth() < Gdx.graphics.getWidth()) {
            setX(x2);
        }
        shape.rect(getX(), getY(), getWidth(), getHeight());
    }
    
    
}
