package testing;

import java.util.HashMap;
import java.util.Map;

public class QLerner {
	
	private static QLerner instance;
	
	Map qTable = new HashMap<String, SmallTalkAction[]>();
	float alpha = 0.5f;
	float gamma = 0.5f;
	
	//hier die Themen hinzufügen
	//ACHTUNG: Aktionen in der QWert Tabelle haben jeweil den Index wie
	//im themen array
	String[] themen = {"Begr", "Frei", "Sp", "We", "Begr"};
	//zur Vereinfachung speichern wir hier den index der jeweiligen Aktion
	Map indexHelper = new HashMap<String, Integer>();
	
	private QLerner() {
		
		//initialize QTable
		initQTable(themen);
	}
	
	public static QLerner getInstance() {
		
		if(QLerner.instance == null) {
			QLerner.instance = new QLerner();
		}
		return QLerner.instance;
	}
	
	//erstellt eine QTable mit Zuständen die alle Kombinationen
	//der Themanfolgen abbilden. Die Aktionen sind dabei die einzelnen
	//Themen
	private void initQTable(String[] actions) {
		
		initIndexHelper();
		
		//TODO: Hier wird die QTabelle erstellt von Leomoffel Kennekaka
	}
	
	private void initIndexHelper() {
		
		int index = 0;
		for(String thema : themen) {
			
			indexHelper.put(thema, new Integer(index));
			index++;
		}
	}
	
	
	public void UpdateQValue(String s1, String s2, int reward, String a) {
		
		//get Q(s1, a)
		SmallTalkAction[] actionsInState1 = (SmallTalkAction[])qTable.get(s1);
		Integer actionIndex = (Integer)indexHelper.get(a);
		SmallTalkAction action = actionsInState1[actionIndex.intValue()];
		float qValueS1 = action.qValue;
		
		//get max-Q(s2, a)
		SmallTalkAction[] actionsInState2 = (SmallTalkAction[])qTable.get(s1);
		SmallTalkAction maxAction = getMaxAction(actionsInState2);
		float maxQValue = maxAction.qValue;
		
		//Q(s1, a) -> Q(s1, a) + alpha[r + gamme * max(a)Q(s2,a) - Q(s1,a)]
		float updateValue = qValueS1 + this.alpha * (reward + this.gamma * maxQValue - qValueS1);
		SetQValue(s1, a, updateValue);
		
	}
	
	public void SetQValue(String state, String action, float updateValue) {
		
		SmallTalkAction[] qValues = (SmallTalkAction[])qTable.get(state);
		Integer actionIndex = (Integer)indexHelper.get(action);
		
		//Update the Q-Value
		qValues[actionIndex.intValue()].qValue = updateValue;
	}
	
	public SmallTalkAction getMaxAction(SmallTalkAction[] actions) {
		
		SmallTalkAction out = actions[0];
		for(SmallTalkAction currentAction : actions) {
			
			if(currentAction.qValue > out.qValue) {
				out = currentAction;
			}
		}
		return out;
	}
	
	
}
