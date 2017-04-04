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

public class Timer  {

	private long aloita;
	private long lopeta;
	private long tulos;
	private long sekunnit;
	private int minuutit;
	//private long kokonaisaika;


	public void aloitaTimer() {
		
		aloita = System.currentTimeMillis();
	}


	public void lopetaTimer() {
		
		lopeta = System.currentTimeMillis();
		
	}

	public void tulosTimer() {
		
		lopetaTimer();
		
		tulos = lopeta - aloita;
		
		//kokonaisaika = tulos / 1000;
		//tulos = kokonaisaika;
		
		
		LCD.drawString("Aika:", 0, 1);
		//seknnit
		LCD.drawString("Sec:", 0, 3);

			LCD.drawInt((int)(tulos/1000), 8, 3);
			//minuutit
			LCD.drawString("Min:", 0, 2);
				LCD.drawInt((int) (tulos/1000)/60, 8, 2);
		
			
	}

}