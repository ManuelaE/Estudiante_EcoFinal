package final_eco;

public class Estudiante {
	
	private String uidDeAut;
	private String nombre;
	private String pass;
	private String correo;
	private String posiciondeRegistro;
	private String idDeBase;
	
	public Estudiante() {
		
	}
	
	public Estudiante(String uid, String nombre, String pass, String correo, String pos) {
		super();
		this.uidDeAut = uid;
		this.nombre = nombre;
		this.pass = pass;
		this.correo = correo;
		posiciondeRegistro = pos;
		idDeBase = "";
	}
	
	public String getUid() {
		return uidDeAut;
	}
	public void setUid(String uid) {
		this.uidDeAut = uid;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPosiciondeRegistro() {
		return posiciondeRegistro;
	}

	public void setPosiciondeRegistro(String posiciondeRegistro) {
		this.posiciondeRegistro = posiciondeRegistro;
	}

	public String getIdDeBase() {
		return idDeBase;
	}

	public void setIdDeBase(String idDeBase) {
		this.idDeBase = idDeBase;
	}
	
	
}

