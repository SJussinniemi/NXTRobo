import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;

public class Ajoluokka{

	public static boolean ajossa = true;
	public static int MAX_DETECT = 30; // sEtäisyys havaitusta objetista kun tehdään jotain.
	
	
	public static void main(String[] args) {
		
		Moottori Mot = new Moottori();
		UltraAnturi Ua = new UltraAnturi(Mot); 
		ValoAnturi Va = new ValoAnturi(Mot);
		
		//Ultrasaie ei toistaiseksi käytössä.
		//Thread Ultrasaie = new Thread(Ua);
		Thread Valosaie = new Thread(Va);
		//Ohjelma aloittaa suorittamisen
		LCD.drawString("mr robotto", 0, 0);
		Button.waitForAnyPress();

		//Ultrasaie ei toistaiseksi käytössä.
		//Ultrasaie.start();
		Valosaie.start();
		// Kutsutaan ultra-anturin kuuntelija.
		
		
		Ua.suorita(Ua);
		
		
		



/*		
		while (Ua.havaittu == false) {
			
			Mot.eteenpainRobo();
			
		}
*/
		//Mot.eteenpainRobo();
		//Mot.pysahdyRobo();
		Button.waitForAnyPress();
		
	}

}
