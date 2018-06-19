package de.hcmlab.prl.reetiwoz;

import java.util.HashMap;
import java.util.Map;

public class QLerner {
	
	private static QLerner instance;
	
	Map QTable = new HashMap<String, SmallTalkAction[] actions>();
	float alpha = 0.5f;
	float gamma = 0.5f;
	
	//hier die Themen hinzufügen
	String[] themen = {"Begr", "Frei", "Sp", "We", "Begr"};
	
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
		
		//TODO: Hier wird die QTabelle erstellt
	}
	
	
}
