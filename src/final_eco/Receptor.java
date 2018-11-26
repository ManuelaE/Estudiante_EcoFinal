package final_eco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

///////////ESTUDIANTE
public class Receptor extends Thread{
	
	private Socket s;
	private OnMessage observer;
	
	public Receptor ( Socket s) {
		this.s = s;
	}
	
	@Override
	public void run() {
		
		try {
			
			InputStream is = s.getInputStream();
			BufferedReader lector = new BufferedReader ( new InputStreamReader (is) );
			
			while (true) {
				
				String mensaje = lector.readLine();
				observer.recibido(mensaje);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public interface OnMessage {
		
		public void recibido ( String mensaje );
	}
	
	public void setObserver ( OnMessage mensajito ) {
		
		this.observer = mensajito;
	}
	
	
}