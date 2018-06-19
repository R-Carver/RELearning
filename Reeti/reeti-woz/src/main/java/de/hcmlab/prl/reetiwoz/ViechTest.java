package de.hcmlab.prl.reetiwoz;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.*;

public class ViechTest {
	
	public static void test(){
		
		URI uri;
		try {
			uri = new URI("file:///C:/Users/makowsda/Desktop/temp/2018ss-prl/Reeti/reeti-woz/src/main/java/Test.json");
			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			JSONObject root = new JSONObject(tokener);
			
			String name = root.getString("Name");
			String art = root.getString("Art");
			Iterator<String> keys = root.keys();
			
			for(Iterator tempKeys = keys; tempKeys.hasNext();) {
				
				System.out.println(tempKeys.next());
			}
			
			String[] testAntworten = {"ja", "nein"};
			RoboSatz testSatz = new RoboSatz("Wetter", 
											"Was da los?", 
											testAntworten,
											null);
			
			Map dict = new HashMap();
			dict.put("test", testSatz);
			
			RoboSatz out = (RoboSatz)dict.get("test");
			System.out.println(out.roboErwiederung);
			
			//System.out.println(name);
			//System.out.println(art);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println("Test");
	}
	
	public static Map<String, RoboSatz[]> loadData(){
		
		Map dictionary = new HashMap();
		
		
		URI uri;
		try {
			
			//Get root of the JSON file 
			uri = new URI("file:///C:/Users/makowsda/Desktop/temp/2018ss-prl/Reeti/reeti-woz/src/main/java/Test.json");
			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			JSONObject root = new JSONObject(tokener);
			//int numOfTopics = root.length();
			
			//Dynamically generate the Dictionary with <Thema, Satz>
			
			for(Iterator tempKeys = root.keys(); tempKeys.hasNext();) {
				
				String thema = (String)tempKeys.next();
				
				JSONArray satzArray = root.getJSONArray(thema);
				int numOfTopics = satzArray.length();
				
				//get the Array of roboSaetze
				RoboSatz[] robArray = new RoboSatz[numOfTopics];
				int robArrayIndex = 0;
				
				for(Iterator roboSatzArray = satzArray.iterator(); roboSatzArray.hasNext();) {
					
					JSONObject currentRoboSatz = (JSONObject)roboSatzArray.next();
					
					String frage;
					if(!currentRoboSatz.isNull("frage")) {
						frage = currentRoboSatz.getString("frage");
					}else {
						
						frage = null;
					}
					
					
					JSONArray antwortenJson = currentRoboSatz.getJSONArray("spielerAntworten");
					int numOfAntworten = antwortenJson.length();
					
					String[] spAntworten = new String[numOfAntworten];
					int index = 0;
					for(Iterator antworten = antwortenJson.iterator(); antworten.hasNext();) {
						
						spAntworten[index] = (String)antworten.next();
						index ++ ;
					}
					
					String erwiederung;
					if(!currentRoboSatz.isNull("roboErwiederung")) {
						
						erwiederung = currentRoboSatz.getString("roboErwiederung");
					}else {
						
						erwiederung = null;
					}

					
					//Create the RoboSatz object
					RoboSatz robObj = new RoboSatz(
								thema,
								frage,
								spAntworten,
								erwiederung
							);
					
					robArray[robArrayIndex] = robObj;
					robArrayIndex ++ ;
				}
				
				dictionary.put(thema, robArray);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dictionary;
		
	}
	
}
