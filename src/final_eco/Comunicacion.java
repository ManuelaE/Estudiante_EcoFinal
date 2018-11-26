package final_eco;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

///////////ESTUDIANTE
public class Comunicacion extends Thread implements Receptor.OnMessage {

	private Socket s;
	private Receptor receptor;
	private Mensaje observer;
	private OutputStream os;
	private PrintWriter mensaje;

	public Comunicacion() {

	}

	@Override
	public void run() {

		try {
			//verificar la ip del servidor
			s = new Socket("127.0.0.1", 5000);
			receptor = new Receptor(s);
			receptor.setObserver(this);
			receptor.start();
			
			os = s.getOutputStream();
			mensaje = new PrintWriter(new OutputStreamWriter(os));
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void enviar(final String msj) {

		new Thread(new Runnable() {
			@Override
			public void run() {

				try {

					System.out.println("ME PREPARO PARA ENVIAR UN MENSAJE");
					mensaje.println(msj);
					mensaje.flush();

				} catch (Exception e) {

					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void recibido(String mensaje) {
		observer.recibirInfo(mensaje);
	}

	public interface Mensaje {

		public void recibirInfo(String mensaje);
	}

	public void setObserver(Mensaje mensajito) {

		this.observer = mensajito;
	}
}
