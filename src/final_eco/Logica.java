package final_eco;

import processing.core.PApplet;
import processing.core.PImage;

///////////ESTUDIANTE
public class Logica implements Comunicacion.Mensaje, Juego.Mensaje {

	private PApplet p;
	private Comunicacion comunicacion;
	private Juego game;
	private Login login;
	private String posiciondeRegistro;

	private PImage pantalla_1, pantalla_2, pantalla_3, pantalla_4, pantalla_5;

	private int pantalla;

	public Logica(PApplet p) {
		this.p = p;
		comunicacion = new Comunicacion();
		comunicacion.setObserver(this);
		comunicacion.start();
		
		login = new Login(p);

		game = new Juego(p);
		game.setObserver(this);

		cargarImagenes();
		inicializarVariables();
		posiciondeRegistro = "";
	}

	// Interfaz gráfica del estudiante
	public void pintar() {

		pantallas();

	}

	// Cambio de pantallas
	public void pantallas() {

		switch (pantalla) {
		case 0:
			login.pintar();
			if(login.isTerminado()==true) {
				posiciondeRegistro = login.getPosiciondeReg();
				System.out.println("Logica recibe pos reg"+posiciondeRegistro);
				comunicacion.enviar("posiciondeRegistro:"+posiciondeRegistro);
				game.setPosiciondeRegistro(posiciondeRegistro);
				pantalla = 1;
			}
			break;

		case 1:
			p.background(255, 100, 0);
			p.fill(255);
			p.text("esperando ando", p.width/2, p.height/2);
			break;

		case 2:
			p.background(255, 150, 0);
			break;

		case 3:
			p.background(255, 200, 0);
			break;

		case 4:
			p.background(255, 250, 0);
			break;

		case 5:
			p.background(255);
			game.pintar();
			break;
		
		// pantalla de espera
		case 6:
			p.background(0, 0, 180);
			break;
		}
	}

	// Zonas sensibles
	public void zonaSensible() {

		switch (pantalla) {
		case 0:
			login.enviar();
//			
//			if (p.mouseX >= 0 && p.mouseX <= 200 && p.mouseY >= 0 && p.mouseY <= 200) {
//				pantalla = 1;
//			}
			break;

		case 1:
			if (p.mouseX >= 0 && p.mouseX <= 200 && p.mouseY >= 0 && p.mouseY <= 200) {
				pantalla = 2;
			}
			break;

		case 2:
			if (p.mouseX >= 0 && p.mouseX <= 200 && p.mouseY >= 0 && p.mouseY <= 200) {
				pantalla = 3;
			}
			break;

		case 3:
			if (p.mouseX >= 0 && p.mouseX <= 200 && p.mouseY >= 0 && p.mouseY <= 200) {
				pantalla = 4;
			}
			break;

		case 4:
			if (p.mouseX >= 0 && p.mouseX <= 200 && p.mouseY >= 0 && p.mouseY <= 200) {
				pantalla = 5;
			}
			break;

		case 5:
			game.zonaSensible();
			break;
		}
	}

	public void arrastrar() {
		game.arrastrar();
	}

	public void soltar() {
		game.soltar();
	}

	// Recibo información del servidor
	@Override
	public void recibirInfo(String mensaje) {
		if(mensaje.equals("empezar a jugar")) {
			System.out.println("llego la orden de empezar a jugar");
			pantalla = 5;
		}
		
		try {
			
			String[] terminoJuego = mensaje.split(":");
		
			if(terminoJuego[0].equals("fin del juego")) {
				game.setPuntajeFinal(Integer.parseInt(terminoJuego[1].trim()));
			} 
			
		}catch(Exception e) {
			
			System.out.println(""+e.getMessage());
		}
		
		
	}

	// Cargo las imágenes
	public void cargarImagenes() {

	}

	// Declaro las variables
	public void inicializarVariables() {
		pantalla = 0;
	}

	@Override
	public void enviarInfo(String mensaje) {
		comunicacion.enviar(mensaje);
	}
}
