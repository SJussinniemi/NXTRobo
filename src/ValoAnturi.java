import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.ListenerCaller;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;

/**
 * Tämä luokka ei enää käytössä!!!!!!!
 * <p>
 * Olio-ohjelmoinnin harjoitustyö/IhanSama/kevät 2017
 * <p>
 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
 *
 */

public class ValoAnturi implements Runnable {

	LightSensor valoanturi = new LightSensor ( SensorPort. S4 ) ;

	Moottori motti;

	public ValoAnturi(Moottori m){
		motti = m;
	}




	public void run() {
		// TODO Auto-generated method stub


	}
}
