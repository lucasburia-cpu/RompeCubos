package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import java.util.Random;

public class BlockBuilder {
	private int x = 0;
	private int y = 0;
	private int width = 70;
	private int height = 26;
	private int golpes = 1;
	private Color color;
	
	public BlockBuilder posicion(int x, int y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public BlockBuilder golpes(int golpes) {
		this.golpes = golpes;
		return this;
	}
	
	public BlockBuilder color(Color color) {
		this.color = color;
		return this;
	}
	
	public Block build() {
		Color colorF = (color != null) ? color : randomColor();
		return new Block(x,y,width,height, colorF, golpes);
	}
	
	private Color randomColor() {
		Random r = new Random(x + y);
		return new Color(0.1f + r.nextFloat() * 0.5f, r.nextFloat(), r.nextFloat(),1f);
	}
	
}
