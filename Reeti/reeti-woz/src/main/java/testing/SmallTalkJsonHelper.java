package testing;

public class SmallTalkJsonHelper {
	
	String thema;
	double dauer;
	int belohnung;
	
	public SmallTalkJsonHelper(String thema, double dauer, int belohnung) {
		
		this.thema = thema;
		this.dauer = dauer;
		this.belohnung = belohnung;
	}
	
	public String getThema() {
		
		return this.thema;
	}
	
	public double getDauer() {
		
		return this.dauer;
	}
	
	public int getBelohnung() {
		
		return this.belohnung;
	}
}
