import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;

/**
 * USBSend luokka hoitaa yhteyden muodostamisen robottiin
 * sekä tiedon lähettämisen
 * <p>
 * Olio-ohjelmoinnin harjoitustyö/IhanSama/kevät 2017
 * <p>
 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
 */
public class USBSend {	
	public static void main(String[] args) throws IOException {

	}

	
	/**
	 * 
	 * ajaKysely suorittaa Tietokoneen ja NXT robotin välisen yhteyden muodostamisen
	 * sekä lähettää käyttöliittymän kautta annetun String tyypin muuttujan robotin näytölle esitettäväksi
	 * 
	 * @param n = Vastaanotetaan RoboUI.javan textField oliosta, tässä tarkoituksessa sillä käsitellään robotin nimeä.
	 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
	 */
	public void ajaKysely(String n){

		NXTConnector conn = new NXTConnector();

		conn.addLogListener(new NXTCommLogListener(){

			public void logEvent(String message) {
				System.out.println("USBSend Log.listener: "+message);

			}

			public void logEvent(Throwable throwable) {
				System.out.println("USBSend Log.listener - stack trace: ");
				throwable.printStackTrace();

			}

		});

		// Etsitään USB yhteyttä
		if (!conn.connectTo("usb://")){
			System.err.println("NXT laitetta ei havaittu");
			System.exit(1);
		}

		DataInputStream inDat = new DataInputStream(conn.getInputStream());
		DataOutputStream outDat = new DataOutputStream(conn.getOutputStream());

		//Näyttöön tuleva teksti
		String x = n;		// String joka käsittelee robotin nimen, n = Tämän olion vastaanottama parametri

		try {
			outDat.writeUTF(x); //robotille lähtevä data
			outDat.flush();

		} catch (IOException ioe) {
			System.err.println("IO Exception writing bytes");
		}

		try {
			inDat.close();
			outDat.close();
			System.out.println("Datavirta Suljettu");
		} catch (IOException ioe) {
			System.err.println("IO Exception yhteys sulkeminen");
		}

		try {
			conn.close();
			System.out.println("Yhteys Suljettu");
		} catch (IOException ioe) {
			System.err.println("IO Exception yhteyden sulkeminen");
		}
		System.out.println("Suoritettu onnistuneesti!");
	}

}