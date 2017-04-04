import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Moottori {

	private int vakionopeus = 450; // Robotin nopeus kuljettaessa suoraan eteenpäin
	private int korjausnopeus = 300; // Renkaan pyörimisnopeus kun korjataan roboton positiota viivalla
	private int estenopeus = 100;

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

	public int getEstenopeus() {
		return estenopeus;
	}

	public void setEstenopeus(int estenopeus) {
		this.estenopeus = estenopeus;
	}

	public void eteenpainRobo() {

		Motor.B.setSpeed(vakionopeus);
		Motor.C.setSpeed(vakionopeus);
		Motor.B.forward();
		Motor.C.forward();

	}
	

	public void pysahdyRobo() {

		Motor.B.flt();
		Motor.C.flt();

	}

	public void kaannyVasen() {

		Motor.B.setSpeed(10);
		Motor.B.forward();

		Motor.C.forward();
		Motor.C.setSpeed(estenopeus);
	}

	public void vaistaOikea() {
			
		DifferentialPilot pilot = new DifferentialPilot(1.8f, 3.4f, Motor.B, Motor.C);
		LightSensor valoanturi = new LightSensor(SensorPort.S4);

		Delay.msDelay(500);

		// Kääntyminen 
		pilot.arc(5, 90);
		pilot.travelArc(-5, 15);
		
		while(true){
			
			eteenpainRobo();
			
			if(valoanturi.readValue() <= 40){
				break;
			}
		}
	}
}