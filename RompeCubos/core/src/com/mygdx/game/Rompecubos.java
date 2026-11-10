package com.mygdx.game;

import java.util.ArrayList;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Rompecubos extends ApplicationAdapter {
	    private OrthographicCamera camera;
		private SpriteBatch batch;	   
		private BitmapFont font;
		private ShapeRenderer shape;
		private PingBall ball;
		private Paddle pad;
		private ArrayList<Block> blocks = new ArrayList<>();
		private EstadoJuego estado;
		private final ControlMovimiento[] controles = { new ControlTeclado(), new ControlMouse() };
		private int controlActual = 0;
		

		@Override
		public void create () {	
			camera = new OrthographicCamera();
			camera.setToOrtho(false, 800, 480);
	    	batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    shape = new ShapeRenderer();
		    
		    estado = EstadoJuego.getInstance();
		    estado.reiniciarPartida();
		    
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10, controles[controlActual]);
		    
		    cargarNivel();
		    
		    ball = nuevaBola();
		}
		public void cargarNivel() {
			Nivel nivel = CreadorDeNiveles.crearNivel(estado.getNivel());
			blocks = (ArrayList<Block>) nivel.generate(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		
		public PingBall nuevaBola() {
			 PingBall bola = new PingBall(0, 0, 10, 5, 7, true);
			 bola.reposarSobre(pad);
			 return bola;
		}
		
		private void cambiarControl() {
			controlActual = (controlActual + 1) % controles.length;
			pad.setControl(controles[controlActual]);
		}
		
		public void dibujaTextos() {
			//actualizar matrices de la camara
			camera.update();
			//actualizar 
			batch.setProjectionMatrix(camera.combined);
			batch.begin();
			//dibujar textos
			font.draw(batch, "Puntos: " + estado.getPuntaje(), 10, 25);
			font.draw(batch, "Vidas : " + estado.getVidas(), Gdx.graphics.getWidth()- 150, 25);
			batch.end();
		}	
			
		@Override
		public void render () {
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); 		
				
			if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
				cambiarControl();
			}
				
		    shape.begin(ShapeRenderer.ShapeType.Filled);
		    pad.update();
		    pad.draw(shape);
		        
		        
		    // monitorear inicio del juego
		    if (ball.estaQuieto()) {
		        ball.reposarSobre(pad);
		        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
		        	ball.lanzar();
		        }
		    }else {
		        ball.update();
		    }
		        
		        
		    //verificar si se fue la bola x abajo
		    if (ball.seFueAbajo()) {
		        estado.perderVida();
		        	
		        // verificar game over
		        if(estado.isGameOver()) {
		        	estado.reiniciarPartida();
		        	cargarNivel();
		        }
		        ball = nuevaBola();
		    }
		        
		    // verificar si el nivel se termino
		    if (blocks.isEmpty()) {
		        estado.subirNivel();
		        cargarNivel();
		        ball = nuevaBola();
		    }    	
		        
		   //dibujar bloques
		    for (Block b : blocks) {        	
		    	b.draw(shape);
		        ball.checkCollision(b);
		    }
		        
		    // actualizar estado de los bloques 
		    for (int i = 0; i < blocks.size(); i++) {
		    	if (blocks.get(i).EstaDestruido()) {
		    		estado.sumarPunto();
		            blocks.remove(i);
		            i--; //para no saltarse 1 tras eliminar del arraylist
		        }
		    }
		        
		    ball.checkCollision(pad);
		    ball.draw(shape);
		        
		    shape.end();
		    dibujaTextos();
		}
			
		@Override
		public void dispose () {
			batch.dispose();
			font.dispose();
			shape.dispose();
		}
}
