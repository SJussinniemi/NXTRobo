import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

/**
 * Moottori-luokka. Luokan attributtien ja metodien avulla hallitaan
 * robotin nopeuksia ja liikkumista. 
 * <p>
 * Olio-ohjelmoinnin harjoitusty�/IhanSama/kev�t 2017
 * <p>
 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
 *
 */

public class Moottori {
	
	/** Nopeus robotin kulkiessa suoraan eteenp�in*/
	private int vakionopeus = 450; // Robotin nopeus kuljettaessa suoraan eteenp�in
	/** Renkaan nopeus, kun korjataan robotin asentoa viivalla*/
	private int korjausnopeus = 300; // Renkaan py�rimisnopeus kun korjataan roboton positiota viivalla

	public int getVakionopeus() {
		return vakionopeus;
	}

	public void setVakionopeus(int vakionopeus) {
		this.vakionopeus = vakionopeus;
	}

	public int getKorjausnopeus() {
		return korjausnopeus;
	}

	public void setKorjausnopeus(int korjausnopeus) {
		this.korjausnopeus = korjausnopeus;
	}

	
	/** Liikutetaan robottia vakionopeudella suoraan etennp�in*/
	public void eteenpainRobo() {

		Motor.B.setSpeed(vakionopeus);
		Motor.C.setSpeed(vakionopeus);
		Motor.B.forward();
		Motor.C.forward();

	}
	
	/** Pys�ytet��n robotin molemmat moottorit*/
	public void pysahdyRobo() {

		Motor.B.flt();
		Motor.C.flt();

	}

	/**
	 * TODO
	 */
	public void vaistaOikea() {
			
		DifferentialPilot pilot = new DifferentialPilot(1.8f, 3.4f, Motor.B, Motor.C);
		LightSensor valoanturi = new LightSensor(SensorPort.S4);

		Delay.msDelay(500);

		// K��ntyminen 
		pilot.arc(4, 90);
		pilot.travelArc(-4, 15);
		
		while(true){
			
			eteenpainRobo();
			
			if(valoanturi.readValue() <= 40){
				break;
			}
		}
	}
}