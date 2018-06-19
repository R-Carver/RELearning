package de.hcmlab.prl.reetiwoz;

import de.hcmlab.tools.reetirest.api.Reeti;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private Reeti reeti;

    private void test() {
        reeti = new Reeti("127.0.0.1", "Reeti");
        // Sprache setzen (nur echter Roboter)
        reeti.setLangVoice(Reeti.LangVoice.DE_STEFAN);
        // neutrale Position einnehmen
        reeti.neutralPosition();
        // etwas sagen
        reeti.say("Ich sage etwas");
        // Ohr bewegen
        reeti.leftEar(0);
        reeti.leftEar(1);
        // Kopf bewegen
        reeti.say("Kopf.");
        reeti.neckPan(-1);
        reeti.neckPan(1);
        // Pose laden aus Datei
        reeti.pose("/pose.json"); // Datei muss im ClassPath liegen
        // linke LED grÃ¼n leuchten lassen
        reeti.led(Reeti.Led.LEFT, 0, 1, 0);
        // Audio Datei abspielen
        try {
            final InputStream stream = getClass().getResourceAsStream("/whistle.ogg");
            reeti.playAudio(reeti.cache(stream, "whistle.ogg"));
            stream.close();
        } catch (IOException e) {
        }

    }
    
    

    public static void main(String[] args) {
        //new Main().test();
        //ViechTest.test();
    	//JsonTest.testJson();
    	//int userInput;
    	Scanner reader = new Scanner(System.in);
    	
    	Reeti reeti;
    	reeti = new Reeti("127.0.0.1", "Reeti");
        // Sprache setzen (nur echter Roboter)
        reeti.setLangVoice(Reeti.LangVoice.DE_STEFAN);
        // neutrale Position einnehmen
        reeti.neutralPosition();
        // etwas sagen
        //reeti.say("Ich sage etwas");
        
    	Map<String, RoboSatz[]> themen = ViechTest.loadData();
    	
    	// Begrüßung
        reeti.say(themen.get("Begrüßung")[0].frage);
        
        Thread thread = new Thread(){
        	
        	public void run() {
        		
        		int index = 0;
        		for(String antworten : themen.get("Begrüßung")[0].spielerAntworten) {
        			
        			System.out.println(index + ": " + antworten);
        			index ++;
        		}
        		
        		int userInput = reader.nextInt();
        		reeti.say(themen.get("Begrüßung")[0].roboErwiederung);
        		
        		Set<String> themenNamen = themen.keySet();
                
                
                for(String thema : themenNamen) {
                	
                	int index2 = 0;
                	reeti.say(themen.get(thema)[0].frage);
            		for(String antworten : themen.get(thema)[0].spielerAntworten) {
            			
            			System.out.println(index2 + ": " + antworten);
            			index2 ++;
            		}
            		
            		userInput = reader.nextInt();
            		reeti.say(themen.get(thema)[0].roboErwiederung);
                }
        	}
        };
        thread.start();
        
    }
}
