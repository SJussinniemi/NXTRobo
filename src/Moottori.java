import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;


public class Moottori {

	int vakionopeus = 250;
	int peruutusnopeus = 10;
	// aViivanseurauksessa renkaanpyörimisnopeus 
	//kun korjataan roboton positiota viivalla
	int korjausnopeus = 250; 



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

}
