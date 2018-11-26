package final_eco;

import processing.core.PApplet;
import processing.core.PImage;

public class Objetitos implements Comparable<Objetitos>{

	private PApplet p;

	private int forma, x, y, pos; 
	private PImage cir, tri, rec, five, seis, sfor;

	public Objetitos(PApplet p, int x, int y,int pos, int forma) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.pos=pos;
		this.forma = forma;
		
		forma = 0;
		
		cir = p.loadImage("../data/niveles/cir.png");
		tri = p.loadImage("../data/niveles/tri.png");
		rec = p.loadImage("../data/niveles/rec.png");
		five = p.loadImage("../data/niveles/five.png");
		seis = p.loadImage("../data/niveles/seis.png");
		sfor = p.loadImage("../data/niveles/for.png");
	}

	public void pintar() {
		switch(forma) {
		case 1:
			p.image(cir, x, y);
			break;
			
		case 2:
			p.image(tri, x, y);
			break;
			
		case 3:
			p.image(rec, x, y);
			break;
			
		case 4:
			p.image(five, x, y);
			break;
			
		case 5:
			p.image(seis, x, y);
			break;
			
		case 6:
			p.image(sfor, x+(93), y);
			break;
		}	
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	@Override
	public int compareTo(Objetitos a) {
		return this.getPos() - a.getPos();
	}

	public int getForma() {
		return forma;
	}

	public void setForma(int forma) {
		this.forma = forma;
	}
	
	
	
}
