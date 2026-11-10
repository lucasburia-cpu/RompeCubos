package com.mygdx.game;

public class NivelBasico extends Nivel{
	
	public NivelBasico(int filas) {
		super(filas);
	}

	@Override
	protected Block crearBlock(int x, int y, int width, int height, int fila) {
		return Block.builder().posicion(x, y).dimension(width, height).build();
	}
	
	
}
