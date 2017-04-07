import lejos.nxt.LCD;

/**
 * Timer-luokka. Luokan attributtien ja metodien avulla luodaan 
 * sekuntilaskuri, jonka avulla lasketaan robotin viivanseurakseen
 * kulunut aika. 
 * <p>
 * Olio-ohjelmoinnin harjoitusty�/IhanSama/kev�t 2017
 * <p>
 * @author Matti Pahkuri, Sami Jussinniemi, Valtteri Lattu HAMK
 *
 */

public class Timer  {
	/** sekuntilaskurin alkuaika*/
	private long aloita;
	/** sekuntilaskurin loppuaika*/
	private long lopeta;
	/** sekuntilaskurin alkuajan ja loppuajan erotus*/
	private long tulos;
	

	/** K�ynnistet��n sekuntilaskuri*/
	public void aloitaTimer() {
		
		aloita = System.currentTimeMillis();
	}

	/** Pys�ytet��n sekuntilaskuri*/
	public void lopetaTimer() {
		
		lopeta = System.currentTimeMillis();
		
	}
	
	/** Tulostetaan sekuntilaskurin lukema robotin LCD-n�yt�lle*/
	public void tulosTimer() {
		
		lopetaTimer();
		
		tulos = lopeta - aloita;

		// Tulostetaan kulunutta aikaa robotin n�yt�lle.		
		LCD.drawString("Aika:", 0, 1);
		//seknnit
		LCD.drawString("Sec:", 0, 3);

			LCD.drawInt((int)(tulos/1000), 8, 3);
			//minuutit
			LCD.drawString("Min:", 0, 2);
				LCD.drawInt((int) (tulos/1000)/60, 8, 2);
		
			
	}

}