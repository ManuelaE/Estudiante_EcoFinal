package final_eco;

import java.io.IOException;

import com.google.cloud.firestore.DocumentChange.Type;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.gson.Gson;

import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;

import com.google.gson.reflect.TypeToken;
import java.util.HashMap;

///////////ESTUDIANTE
public class Login {

	private PApplet p;
	private ControlP5 cp5;
	private boolean terminado;
	private String url, reference, posiciondeReg;

	private String correo, nombre, pass;
	private Gson g;
	private String respuesta;
	private int pantalla;

	public Login(PApplet p) {
		this.p = p;
		correo = new String();
		nombre = new String();
		pass = new String();

		pantalla = 0;

		cp5 = new ControlP5(p);
		inputs();

		try {
			url = "https://ecofinal-349b8.firebaseio.com/";
			reference = url + "/usuarios/estudiantes.json";
			g = new Gson();
			respuesta = WEBUtilDomi.GETrequest(reference);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		terminado = false;

		posiciondeReg = "";
	}

	public void pintar() {
		pantallas();
	}

	public void pantallas() {
		p.background(170);

		switch (pantalla) {
		case 1:
			// registrar
			p.text("Nombre", 335, 190);
			cp5.get("").show();
			p.text("Correo", 335, 280);
			cp5.get(" ").show();
			p.text("Contraseña", 335, 370);
			cp5.get("  ").show();

			p.fill(255);
			p.text("Registrar", 100, 600);
			break;

		case 0:
			cp5.get("").hide();
			p.text("Correo", 335, 280);
			cp5.get(" ").show();
			p.text("Contraseña", 335, 370);
			cp5.get("  ").show();

			p.fill(255);
			p.text("Iniciar", 500, 600);
			break;

		default:
			cp5.get("").hide();
			cp5.get(" ").hide();
			cp5.get("  ").hide();
			break;
		}
	}

	public void iniciarSesion() {
		//nombre = cp5.get(Textfield.class, "").getText();
		correo = cp5.get(Textfield.class, " ").getText();
		pass = cp5.get(Textfield.class, "  ").getText();

		System.out.println("Usuario: " + nombre + "\ncorreo: " + correo + "\npass: " + pass + "\npass: " + "\n\n");

		try {
			UserRecord userExistente = FirebaseAuth.getInstance().getUserByEmail(correo);
		//	if (userExistente.getDisplayName().equals(nombre)) {
				System.out.println("bienvenido " + userExistente.getDisplayName());

				respuesta = WEBUtilDomi.GETrequest(reference);
				java.lang.reflect.Type tipo = new TypeToken<HashMap<String, Estudiante>>() {
				}.getType();

				HashMap<String, Estudiante> lista = g.fromJson(respuesta, tipo);

				for (String key : lista.keySet()) {
					Estudiante e = lista.get(key);
					System.out.println(key);
					System.out.println(e.getNombre());

					if(e.getCorreo().equals(correo)) {
						posiciondeReg = e.getPosiciondeRegistro();
						System.out.println("mi posicion de registro es"+posiciondeReg);
						pantalla = 7;
						terminado = true;
						return;
					}
				}
			//} else {
			//	System.out.println("contraseña incorrecta " + userExistente.getDisplayName());
			//}
		} catch (Exception e) {
			System.out.println("no existe una cuenta con el correo:  " + correo);
		}
	}

	public void registarse() {
		nombre = cp5.get(Textfield.class, "").getText();
		correo = cp5.get(Textfield.class, " ").getText();
		pass = cp5.get(Textfield.class, "  ").getText();

		System.out.println("Usuario: " + nombre + "\ncorreo: " + correo + "\npass: " + pass + "\npass: " + "\n\n");

		if (!nombre.equals("") && !correo.equals("") && !pass.equals("") && pass.length() > 6) {
			try {
				UserRecord userExistente = FirebaseAuth.getInstance().getUserByEmail(correo);
				System.out.println("ya existe un usuario con el email " + correo);
				return;
			} catch (FirebaseAuthException e1) {
				try {
					CreateRequest request = new CreateRequest().setEmail(correo).setPassword(pass)
							.setDisplayName(nombre);
					UserRecord userNuevo = FirebaseAuth.getInstance().createUser(request);
					System.out.println("nuevo usuario creado " + userNuevo.getUid());

					// ya se hizo la autenticacion ahora vamos a guardar en la base de datos
					try {

						java.lang.reflect.Type tipo = new TypeToken<HashMap<String, Estudiante>>() {
						}.getType();

						HashMap<String, Estudiante> lista = g.fromJson(respuesta, tipo);
						// System.out.println(lista.size());

						if (lista == null) {
							posiciondeReg = "1";
						} else {
							posiciondeReg = (lista.size() + 1) + "";
						}
						Estudiante manuela = new Estudiante(userNuevo.getUid(), nombre, pass, correo, posiciondeReg);
						String json = g.toJson(manuela);

						// post //push - crea una rama aleatoria
						// WEBUtilDomi.PUTrequest(reference, json);
						String rama = WEBUtilDomi.JsonByPOSTrequest(reference, json);
						manuela.setIdDeBase(rama);

						System.out.println("Agregado a la base de datos ");
						pantalla = 7;
						terminado = true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (FirebaseAuthException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// Campos de texto
	public void inputs() {

		int blanco = p.color(0, 0, 0, 0);
		int negro = p.color(60, 60, 59);

		cp5.addTextfield("").setPosition(335, 208).setSize(400, 24).setColor(negro).setColorForeground(blanco)
				.setColorBackground(blanco).setColorActive(blanco).setColorLabel(blanco);

		cp5.addTextfield(" ").setPosition(335, 297).setSize(400, 24).setColor(negro).setColorForeground(blanco)
				.setColorBackground(blanco).setColorActive(blanco).setColorLabel(blanco);

		cp5.addTextfield("  ").setPosition(335, 387).setSize(400, 24).setColor(negro).setColorForeground(blanco)
				.setColorBackground(blanco).setColorActive(blanco).setColorLabel(blanco);
	}

	public void enviar() {
		switch (pantalla) {
		case 1:
			if (p.mouseX >= 0 && p.mouseX <= 150 && p.mouseY >= 550 && p.mouseY <= 700) {
				registarse();
			}
			if (p.mouseX >= 0 && p.mouseX <= 100 && p.mouseY >= 0 && p.mouseY <= 100) {
				pantalla=0;
			}
			break;

		case 0:
			if (p.mouseX >= 450 && p.mouseX <= 550 && p.mouseY >= 550 && p.mouseY <= 700) {
				iniciarSesion();
			}
			
			if (p.mouseX >= 0 && p.mouseX <= 100 && p.mouseY >= 0 && p.mouseY <= 100) {
				pantalla=1;
			}
			break;

		default:
			break;
		}
	}

	public boolean isTerminado() {
		return terminado;
	}

	public void setTerminado(boolean terminado) {
		this.terminado = terminado;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosiciondeReg() {
		return posiciondeReg;
	}

	public void setPosiciondeReg(String posiciondeReg) {
		this.posiciondeReg = posiciondeReg;
	}
}