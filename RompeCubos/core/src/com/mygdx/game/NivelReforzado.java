package com.mygdx.game;

public class NivelReforzado extends Nivel{
	public NivelReforzado(int filas) {
		super(filas);
	}

	@Override
	protected Block crearBlock(int x, int y, int width, int height, int fila) {
		int golpes = (fila == 0) ? 2 : 1; // else if, si filas = 0 entonces golpes = 2 sino golpes = 1
		return Block.builder().posicion(x, y).dimension(width, height).golpes(golpes).build();
	}
	
	
}
