package final_eco;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class Personaje {
	
	private PApplet p;
	 
	private PVector pos, vel, acel;
	private PImage der, izq, aba, up;
	private int po;

	private boolean salido;

	public Personaje(PApplet p, int x, int y) {
		this.p = p;
		pos = new PVector(x,y);
		vel = new PVector();
		acel = new PVector();
		salido = false;
		
		po=1;
		
		der = p.loadImage("../data/personajillo/perDer.png");
		izq = p.loadImage("../data/personajillo/perIzq.png");
		aba = p.loadImage("../data/personajillo/perAba.png");
		up = p.loadImage("../data/personajillo/perUp.png");
	}
	
	public void pintar() {
		switch(po) {
		case 1:
			p.imageMode(p.CENTER);
			p.image(der, pos.x, pos.y);
			break;
			
		case 3:
			p.imageMode(p.CENTER);
			p.image(izq, pos.x, pos.y-8);
			break;
			
		case 2:
			p.imageMode(p.CENTER);
			p.image(aba, pos.x-8, pos.y);
			break;
			
		case 4:
			p.imageMode(p.CENTER);
			p.image(up, pos.x, pos.y);
			break;
		}
	}
	
	public void mover() {
		switch(po) {
		case 1:
			if(pos.x>280 && pos.x<528) {
				pos.x+=1;
			}else {
				salido=true;
			}
			break;
			
		case 2:
			if(pos.y<251 || pos.y>477) {
				salido=true;
				return;
			}
			
			if(pos.x<466 && pos.y>331) {
				salido=true;
			}else {
				pos.y+=1;
			}
			break;
		}
	}
	
	public void moverN3() {
		switch(po) {
		case 1:
			if(pos.x>280 && pos.x<528) {
				pos.x+=1;
			}else {
				//salido=true;
			}
			break;
			
		case 3:
			if(pos.x>280 && pos.x<528) {
				pos.x-=1;
			}else {
				//salido=true;
			}
			break;
			
		case 2:
			if(pos.y>251 && pos.y<477) {				
				pos.y+=1;
			}else {
				//salido=true;
			}
			break;
			
		case 4:
			if(pos.y>251 && pos.y<477) {
				pos.y-=1;
			}else {
				//salido=true;
			}
			break;
		}
	}

	public int getX() {
		return (int) pos.x;
	}

	public void setX(int x) {
		this.pos.x = x;
	}

	public int getY() {
		return (int)  pos.y;
	}

	public void setY(int y) {
		this.pos.y = y;
	}

	public int getPo() {
		return po;
	}

	public void setPo(int po) {
		this.po = po;
	}

	public boolean isSalido() {
		return salido;
	}

	public void setSalido(boolean salido) {
		this.salido = salido;
	}
}
