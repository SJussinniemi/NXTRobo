import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.FeatureListener;
import lejos.robotics.objectdetection.RangeFeatureDetector;


public class UltraAnturi implements Runnable, FeatureListener {

	//Boolea kun este on havaittu ajetaanko t‰ll‰ booleanilla k‰‰ntyminen?
	boolean havaittu;
	
	//public static int MAX_DETECT = 60;
	
	UltraAnturi listener; //UltraAnturi();
	//UltrasonicSensor sensori = new UltrasonicSensor(SensorPort.S1);
	
	Moottori motti;
	UltrasonicSensor us;
	
	//kannetaan Moottoriolio UltraAnturi luokkaan
	public UltraAnturi(Moottori m){
		motti = m;
	}

	// Alustetaan ultra-anturin kuuntelija.
	public void suorita(UltraAnturi ua){
		
		listener = ua;
		UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
		RangeFeatureDetector fd = new RangeFeatureDetector(us, Ajoluokka.MAX_DETECT, 500);
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
		// TODO Auto-generated method stub
		//LCD.drawString("STOPEtaisyys: ", 0, 2);
		listener.havaittu = true;
		motti.pysahdyRobo();
		
		
	}
}