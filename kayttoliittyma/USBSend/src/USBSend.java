
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

		} 
				);

		if (!conn.connectTo("usb://")){
			System.err.println("NXT laitetta ei havaittu");
			System.exit(1);
		}

		DataInputStream inDat = new DataInputStream(conn.getInputStream());
		DataOutputStream outDat = new DataOutputStream(conn.getOutputStream());

		//Näyttöön tuleva teksti
		String x = n;		// String joka käsittelee robotin nimen

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