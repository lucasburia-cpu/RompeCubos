package com.mygdx.game;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Rompecubos extends ApplicationAdapter {
	    private OrthographicCamera camera;
		private SpriteBatch batch;	   
		private BitmapFont font;
		private ShapeRenderer shape;
		private PingBall ball;
		private Paddle pad;
		private ArrayList<Block> blocks = new ArrayList<>();
		private EstadoJuego estado;
		private final ControlMovimiento teclado = new ControlTeclado();
		private final ControlMovimiento mouse = new ControlMouse();
		private boolean usaMause = false;
		

		@Override
		public void create () {	
			camera = new OrthographicCamera();
			camera.setToOrtho(false, 800, 480);
	    	batch = new SpriteBatch();
		    font = new BitmapFont();
		    font.getData().setScale(3, 2);
		    shape = new ShapeRenderer();
		    
		    estado = EstadoJuego.getInstance();
		    estado.reset();
		    
		    pad = new Paddle(Gdx.graphics.getWidth()/2-50,40,100,10, teclado);
		    
		    cargarNivel();
		    
		    ball = nuevaBola();
		}
		public void cargarNivel() {
			Nivel nivel = CreadorDeNiveles.SelectorNivel(estado.getNivel());
			blocks = (ArrayList<Block>) nivel.generate(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
		
		public PingBall nuevaBola() {
			return new PingBall(pad.getX() + pad.getWidth() / 2 - 5, pad.getY() + pad.getHeight() + 11, 10, 5, 7, true);
		}
		
		
		public void dibujaTextos() {
			//actualizar matrices de la cámara
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
					usaMause = !usaMause;
					pad.setControl(usaMause ? mouse : teclado);
				}
				
		        shape.begin(ShapeRenderer.ShapeType.Filled);
		        pad.update();
		        pad.draw(shape);
		        
		        
		        // monitorear inicio del juego
		        if (ball.estaQuieto()) {
		        	ball.setXY(pad.getX()+pad.getWidth()/2-5, pad.getY()+pad.getHeight()+11);
		        	if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) ball.setEstaQuieto(false);
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
		        		ball = nuevaBola();
		        	}else {
		        		ball = nuevaBola();
		        	}
		        }
		        
		        /*
		        if (estado.isGameOver()) {
		        	estado.reiniciarPartida();
		        	cargarNivel();
		        	ball = nuevaBola();
		        }
		        */
		        
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
