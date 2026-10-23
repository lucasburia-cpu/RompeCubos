package com.mygdx.game;

public class CreadorDeNiveles {
	private CreadorDeNiveles() {}
	
	public static Nivel SelectorNivel(int nivel) {
		int filas = 2 + nivel;
		switch (nivel % 3) {
			case 1:
				return new NivelBasico(filas);
			case 2:
				return new NivelIntercado(filas);
			default:
				return new NivelReforzado(filas);
		}
	}
}
