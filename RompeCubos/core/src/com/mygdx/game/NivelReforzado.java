package com.mygdx.game;

public class NivelReforzado extends Nivel{
	private final int filas;
	
	public NivelReforzado(int filas) {
		this.filas = filas;
	}

	@Override
	protected int getFilas() {
		return filas;
	}

	@Override
	protected Block crearBlock(int x, int y, int width, int height, int fila) {
		int golpes = (fila == 0) ? 2 : 1;
		return Block.builder().posicion(x, y).dimension(width, height).golpes(golpes).build();
	}
	
	
}
