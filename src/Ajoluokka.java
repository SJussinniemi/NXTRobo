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
	
	//public static boolean ajossa = true;
	/** Robotin havaitsemien esteiden lukum‰‰r‰*/
	public static int esteLKM = 0;		// Havaittujen esteiden lukum‰‰r‰
	/** Et‰isyys, jolta robotin ultra-anturi havaitsee esteen*/
	public static int MAX_DETECT = 25; // Et‰isyys jolta robotti havaitsee esteen (cm)

	public static void main(String[] args) throws IOException {
		
		LCD.drawString("waiting", 0, 0);
		USBConnection conn = USB.waitForConnection();
		DataOutputStream dOut = conn.openDataOutputStream();
		DataInputStream dIn = conn.openDataInputStream();
		LCD.clear();
		while (true) 
		{
            String b = "Valtsu";
            try
            {
                b = dIn.readUTF();
            }
            catch (EOFException e) 
            {
                break;
            }         
			dOut.writeUTF(b);
			dOut.flush();
	        LCD.drawString(b, 8, 0);
		}
        dOut.close();
        dIn.close();
        conn.close();
		
		
		//Alustetaan oliot
		Moottori Mot = new Moottori();
		Timer Tm = new Timer();
		ValoAnturi Va = new ValoAnturi(Mot);
		Anturit aT = new Anturit(Mot, Tm, Va);
		
		//Alustetaan Sensorit
		Thread Sensorit = new Thread(aT);

		// Ohjelma aloittaa suorittamisen napin painalluksen j‰lkeen
	//	LCD.drawString("Mr.Robotto", 0, 0);
		Button.waitForAnyPress();
		
		//K‰ynnistet‰‰n s‰ie viivan- ja esteen haistelua varten
		Sensorit.start();
		
		Button.waitForAnyPress();

	}

}