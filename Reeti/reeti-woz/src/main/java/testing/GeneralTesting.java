package testing;

import org.json.JSONObject;

public class GeneralTesting {

	public static void main(String[] args) {
		
		JsonTest jsonTest = new JsonTest();
		jsonTest.testJson();
		
		JsonLearner jLearner = new JsonLearner();
		jLearner.loadRoot();
		System.out.println(jLearner.root.toString());
		
		jLearner.UpdateTableFromRoot();
		
		//System.out.println(jLearner.root.length());
		
		//QLerner.getInstance();
		
	}
}
