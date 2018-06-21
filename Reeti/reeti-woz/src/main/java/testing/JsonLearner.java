package testing;

import java.net.URI;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonLearner {
	
	public JSONObject root;
	
	public void loadRoot() {
		
		try {
			
			URI uri = new URI("file:///C:/Users/makowsda/Desktop/temp/2018ss-prl/Reeti/reeti-woz/FinalJson.json");
			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			root = new JSONObject(tokener);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void UpdateTableFromRoot() {
		
		int talkCounter = root.length();
		
		//for every state update the qTable
		for(int i = 0; i < talkCounter ; i++) {
			
			JSONArray talks = root.getJSONArray(Integer.toString(i));
			//System.out.println(talks);
			
			//create the state, the last element has the current action
			String currentState  = "";
			for(int j = 0; j < talks.length()-1 ; j++) {
				
				JSONObject obj = (JSONObject)talks.get(j); 
				currentState += obj.getString("thema") + "-";
			}

			//remove last dash
			currentState = currentState.substring(0, currentState.length() - 1);
			
			//now get the action and the reward from the last object
			JSONObject lastObj = (JSONObject)talks.get(talks.length()-1);
			
			String action = lastObj.getString("thema");
			float reward = (float)lastObj.getDouble("dauer");
			
			UpdateQValue(currentState, action, reward);
		}
		
		
		//to update the q table we need the state, the action and the value
		//UpdateQValue(String state, String action, float value);
	}
	
	private void UpdateQValue(String state, String action, float value) {
		
		SmallTalkAction[] actionsToUpdate = (SmallTalkAction[])QLerner.getInstance().qTable.get(state);
		SmallTalkAction currentAction = actionsToUpdate[(int)QLerner.getInstance().indexHelper.get(action)];
		currentAction.qValue = value;
	}
}
