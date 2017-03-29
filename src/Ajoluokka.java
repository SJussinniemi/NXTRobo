import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;

public class Ajoluokka{

	public static boolean ajossa = true;



	public static int MAX_DETECT = 25; // Et�isyys havaitusta objetista kun tehd��n jotain.


	public static void main(String[] args) {

		Moottori Mot = new Moottori();
		UltraAnturi Ua = new UltraAnturi(Mot); 
		ValoAnturi Va = new ValoAnturi(Mot);
		
		Anturit aT = new Anturit(Mot,Ua,Va);

		//Ultrasaie ei toistaiseksi k�yt�ss�.
		//Thread Ultrasaie = new Thread(Ua);
		//Thread Valosaie = new Thread(Va);
		
		Thread Sensorit = new Thread(aT);
		
		//Ohjelma aloittaa suorittamisen
		LCD.drawString("Mr.Robotto", 0, 0);
		Button.waitForAnyPress();

		//Ultrasaie ei toistaiseksi k�yt�ss�.
		//Ultrasaie.start();
		//Valosaie.start();
		Sensorit.start();
		
		
		// Kutsutaan ultra-anturin kuuntelija.
		//Ua.suorita(Ua);


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
