import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
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
	// 450
	private int vakionopeus = 350; // Robotin nopeus kuljettaessa suoraan eteenp�in
	/** Renkaan nopeus, kun korjataan robotin asentoa viivalla*/
	private int korjausnopeus = 300; // Renkaan py�rimisnopeus kun korjataan roboton positiota viivalla

	// Getterit ja Setterit
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
	 * vaistaOikea suorittaa esteen v�ist�misen kun Ultra��nianturi havaitsee esteen radalla.
	 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
	 */
	public void vaistaOikea() {
		
		//Alustetaan pilot ja valoanturi oliot.
		DifferentialPilot pilot = new DifferentialPilot(1.8f, 3.4f, Motor.B, Motor.C);
		LightSensor valoanturi = new LightSensor(SensorPort.S4);

		Delay.msDelay(500);

		// K��ntyminen "Eka puolisko"
		pilot.arc(4, 90);
		// K��ntyminen "Takaisin radalle"
		// 3.3
		pilot.travelArc(-4.3, 15);
		
		// K��nt�misen j�lkeen robotti ajaa suoraan takaisin kunnes l�yt�� radan.
		while(true){
			
			eteenpainRobo();
			
			// loop suljetaan kun valoanturin arvo vastaa mustaa v�ri�.
			if(valoanturi.readValue() <= 40){
				break;
			}
		}
	}
}