package final_eco;

import java.util.ArrayList;
import java.util.Collections;

import processing.core.PApplet;
import processing.core.PImage;

public class NivelUno {

	private PApplet p;

	private Personaje per;
	private Objetitos init;
	private ArrayList<Objetitos> listos;

	private int xc, yc, x1, y1, xp, yp, punticos, time;
	private boolean terminado;

	public NivelUno(PApplet p) {
		this.p = p;

		// ubicación de los elementos que se arrastran
		xc = 102;
		yc = 373;

		// posición barra de abajo
		x1 = 251;
		y1 = 606;

		// posición inicial del personaje
		xp = 310;
		yp = 373;
		per = new Personaje(p, xp, yp);

		init = new Objetitos(p, xc, yc, 0, 1);
		listos = new ArrayList<>();
		
		
		punticos = 0;
		time = p.frameCount;

	}

	public void pintar() {
		per.pintar();
		init.pintar();

		// pinto listas y elimino cuando saco los elementos de la barra
		for (int i = 0; i < listos.size(); i++) {
			listos.get(i).pintar();

			if (listos.get(i).getX() < 223 || listos.get(i).getX() > 588 || listos.get(i).getY() < 572
					|| listos.get(i).getY() > 638) {
				if (p.mousePressed == false)
					listos.remove(i);
			}
		}
	}

	public void arrastrarUno() {
		// arrastro la bolita inicial
		if (PApplet.dist(p.mouseX, p.mouseY, init.getX(), init.getY()) < 20) {
			init.setX(p.mouseX);
			init.setY(p.mouseY);
		}

		// arrastar las bolas que están en la bala de acción
		for (Objetitos i : listos) {
			if (PApplet.dist(p.mouseX, p.mouseY, i.getX(), i.getY()) < 20) {
				i.setX(p.mouseX);
				i.setY(p.mouseY);
			}
		}
	}

	/*
	 * Valido si ya está sobre un punto de la linea de acción y retorno el punto
	 * exacto donde se queda
	 */
	public int menuAbajoUno(int x, int y) {
		for (int i = 0; i < 8; i++) {
			if (PApplet.dist(x, y, 251 + (i * 44), y1) < 25) {
				return 251 + (i * 44);
			}
		}
		return 0;
	}

	public void soltarUno() {
		// Valido si ya está sobre un punto de la linea de acción y guardo el retorno
		int temp = menuAbajoUno(init.getX(), init.getY());
		if (temp != 0) {
			int posicionEnlaBarra = 1 + ((temp - 251) / 44);
			Objetitos p = new Objetitos(this.p, temp, y1, posicionEnlaBarra, 1);
			listos.add(p);

			init.setX(xc);
			init.setY(yc);
		}

		// Cambiar la posicion de la bola y que le de la nueva posicion en la barra
		for (int i = 0; i < listos.size(); i++) {
			if (listos.get(i).getX() > 223 && listos.get(i).getX() < 588 && listos.get(i).getY() > 572
					&& listos.get(i).getY() < 638) {

				if (p.mousePressed == false) {
					int t = menuAbajoUno(listos.get(i).getX(), listos.get(i).getY());
					listos.get(i).setX(t);
					listos.get(i).setY(y1);
					listos.get(i).setPos(1 + ((t - 251) / 44));
				}
			}
		}
	}

	public void zona() {
		// darle play
		if (p.mouseX >= 644 && p.mouseX <= 703 && p.mouseY >= 574 && p.mouseY <= 635) {
			Thread t = new Thread() {
				@Override
				public void run() {
					// el while se ejecuta hasta que el personaje llegue a la posicion final
					// posicion final = cantidad de bolitas en la barra de accion
					while (per.getX() < (listos.size() * 65) + 310) {
						per.setX(per.getX() + 2);

						try {
							sleep(50);

							// parar en el segundo punto
							if (per.getX() == (2 * 65) + 310) {
								sleep(600);
							}

							// parar en el primer punto
							if (per.getX() == (65) + 311) {
								sleep(600);
							}

							if (per.getX() == (3 * 65) + 311) {
								sleep(600);
								punticos = 10000 / ((p.frameCount/60) - time);
								terminado = true;

							}

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			};

			t.start();
		}
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public int getPunticos() {
		return punticos;
	}

	public void setPunticos(int punticos) {
		this.punticos = punticos;
	}

}
