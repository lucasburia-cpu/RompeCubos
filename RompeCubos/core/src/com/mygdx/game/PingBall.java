package com.mygdx.game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PingBall implements Colisionable{
	    private int x;
	    private int y;
	    private final int size;
	    private int xSpeed;
	    private int ySpeed;
	    private Color color = Color.WHITE;
	    private boolean estaQuieto;
	    
	    public PingBall(int x, int y, int size, int xSpeed, int ySpeed, boolean iniciaQuieto) {
	        this.x = x;
	        this.y = y;
	        this.size = size;
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	        estaQuieto = iniciaQuieto;
	    }
	    
	    public boolean estaQuieto() {
	    	return estaQuieto;
	    }
	    public void setEstaQuieto(boolean bb) {
	    	estaQuieto = bb;
	    }
	    
	    public boolean seFueAbajo() {
	    	return (y + size) < 0;
	    }
	    public void setXY(int x, int y) {
	    	this.x = x;
	        this.y = y;
	    }
	    
	    public int getCentroY() {
	    	return y;
	    }
	    
	    @Override
	    public int getX() {
	    	return x - size;
	    }	    
	    
	    @Override
	    public int getY() {
	    	return y - size;
	    }
	    
	    @Override
	    public int getWidth() {
	    	return size * 2;
	    }
	    
	    @Override
	    public int getHeight() {
	    	return size * 2;
	    }
	    
	    
	    public void draw(ShapeRenderer shape){
	        shape.setColor(color);
	        shape.circle(x, y, size);
	    }
	    
	    public void update() {
	    	if (estaQuieto) return;
	        x += xSpeed;
	        y += ySpeed;
	        if (x-size < 0 || x+size > Gdx.graphics.getWidth()) {
	            xSpeed = -xSpeed;
	        }
	        if (y+size > Gdx.graphics.getHeight()) {
	            ySpeed = -ySpeed;
	        }
	    }
	    
	    public void checkCollision(Paddle paddle) {
	        if(collidesWith(paddle)){
	            color = Color.GREEN;
	            ySpeed = -ySpeed;
	        }
	        else{
	            color = Color.WHITE;
	        }
	    }

	    public void checkCollision(Block block) {
	        if(collidesWith(block)){
	            ySpeed = - ySpeed;
	            block.golpeado();
	        }
	    }
	    
	    // aprovechar polimorfismo de Colisionable
	    private boolean collidesWith(Colisionable objetoColicionado) {

	    	boolean intersectaX = (objetoColicionado.getX() + objetoColicionado.getWidth() >= x-size) && (objetoColicionado.getX() <= x + size);
	        boolean intersectaY = (objetoColicionado.getY() + objetoColicionado.getHeight() >= y- size) && (objetoColicionado.getY() <= y+size);		
	    	return intersectaX && intersectaY;
	    }
	    
	}
