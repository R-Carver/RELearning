package testing;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class QLerner {
	
	private static QLerner instance;
	
	Map qTable = new HashMap<String, SmallTalkAction[]>();
	float alpha = 0.5f;
	float gamma = 0.5f;
	
	//hier die Themen hinzufügen
	//ACHTUNG: Aktionen in der QWert Tabelle haben jeweil den Index wie
	//im themen array
	String[] themen = { "Begr", "Frei", "Sp", "We", "Bez", "Vor", "Ver", "Ber", "Stud", "Par"};
	//zur Vereinfachung speichern wir hier den index der jeweiligen Aktion
	Map indexHelper = new HashMap<String, Integer>();
	public Map abkurzHelper = new HashMap<String, String>();
	
	private QLerner() {
		
		//initialize QTable
		initQTable(themen);
		initAbkurzHelper();
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
		LinkedList<String> themenList = new LinkedList<String>();
		for (String t : actions) {
			themenList.add(t);
		}
		
		int i = 0;
		for (String t : themenList) {
			String root = themenList.get(i);
			createString(themenList, root, root);

			i++;
		}
	}
	
	public void createString(List<String> actions, String thema, String root) {
		String newState;
		List<String> newActions = new LinkedList<String>(actions);

		for (String S : actions) {
			
			if(!S.equals(root)) {
				newState = thema + "-" + S;
				System.out.println(newState);
				qTable.put(newState, createSmallTalkAction());

				newActions.remove(S);
				createString(newActions, newState, root);
			}
		}

		 System.out.println(qTable.size());
	}
	
	public SmallTalkAction[] createSmallTalkAction() {
		SmallTalkAction[] actionArray = new SmallTalkAction[themen.length];
		SmallTalkAction actionItem;

		for (int i = 0; i < themen.length; i++) {
			actionItem = new SmallTalkAction(themen[i], 0);
			actionArray[i] = actionItem;
		}

		return actionArray;
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
	
	private void initAbkurzHelper() {
		
		abkurzHelper.put("Begrüßung", "Begr");
		abkurzHelper.put("Freizeit", "Frei");
		abkurzHelper.put("Sport", "Sp");
		abkurzHelper.put("Wetter", "We");
		abkurzHelper.put("Beziehung", "Bez");
		abkurzHelper.put("Vorstellung", "Vor");
		abkurzHelper.put("Verabschiedung", "Ver");
		abkurzHelper.put("Beruf", "Ber");
		abkurzHelper.put("Studium", "Stud");
		abkurzHelper.put("Party", "Par");
	}
}
