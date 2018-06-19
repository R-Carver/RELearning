package testing;

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
		
		TestClass testInstance = new TestClass("Viech", 1, "Viechwert");
		
		JSONObject jsonObject = new JSONObject(testInstance);
		String myJson = jsonObject.toString();
		
		System.out.println(myJson);
	}
	
}
