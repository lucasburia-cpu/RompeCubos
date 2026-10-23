package com.mygdx.game;

public class NivelIntercado extends Nivel{
	private final int filas;
	
	public NivelIntercado(int filas) {
		this.filas = filas;
	}

	@Override
	protected int getFilas() {
		return filas;
	}
	
	@Override
	protected boolean shouldPlaceBlock(int fila, int columna) {
		return (fila + columna) % 2 == 0;
	}
	

	@Override
	protected Block crearBlock(int x, int y, int width, int height, int fila) {
		return Block.builder().posicion(x, y).dimension(width, height).build();
	}
	
	
}
