import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;


public class ValoAnturi implements Runnable {

	LightSensor valoanturi = new LightSensor ( SensorPort. S4 ) ;

	Moottori motti;

	public ValoAnturi(Moottori m){
		motti = m;
	}




	public void run() {
		// TODO Auto-generated method stub
		while(Ajoluokka.ajossa == true){


			// Silkkaa kokeilujöötiä tän kanssa Seuraa nyt oikeaa puolta
			while(!Button.ESCAPE.isDown()){
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				LCD.drawString("Valo: ", 0, 6);
				LCD.drawInt (valoanturi.readValue ( ) , 6 , 6) ;
				/* LCD.drawInt ( valoanturi.readNormalizedValue ( ) , 4 , 0 , 1 ) ;
				LCD.drawInt ( SensorPort.S4.readRawValue ( ) , 4 , 0 , 2 );
				LCD.drawInt (SensorPort.S4.readValue ( ) , 4 , 0 , 3 ) ;
				 */

				if(valoanturi.readValue() >= 44 && valoanturi.readValue() <= 52){
					motti.eteenpainRobo();

				}else if(valoanturi.readValue() <= 36){
					//kääntyy oikealle
					Motor.B.stop();
					Motor.C.setSpeed(motti.getKorjausnopeus());
					Motor.C.forward();
					//kääntyy vasemmalle
				}else if(valoanturi.readValue() >= 55){

					Motor.C.stop();
					Motor.B.setSpeed(motti.getKorjausnopeus());
					Motor.B.forward();


				}

				else if(valoanturi.readValue() <= 40){
					//kääntyy oikealle
					Motor.B.flt();
					Motor.B.setSpeed(motti.getNopeustaakse());
					Motor.C.setSpeed(motti.getKorjausnopeus());
					Motor.C.forward();
					//kääntyy vasemmalle
				}else if(valoanturi.readValue() >= 60){

					Motor.C.flt();
					Motor.C.setSpeed(motti.getNopeustaakse());
					Motor.B.setSpeed(motti.getKorjausnopeus());
					Motor.B.forward();


				}

				//vasen  




			}

		}

	}
}
