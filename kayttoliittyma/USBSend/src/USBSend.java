import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import lejos.pc.comm.NXTCommLogListener;
import lejos.pc.comm.NXTConnector;

/**
 * USBSend luokka hoitaa yhteyden muodostamisen robottiin
 * sek� tiedon l�hett�misen
 * <p>
 * Olio-ohjelmoinnin harjoitusty�/IhanSama/kev�t 2017
 * <p>
 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
 */
public class USBSend {	
	public static void main(String[] args) throws IOException {

	}

	
	/**
	 * 
	 * ajaKysely suorittaa Tietokoneen ja NXT robotin v�lisen yhteyden muodostamisen
	 * sek� l�hett�� k�ytt�liittym�n kautta annetun String tyypin muuttujan robotin n�yt�lle esitett�v�ksi
	 * 
	 * @param n = Vastaanotetaan RoboUI.javan textField oliosta, t�ss� tarkoituksessa sill� k�sitell��n robotin nime�.
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

		// Etsit��n USB yhteytt�
		if (!conn.connectTo("usb://")){
			System.err.println("NXT laitetta ei havaittu");
			System.exit(1);
		}

		DataInputStream inDat = new DataInputStream(conn.getInputStream());
		DataOutputStream outDat = new DataOutputStream(conn.getOutputStream());

		//N�ytt��n tuleva teksti
		String x = n;		// String joka k�sittelee robotin nimen, n = T�m�n olion vastaanottama parametri

		try {
			outDat.writeUTF(x); //robotille l�htev� data
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