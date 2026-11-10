package com.mygdx.game;

public class NivelIntercalado extends Nivel{
	
	public NivelIntercalado(int filas) {
		super(filas);
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
