package com.mygdx.pokemon.Logic.Data;

public class InteractData {
	public String[] interactTriggers;
	public boolean[] booleans;
	public String[] directions;
	
	public InteractData() {
		//INTERACT TRIGGERS
		interactTriggers = new String[30];
		interactTriggers[0] = "PC";
		interactTriggers[1] = "Television";
		interactTriggers[2] = "Bookshelf";
		interactTriggers[3] = "Broken";
		interactTriggers[4] = "Fridge";
		interactTriggers[5] = "PokemonSelect";

		//DIRECTION NEEDED TO TRIGGER
		directions = new String[30];
		directions[0] = "Up";
		directions[1] = "Up";
		directions[2] = "Up";
		directions[3] = "Up";
		directions[4] = "Up";
		directions[5] = "Up";
		
		//IS TRIGGERED BOOLEANS
		booleans = new boolean[30];
		for (int i = 0; i < 30; i++) {
			booleans[i] = false;
		}
		
		
	}
	
}
