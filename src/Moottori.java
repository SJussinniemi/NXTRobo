import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;


public class Moottori {

	private int vakionopeus = 250; //Robotin nopeus kuljettaessa suoraan eteenpäin
	private int korjausnopeus = 100; //Renkaan pyörimisnopeus kun korjataan roboton positiota viivalla
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

	public void eteenpainRobo(){

		Motor.B.forward();
		Motor.C.forward();

		Motor.B.setSpeed(vakionopeus);
		Motor.C.setSpeed(vakionopeus);

	}

	public void pysahdyRobo(){

		LCD.clear();
		LCD.drawString("Olen Pysahdorobo metodissa", 0, 0);

		Motor.B.stop();
		Motor.C.stop();

	}

	public void kaannyVasen(){

		Motor.B.setSpeed(10);
		Motor.B.forward();

		Motor.C.forward();
		Motor.C.setSpeed(estenopeus);
	}


	public void vaistaOikea(){
		
		DifferentialPilot pilot = new DifferentialPilot(1.8f, 3.4f, Motor.B,Motor.C);
		
		Delay.msDelay(1000);
		pilot.setRotateSpeed(150);

		pilot.rotate(90);
		pilot.travel(8);
		pilot.rotate(-90);
		pilot.travel(8);
		pilot.rotate(-90);
		pilot.travel(8);
		pilot.rotate(90);

		LCD.clear();
		LCD.drawString("Kaannetty", 0, 0);

	}



}