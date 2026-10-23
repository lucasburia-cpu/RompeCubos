package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class ControlMouse implements ControlMovimiento{
	
	@Override
	public int siguienteX(int actualX, int width) {
		return Gdx.input.getX() - width / 2;
	}
}
