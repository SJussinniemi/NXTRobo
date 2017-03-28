import lejos.nxt.Button;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;
import lejos.util.Delay;


public class UltraAnturi implements Runnable, FeatureListener {

	//asBoolea kun este on havaittu ajetaanko t‰ll‰ booleanilla k‰‰ntyminen?
	private static boolean havaittu;


	UltraAnturi listener; //UltraAnturi();
	//UltrasonicSensor sensori = new UltrasonicSensor(SensorPort.S1);

	Moottori motti;
	UltrasonicSensor us;

	public static boolean isHavaittu() {
		return havaittu;
	}

	public static void setHavaittu(boolean havaittu) {
		UltraAnturi.havaittu = havaittu;
	}

	//kannetaan Moottoriolio UltraAnturi luokkaan
	public UltraAnturi(Moottori m){
		motti = m;
	}

	// Alustetaan ultra-anturin kuuntelija.
	public void suorita(UltraAnturi ua){

		listener = ua;
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
		RangeFeatureDetector fd = new RangeFeatureDetector(us, Ajoluokka.MAX_DETECT, 400);
		fd.addListener(listener);
		//Button.ENTER.waitForPressAndRelease();
	}

	// t‰t‰ ei toistaiseksi k‰ytet‰
	public void run() {

		while(Ajoluokka.ajossa == true){

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				//VIRHE Hyi
				e.printStackTrace();
			}

			LCD.drawString("Etaisyys: ", 0, 2);
			LCD.drawInt(us.getDistance(), 0, 3);

		}

	}

	// t‰m‰ tapahtuu kun havaitaan este.
	public void featureDetected(Feature feature, FeatureDetector detector) {

		DifferentialPilot pilot = new DifferentialPilot(1.8f, 3.4f, Motor.B,Motor.C);

		
		//LCD.drawString("STOPEtaisyys: ", 0, 2);
		UltraAnturi.setHavaittu(true); // Lopettaa viivanhaistelun suorittamisen 
		Ajoluokka.ajossa = false; 
		pilot.stop();
		//motti.pysahdyRobo();

		LCD.clear();
		LCD.drawString("Stopin jalkeen", 0, 0);
		
		motti.vaistaOikea();
		
		UltraAnturi.setHavaittu(false);
		Ajoluokka.ajossa = true;

		//	pilot.travel(50);
		//pilot.rotate(100);
		//Delay.msDelay(2000);





	}
}