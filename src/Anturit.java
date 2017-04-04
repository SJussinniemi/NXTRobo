import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;


public class Anturit implements Runnable {

	private Moottori Mot;
	private Timer Tm;
	private ValoAnturi vA;
	public int valolukema;
	UltrasonicSensor us = new UltrasonicSensor(SensorPort.S1);
	LightSensor valoanturi = new LightSensor ( SensorPort. S4 ) ;

	public Anturit(Moottori Mot, Timer Tm, ValoAnturi vA){
		this.Mot = Mot;
		this.Tm = Tm;
		this.vA = vA;

	}

	public void run() {
		
		Tm.aloitaTimer();
		while(true){
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Tm.tulosTimer();
			
			valolukema = valoanturi.readValue();
			LCD.drawString("Valo: ", 0, 6);
			LCD.drawInt (valoanturi.readValue( ) , 6 , 6);

			if(us.getDistance() <= Ajoluokka.MAX_DETECT){
				
				Mot.pysahdyRobo();
				
				Ajoluokka.esteLKM++;
				if(Ajoluokka.esteLKM == 2){
					Sound.beep();
					//Ajoluokka.ajossa = false;
					break;
				}
				Mot.vaistaOikea();
				Tm.tulosTimer();
			}
			
			if(valoanturi.readValue() >= 45 && valoanturi.readValue() <= 53){
				Mot.eteenpainRobo();
			
			}else if(valoanturi.readValue() <= 45){ //k��ntyy oikealle

				Motor.B.setSpeed(100);
				Motor.C.setSpeed(Mot.getKorjausnopeus());
				Motor.C.forward();
				
			}else if(valoanturi.readValue() >= 53){ //k��ntyy vasemmalle

				Motor.C.setSpeed(100);
				Motor.B.setSpeed(Mot.getKorjausnopeus());
				Motor.B.forward();

			}
		}
	}
}
