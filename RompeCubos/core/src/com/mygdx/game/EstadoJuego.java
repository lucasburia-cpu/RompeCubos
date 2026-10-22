package com.mygdx.game;
/**
	 * GM-6 (Singleton): estado global de la partida (vidas, puntaje y nivel).
	 * Debe existir una única instancia compartida por toda la aplicación.
	 */
public class EstadoJuego {
	public static final int VIDAS_INICIALES = 3;
	private static EstadoJuego instancia;
	private int vidas;
	private int puntaje;
	private int nivel;

	private EstadoJuego() {
		reset();
	}

	public static synchronized EstadoJuego getInstance() {
		if (instancia == null) {
			instancia = new EstadoJuego();
	    }
	    return instancia;
	}

	// se deja estado de juego en el inicio primer nivel, 3 vidas y 0 puntos
	public void reset() {
		vidas = VIDAS_INICIALES;
	    puntaje = 0;
	    nivel = 1;
	}

	/** Reinicio tras perder todas las vidas: vuelve al nivel 1 conservando el puntaje. */
	public void reiniciarPartida() {
		vidas = VIDAS_INICIALES;
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
