package final_eco;

import final_eco.Comunicacion.Mensaje;
import processing.core.PApplet;
import processing.core.PImage;

public class Juego {

	private PApplet p;
	private NivelUno uno;
	private NivelDos dos;
	private NivelTres tres;
	private Mensaje observer;

	private String posiciondeRegistro;

	private int id, pantalla, puntajeFinal;
	private PImage juego, nivel0, nivel1, nivel2, nivel3, nivel4, nivel5;
	private PImage con_est, con_prof, ini_est, ini_prof, ini2_est, ini2_prof;

	public Juego(PApplet p) {
		this.p = p;

		cargarImagenes();
		inicializarVariables();
	}

	public void pintar() {
		p.imageMode(p.CORNER);
		p.image(nivel0, 0, 0);

		pantallas();
	}

	public void pantallas() {

		switch (pantalla) {
		case 0:

			break;

		case 1:
			p.imageMode(p.CORNER);
			p.image(juego, 0, 0);
			p.image(nivel1, 0, 0);

			uno.pintar();
			if (uno.isTerminado()) {
				observer.enviarInfo("puntos:" + uno.getPunticos());
				System.out.println("enviamos puntos:" + uno.getPunticos());
				pantalla++;
				System.out.println("termino nivel uno");
			}
			break;

		case 2:
			p.imageMode(p.CORNER);
			p.image(nivel2, 0, 0);

			dos.pintar();
			if (dos.isTerminado()) {
				observer.enviarInfo("puntos:" + dos.getPunticos());
				System.out.println("enviamos puntos:" + dos.getPunticos());
				pantalla++;
				System.out.println("termino nivel dos");
			}
			break;

		case 3:
			p.imageMode(p.CORNER);
			p.image(nivel4, 0, 0);

			tres.pintar();
			if (tres.isTerminado()) {
				observer.enviarInfo("puntos:" + tres.getPunticos());
				System.out.println("enviamos puntos:" + tres.getPunticos());
				pantalla++;
				System.out.println("termino nivel tres");
			}
			break;

		case 4:
			// pintar puntaje final
			p.background(0, 255, 100);
			p.fill(255);
			p.text(puntajeFinal, p.width / 2, p.height / 2);
			break;

		case 5:
			p.imageMode(p.CORNER);
			p.image(nivel5, 0, 0);
			break;

		case 6:
			p.imageMode(p.CORNER);
			p.background(0, 255, 100);
			break;
		}
		p.fill(0, 0, 0);
		p.textSize(500);
		p.text(posiciondeRegistro, p.width / 2, p.height / 2);
	}

	public void arrastrar() {

		switch (pantalla) {
		case 1:
			uno.arrastrarUno();
			break;

		case 2:
			dos.arrastrarDos();
			break;

		case 3:
			tres.arrastrarTres();
			break;
		}
	}

	public void soltar() {

		switch (pantalla) {
		case 1:
			uno.soltarUno();
			break;

		case 2:
			dos.soltarDos();
			break;

		case 3:
			tres.soltarTres();
			break;
		}
	}

	public void zonaSensible() {

		switch (pantalla) {
		case 1:
			uno.zona();
			break;

		case 2:
			dos.zona();
			break;

		case 3:
			tres.zona();
			break;

		case 4:
			break;

		case 5:
			break;
		}
	}

	public void inicializarVariables() {
		puntajeFinal = 0;
		posiciondeRegistro = "";
		pantalla = 1;
		id = 1;
		uno = new NivelUno(p);
		dos = new NivelDos(p);
		tres = new NivelTres(p);
	}

	public void cargarImagenes() {
		juego = p.loadImage("../data/juego/juego.png");
		
		
		nivel0 = p.loadImage("../data/juego/nivel0.png");
		nivel1 = p.loadImage("../data/juego/nivel7.png");
		nivel2 = p.loadImage("../data/juego/nivel8.png");
		nivel3 = p.loadImage("../data/juego/nivel6.png");
		nivel4 = p.loadImage("../data/juego/nivel4.png");
		nivel5 = p.loadImage("../data/juego/nivel5.png");

		con_est = p.loadImage("../data/juego/con_est.png");
		con_prof = p.loadImage("../data/juego/con_prof.png");
		ini_est = p.loadImage("../data/juego/ini_est.png");
		ini_prof = p.loadImage("../data/juego/ini_pro.png");
		ini2_est = p.loadImage("../data/juego/ini2_est.png");
		ini2_prof = p.loadImage("../data/juego/ini2_prof.png");
	}

	public interface Mensaje {

		public void enviarInfo(String mensaje);
	}

	public void setObserver(Mensaje mensajito) {

		this.observer = mensajito;
	}

	public String getPosiciondeRegistro() {
		return posiciondeRegistro;
	}

	public void setPosiciondeRegistro(String posiciondeRegistro) {
		this.posiciondeRegistro = posiciondeRegistro;
	}

	public int getPuntajeFinal() {
		return puntajeFinal;
	}

	public void setPuntajeFinal(int puntajeFinal) {
		this.puntajeFinal = puntajeFinal;
		pantalla = 4;
	}

}
