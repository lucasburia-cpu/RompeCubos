package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

public abstract class Nivel {
	private static final int BLOCK_WIDTH = 70;
	private static final int BLOCK_HEIGHT = 26;
	private static final int SEPARACION = 10;
	private static final int MARGEN_IZQUIERDO = 5;
	
	
	public final List<Block> generate(int anchoPantalla, int alturaPantalla){
		List<Block> blocks = new ArrayList<>();
		int filas = getFilas();
		int y = alturaPantalla;
		for(int fila = 0; fila < filas;fila++) {
			y -= BLOCK_HEIGHT + SEPARACION;
			int columna = 0;
			for(int x = MARGEN_IZQUIERDO; x < anchoPantalla; x += BLOCK_WIDTH + SEPARACION) {
				if(shouldPlaceBlock(fila,columna)) {
					blocks.add(crearBlock(x,y,BLOCK_WIDTH,BLOCK_HEIGHT,fila));
				}
				columna++;
			}
		}
		return blocks;
	}
	
	protected abstract int getFilas();
	protected abstract Block crearBlock(int x, int y, int width, int height, int fila);
	protected boolean shouldPlaceBlock(int fila, int columna) {
		return true;
	}
	
}
