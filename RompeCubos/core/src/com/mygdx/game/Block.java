package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Block extends ElementoJuego{
    private final Color cc;
    private int golpesRestantes;
    private boolean destruido;
    
    public Block(int x, int y, int width, int height, Color color, int golpes) {
    	super(x,y,width,height);
        this.cc = color;
        this.golpesRestantes = golpes;
        this.destruido = false;
    }
    
    
    public static BlockBuilder builder() {
    	return new BlockBuilder();
    }
    
    public boolean EstaDestruido() {
    	return destruido; 
    }
    
    public int getGolpesRestantes() {
    	return golpesRestantes;
    }
    
    @Override
    public void draw(ShapeRenderer shape){
    	shape.setColor(golpesRestantes > 1 ? Color.GRAY : cc);
        shape.rect(getX(), getY(), getWidth(), getHeight());
    }

    // resta los golpes recibidos y si no quedan golpes restantes se destruye el bloque
	@Override
	public void Coaliciona() {
		golpesRestantes--;
		if(golpesRestantes <= 0) {
			destruido = true;
		}
		
	}
}
