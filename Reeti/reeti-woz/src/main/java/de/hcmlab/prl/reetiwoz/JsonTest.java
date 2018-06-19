package de.hcmlab.prl.reetiwoz;

import java.net.URI;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonTest {
	
	public static void testJson() {
		
		try {
			
			URI uri = new URI("file:///C:/Users/makowsda/Desktop/temp/2018ss-prl/Reeti/reeti-woz/src/main/java/Test.json");
			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			JSONObject root = new JSONObject(tokener);
			
			for(Iterator keys = root.keys() ; keys.hasNext();) {
				
				String key = (String)keys.next();
				System.out.println(key);
				JSONArray satzArray = root.getJSONArray(key);
				for(Iterator roboSatzArray = satzArray.iterator() ; roboSatzArray.hasNext();) {
					
					JSONObject currentRoboSatz = (JSONObject)roboSatzArray.next();
					
					System.out.println(currentRoboSatz.get("frage"));
					System.out.println(currentRoboSatz.get("roboErwiederung"));
				}
			}
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
