import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;

public class Ajoluokka {

	public static boolean ajossa = true;
	public static int MAX_DETECT = 25; // Et‰isyys jolta robotti havaitsee esteen (cm)

	public static void main(String[] args) {
		
		//Alustetaan oliot
		Moottori Mot = new Moottori();
		UltraAnturi Ua = new UltraAnturi(Mot);
		ValoAnturi Va = new ValoAnturi(Mot);
		Anturit aT = new Anturit(Mot, Ua, Va);
		
		//Alustetaan Sensorit
		Thread Sensorit = new Thread(aT);

		// Ohjelma aloittaa suorittamisen napin painalluksen j‰lkeen
		LCD.drawString("Mr.Robotto", 0, 0);
		Button.waitForAnyPress();
		
		//K‰ynnistet‰‰n s‰ie viivan- ja esteen haistelua varten
		Sensorit.start();
		
		Button.waitForAnyPress();

	}

}
