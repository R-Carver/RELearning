package testing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONObject;

public class JsonTest {
	
	public class TestClass{
			
		String name;
		int value1;
		String value2;
		
		public TestClass(String name, int value1, String value2) {
			
			this.name = name;
			this.value1 = value1;
			this.value2 = value2;
		}
		
		public String getName() {
			
			return this.name;
		}
		
		public int getValue1() {
			
			return this.value1;
		}
		
		public String getValue2() {
			
			return this.value2;
		}
		
	}
	
	public void testJson() {
		
		//TestClass testInstance = new TestClass("Viech", 1, "Viechwert");
		
		SmallTalkEpisode episode = new SmallTalkEpisode();
		SmallTalkGespraech gespraech = new SmallTalkGespraech(0);
		
		SmallTalkJsonHelper testInstanz1 = new SmallTalkJsonHelper("We", 2.34 , 0);
		SmallTalkJsonHelper testInstanz2 = new SmallTalkJsonHelper("Bez", 5.24 , 0);
		SmallTalkJsonHelper testInstanz3 = new SmallTalkJsonHelper("Begr", 22.34 , 1);
		
		JSONObject jsonObject = new JSONObject(testInstanz1);
		String myJson1 = jsonObject.toString();
		gespraech.addGespraech(myJson1);
		
		jsonObject = new JSONObject(testInstanz2);
		String myJson2 = jsonObject.toString();
		gespraech.addGespraech(myJson2);
		
		jsonObject = new JSONObject(testInstanz3);
		String myJson3 = jsonObject.toString();
		gespraech.addGespraech(myJson3);
		
		episode.addGespraech(gespraech.createInnerJson());
		
		SmallTalkGespraech gespraech1 = new SmallTalkGespraech(1);
		
		SmallTalkJsonHelper testInstanz4 = new SmallTalkJsonHelper("We", 2.34 , 0);
		SmallTalkJsonHelper testInstanz5 = new SmallTalkJsonHelper("Bez", 5.24 , 0);
		SmallTalkJsonHelper testInstanz6 = new SmallTalkJsonHelper("Begr", 22.34 , 1);
		
		jsonObject = new JSONObject(testInstanz1);
		String myJson4 = jsonObject.toString();
		gespraech1.addGespraech(myJson1);
		
		jsonObject = new JSONObject(testInstanz2);
		String myJson5 = jsonObject.toString();
		gespraech1.addGespraech(myJson2);
		
		jsonObject = new JSONObject(testInstanz3);
		String myJson6 = jsonObject.toString();
		gespraech1.addGespraech(myJson3);
		
		episode.addGespraech(gespraech1.createInnerJson());
		
		System.out.println(episode.createInnerJson());
		
		FileSaver fileSaver = new FileSaver();
		fileSaver.stringAsJson(episode.createInnerJson(), "FinalJson.json");
	}
	
}
