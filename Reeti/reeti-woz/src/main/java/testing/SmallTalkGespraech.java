package testing;

import java.util.ArrayList;

public class SmallTalkGespraech {
	
	ArrayList<String> gespraeche;
	int gespraechCounter;
	
	public SmallTalkGespraech(int gespraechCounter) {
		
		this.gespraechCounter = gespraechCounter;
		this.gespraeche = new ArrayList<String>();
	}
	
	public String createInnerJson() {
		
		String out = "\"" + this.gespraechCounter + "\": ";  
		out += "[";
		
		for(String gespraech : this.gespraeche) {
			
			out += gespraech;
			out += ",";
		}
		//remove last comma
		out = out.substring(0, out.length() - 1);
		
		out += "]";
		return out;
	}
	
	public void addGespraech(String gespraech) {
		
		this.gespraeche.add(gespraech);
	}
}
