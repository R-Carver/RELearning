package de.hcmlab.prl.reetiwoz;

public class RoboSatz {
	
	String thema;
	String frage;
	String[] spielerAntworten;
	String roboErwiederung;
	
	public RoboSatz(String thema, String frage, String[] spAntworten, String rErw) {
		
		this.thema = thema;
		this.frage = frage;
		this.spielerAntworten = spAntworten;
		this.roboErwiederung = rErw;
	}
}
