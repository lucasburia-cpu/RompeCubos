package com.mygdx.game;

import java.util.Arrays;
import java.util.List;



public class CreadorDeNiveles {
	private static final int FILAS_BASE = 2;
	
	
    private interface FabricaNivel {
        Nivel crear(int filas);
    }

    private static final List<FabricaNivel> TIPOS = Arrays.asList(
            new FabricaNivel() {
                @Override
                public Nivel crear(int filas) { return new NivelBasico(filas); }
            },
            new FabricaNivel() {
                @Override
                public Nivel crear(int filas) { return new NivelIntercalado(filas); }
            },
            new FabricaNivel() {
                @Override
                public Nivel crear(int filas) { return new NivelReforzado(filas); }
            }
    );
	
	private CreadorDeNiveles() {
		
	}
	
    public static Nivel crearNivel(int numeroNivel) {
        int filas = FILAS_BASE + numeroNivel;
        return TIPOS.get((numeroNivel - 1) % TIPOS.size()).crear(filas);
    }

}
