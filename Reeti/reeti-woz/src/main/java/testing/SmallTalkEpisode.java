package testing;

import java.util.ArrayList;

public class SmallTalkEpisode {
	
	ArrayList<String> sequenzen;
	
	public SmallTalkEpisode() {
		
		this.sequenzen = new ArrayList<String>();
	}
	
	public String createInnerJson() {
		
		String out = "";  
		out += "{";
		
		for(String sequenz : this.sequenzen) {
			
			out += sequenz;
			out += ",";
		}
		//remove last comma
		out = out.substring(0, out.length() - 1);
		
		out += "}";
		return out;
	}
	
	public void addGespraech(String gespraech) {
		
		this.sequenzen.add(gespraech);
	}
}
