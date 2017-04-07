import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;


/**
 * Ajoluokka. Luokka sis‰lt‰‰ main-metodin. Luokassa alustetaan ohjelmassa
 * tarvittavat oliot ja k‰ynnistet‰‰n s‰ikeet.
 * <p>
 * Olio-ohjelmoinnin harjoitustyˆ/IhanSama/kev‰t 2017
 * <p>
 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
 *
 */


public class Ajoluokka {

	/** Robotin havaitsemien esteiden lukum‰‰r‰*/
	public static int esteLKM = 0;		
	/** Et‰isyys, jolta robotin ultra-anturi havaitsee esteen centtimetrein‰*/
	public static int MAX_DETECT = 25; 

	public static void main(String[] args) throws IOException {

		
		//Alustetaan oliot
		Moottori Mot = new Moottori();
		Timer Tm = new Timer();
		ValoAnturi Va = new ValoAnturi(Mot);
		Anturit aT = new Anturit(Mot, Tm, Va);

		//Alustetaan Sensorit
		Thread Sensorit = new Thread(aT);
		
		//Aloitetaan USB yhteyden muodostaminen
		LCD.drawString("Odottaa yhteytta", 0, 0);
		USBConnection conn = USB.waitForConnection();
		DataOutputStream dOut = conn.openDataOutputStream();
		DataInputStream dIn = conn.openDataInputStream();
		LCD.clear();
		
		while (true) 
		{
			String robonimi;
			try
			{
				//Otetaan vastaan Robotin nimi
				robonimi = dIn.readUTF();
			}
			catch (EOFException e) 
			{
				break;
			}     
			//Kirjataan Robotin nimi ruudulle
			dOut.writeUTF(robonimi);
			dOut.flush();
			LCD.drawString(robonimi, 2, 0);
		}
		//Suljetaan yhteydet
		dOut.close();
		dIn.close();
		conn.close();


		// Ohjelma aloittaa suorittamisen napin painalluksen j‰lkeen
		Button.waitForAnyPress();

		//K‰ynnistet‰‰n s‰ie viivan- ja esteen haistelua varten
		Sensorit.start();

		Button.waitForAnyPress();

	}

}