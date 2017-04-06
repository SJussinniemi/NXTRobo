
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
			System.err.println("No NXT found using USB");
			System.exit(1);
		}
		
		DataInputStream inDat = new DataInputStream(conn.getInputStream());
		DataOutputStream outDat = new DataOutputStream(conn.getOutputStream());
		
		//Näyttöön tuleva teksti
		String x = "Mr.Valtteri";
		//for(int i=0;i<100;i++) 
	//	{
			try {
			   outDat.writeUTF(x); //robotille lähtevä data
			   outDat.flush();
	
			} catch (IOException ioe) {
				System.err.println("IO Exception writing bytes");
			}
	        
			try {
			inDat.close();
			outDat.close();
			System.out.println("Closed data streams");
		} catch (IOException ioe) {
			System.err.println("IO Exception Closing connection");
		}
		
		try {
			conn.close();
			System.out.println("Closed connection");
		} catch (IOException ioe) {
			System.err.println("IO Exception Closing connection");
		}
	}
}