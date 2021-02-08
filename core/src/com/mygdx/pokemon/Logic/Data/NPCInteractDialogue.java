package com.mygdx.pokemon.Logic.Data;

import java.util.ArrayList;

import com.mygdx.pokemon.Logic.Dialogue;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class NPCInteractDialogue {

	public ArrayList<Dialogue> dialogues;
	
	public NPCInteractDialogue() {
		dialogues = new ArrayList<Dialogue>();
		
		//INTERACT DIALOGUE WITH NPC, PROBABLY SHOULD GO WITH THE DIALOGUELIST
		dialogues.add(new Dialogue("Just head east and it should be at the \nend of the path!", PokemonWorld.sb));
	}
}
