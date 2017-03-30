import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;


public class Anturit implements Runnable {

	private Moottori Mot;
	private UltraAnturi uA;
	private ValoAnturi vA;		
	UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
	LightSensor valoanturi = new LightSensor ( SensorPort. S4 ) ;

	public Anturit(Moottori Mot, UltraAnturi uA, ValoAnturi vA){
		this.Mot = Mot;
		this.uA = uA;
		this.vA = vA;

	}

	public void run() {

		while(Ajoluokka.ajossa){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			LCD.drawString("Valo: ", 0, 6);
			LCD.drawInt (valoanturi.readValue( ) , 6 , 6);

			if(us.getDistance() <= Ajoluokka.MAX_DETECT){
				
				Mot.pysahdyRobo();
				Mot.vaistaOikea();
			}
			
			if(valoanturi.readValue() >= 44 && valoanturi.readValue() <= 52){
				Mot.eteenpainRobo();

			}else if(valoanturi.readValue() <= 36){
				//kääntyy oikealle
				Motor.B.stop();
				Motor.C.setSpeed(Mot.getKorjausnopeus());
				Motor.C.forward();
				//kääntyy vasemmalle
			}else if(valoanturi.readValue() >= 55){

				Motor.C.stop();
				Motor.B.setSpeed(Mot.getKorjausnopeus());
				Motor.B.forward();


			}



		}

	}


}
