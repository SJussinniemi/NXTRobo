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
		// TaODO Auto-generated method stub
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

				if(valoanturi.readValue() >= 31 && valoanturi.readValue() <= 41){
					motti.eteenpainRobo();

				}else if(valoanturi.readValue() <= 42){

					Motor.B.stop();
					Motor.C.setSpeed(motti.korjausnopeus);
					Motor.C.forward();

				}else if(valoanturi.readValue() >= 50){

					Motor.C.stop();
					Motor.B.setSpeed(motti.korjausnopeus);
					Motor.B.forward();


				}

			}

		}

	}
}


