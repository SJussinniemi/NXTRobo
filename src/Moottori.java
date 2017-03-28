import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;


public class Moottori {

	private int vakionopeus = 250;
	private int peruutusnopeus = 10;
	// Viivanseurauksessa renkaanpyörimisnopeus 
	//kun korjataan roboton positiota viivalla
	private int korjausnopeus = 100; 
	private int nopeustaakse = 20;
	private int estenopeus = 100;



	public int getVakionopeus() {
		return vakionopeus;
	}

	public void setVakionopeus(int vakionopeus) {
		this.vakionopeus = vakionopeus;
	}

	public int getPeruutusnopeus() {
		return peruutusnopeus;
	}

	public void setPeruutusnopeus(int peruutusnopeus) {
		this.peruutusnopeus = peruutusnopeus;
	}

	public int getKorjausnopeus() {
		return korjausnopeus;
	}

	public void setKorjausnopeus(int korjausnopeus) {
		this.korjausnopeus = korjausnopeus;
	}

	public int getNopeustaakse() {
		return nopeustaakse;
	}

	public void setNopeustaakse(int nopeustaakse) {
		this.nopeustaakse = nopeustaakse;
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
		
		//Motor.B.stop();
		//Motor.C.stop();
		Motor.B.flt();
		Motor.C.flt();
	}
	
	public void kaannyVasen(){
	
		Motor.B.setSpeed(10);
		Motor.B.forward();
		
		Motor.C.forward();
		Motor.C.setSpeed(estenopeus);
		}
	
	
	public void kaannyOikea(){
		
		Motor.C.setSpeed(10);
		Motor.C.forward();
		
		Motor.B.forward();
		Motor.B.setSpeed(estenopeus);
		
	}

}