package final_eco;

import java.io.FileInputStream;
import java.util.Collections;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import processing.core.PApplet;

///////////ESTUDIANTE
public class Main extends PApplet {

	FileInputStream serviceAccount;
	FirebaseOptions options;

	private Logica log;

	public static void main(String[] args) {

		PApplet.main("final_eco.Main");

	}

	public void setup() {
		log = new Logica(this);
		try {

			serviceAccount = new FileInputStream("ecofinal-349b8-firebase-adminsdk-0nvov-25c5c3db57.json");
			options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://ecofinal-349b8.firebaseio.com/").build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}
	}

	public void settings() {
		size(1200, 700);
	}

	public void draw() {
		log.pintar();
	}

	public void mouseDragged() {
		log.arrastrar();
	}

	@Override
	public void mouseReleased() {
		log.soltar();
	}

	public void mousePressed() {
		//System.out.println(("" + this.mouseX) + ("     " + this.mouseY));
		log.zonaSensible();
	}
}
