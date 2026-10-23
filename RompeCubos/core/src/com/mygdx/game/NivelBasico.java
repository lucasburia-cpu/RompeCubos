package com.mygdx.game;

public class NivelBasico extends Nivel{
	private final int filas;
	
	public NivelBasico(int filas) {
		this.filas = filas;
	}

	@Override
	protected int getFilas() {
		return filas;
	}

	@Override
	protected Block crearBlock(int x, int y, int width, int height, int fila) {
		return Block.builder().posicion(x, y).dimension(width, height).build();
	}
	
	
}
