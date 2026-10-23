package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class ControlTeclado {
	private static final int PASO = 15;
	
	
	public int siguienteX(int actualX, int width) {
		int x = actualX;
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			x = actualX - PASO;
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			x = actualX + PASO;
		}
		return x;
	}
}
