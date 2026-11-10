package com.mygdx.game;
/**
	 * GM-6 (Singleton): estado global de la partida (vidas, puntaje y nivel).
	 * Debe existir una unica instancia compartida por toda las partidas.
	 */
public class EstadoJuego {
	public static final int VIDAS_INICIALES = 3;
	private static EstadoJuego instancia;
	private int vidas;
	private int puntaje;
	private int nivel;

	private EstadoJuego() {
		reiniciarPartida();
	}

	public static synchronized EstadoJuego getInstance() {
		if (instancia == null) {
			instancia = new EstadoJuego();
	    }
	    return instancia;
	}


	/** Reinicio tras perder todas las vidas: vidas = 3, puntaje = 0 y nivel = 1. */
	public void reiniciarPartida() {
		vidas = VIDAS_INICIALES;
		puntaje = 0;
	    nivel = 1;
	}

	public void perderVida() { vidas--; }
	public void sumarPunto() { puntaje++; }
	public void subirNivel() { nivel++; }
	public boolean isGameOver() { return vidas <= 0; }
	public int getVidas() { return vidas; }
	public int getPuntaje() { return puntaje; }
	public int getNivel() { return nivel; }
}
