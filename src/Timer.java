import lejos.nxt.LCD;

/**
 * Timer-luokka. Luokan attributtien ja metodien avulla luodaan 
 * sekuntilaskuri, jonka avulla lasketaan robotin viivanseurakseen
 * kulunut aika. 
 * <p>
 * Olio-ohjelmoinnin harjoitustyö/IhanSama/kevät 2017
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
	
	//private long sekunnit;
	//private int minuutit;
	//private long kokonaisaika;

	/** Käynnistetään sekuntilaskuri*/
	public void aloitaTimer() {
		
		aloita = System.currentTimeMillis();
	}

	/** Pysäytetään sekuntilaskuri*/
	public void lopetaTimer() {
		
		lopeta = System.currentTimeMillis();
		
	}
	
	/** Tulostetaan sekuntilaskurin lukema robotin LCD-näytölle*/
	public void tulosTimer() {
		
		lopetaTimer();
		
		tulos = lopeta - aloita;
		
		//kokonaisaika = tulos / 1000;
		//tulos = kokonaisaika;
		
		
		LCD.drawString("Aika:", 0, 1);
		//seknnit
		LCD.drawString("Sec:", 0, 3);

			LCD.drawInt((int)(tulos/1000), 8, 3);
			//minuutit
			LCD.drawString("Min:", 0, 2);
				LCD.drawInt((int) (tulos/1000)/60, 8, 2);
		
			
	}

}