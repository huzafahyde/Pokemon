package com.mygdx.pokemon.Logic.Data;

import java.util.ArrayList;

import com.mygdx.pokemon.Entities.Player;
import com.mygdx.pokemon.Logic.Dialogue;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class DialogueList {
	public ArrayList<Dialogue> dialogues;
	
	public DialogueList() {
		dialogues = new ArrayList<Dialogue>();
	
		//LIST OF DIFFERENT POSSIBLE DIALOGUES
		
		dialogues.add(new Dialogue("Hey there " + Player.name + "! ", PokemonWorld.sb));
		dialogues.add(new Dialogue("Where do you think you're going?", PokemonWorld.sb)); 
		dialogues.add(new Dialogue("Can't even say goodbye to your own mother?", PokemonWorld.sb)); 
		dialogues.add(new Dialogue("Anyways, Professor Hyde wants you to go to \nhis lab down the path.", PokemonWorld.sb)); 
		dialogues.add(new Dialogue("He said he had a present for you!", PokemonWorld.sb));
		dialogues.add(new Dialogue("You should head there now. It is just east \nof here, if you remember.", PokemonWorld.sb));
		dialogues.add(new Dialogue("Be safe!", PokemonWorld.sb));  
		
		dialogues.add(new Dialogue("This is your PC. Access it later for more info.", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("The television isn't on.", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("This bookshelf is full of boring books.", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("This hasn't worked for years...", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("... \nNothing in here.", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("Hey " + Player.name + "!", PokemonWorld.sb));
		dialogues.add(new Dialogue("How are you doing?", PokemonWorld.sb));
		dialogues.add(new Dialogue("I'm great, thanks for asking.\nDo you know anything about my research?", PokemonWorld.sb));
		dialogues.add(new Dialogue("You may know that I am a Pokemon \nprofessor.", PokemonWorld.sb));
		dialogues.add(new Dialogue("That means I study Pokemon.", PokemonWorld.sb));
		dialogues.add(new Dialogue("I want to gather more research on how \nPokemon work.", PokemonWorld.sb));
		dialogues.add(new Dialogue("Therefor I want to give you your first \nPokemon!", PokemonWorld.sb));
		dialogues.add(new Dialogue("They are in that machine if you go up \nand to the left.", PokemonWorld.sb));
		dialogues.add(new Dialogue("What are you waiting for?", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("Wow excellent choice!", PokemonWorld.sb));	
		dialogues.add(new Dialogue("You know, I have been thinking...", PokemonWorld.sb));	
		dialogues.add(new Dialogue("I think you are ready for your own Pokemon \nadventure!", PokemonWorld.sb));	
		dialogues.add(new Dialogue("...                        ", PokemonWorld.sb));	
		dialogues.add(new Dialogue("Don't know what that means?", PokemonWorld.sb));	
		dialogues.add(new Dialogue("Basically there are 8 different Pokemon \ngyms in the world.", PokemonWorld.sb));	
		dialogues.add(new Dialogue("You can challenge them all, and if you defeat \nall of them you can challenge the Pokemon", PokemonWorld.sb));	
		dialogues.add(new Dialogue("Champion!", PokemonWorld.sb));	
		dialogues.add(new Dialogue("Or not! This game has no gyms yet so good \nluck trying.", PokemonWorld.sb));	
		dialogues.add(new Dialogue("But anyways, I want you to go out and \nexplore!", PokemonWorld.sb));	
		dialogues.add(new Dialogue("Battle! Get to know your Pokemon!", PokemonWorld.sb));	
		dialogues.add(new Dialogue("Well, what are you waiting for?", PokemonWorld.sb));	
		dialogues.add(new Dialogue("Every second you waste means you missed a \nchance to capture one of the many", PokemonWorld.sb));	
		dialogues.add(new Dialogue("(9) Pokemon in this world!", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("Oh it's you again.", PokemonWorld.sb));
		dialogues.add(new Dialogue("What? You don't remember me?", PokemonWorld.sb));
		dialogues.add(new Dialogue("Oh come on. I'm Li-a.", PokemonWorld.sb));
		dialogues.add(new Dialogue("Pronounced LIDASHA.", PokemonWorld.sb));
		dialogues.add(new Dialogue("See you've got your own pokemon.", PokemonWorld.sb));
		dialogues.add(new Dialogue("Pathetic.", PokemonWorld.sb));
		dialogues.add(new Dialogue("Let's see what you've got!", PokemonWorld.sb));
		
		dialogues.add(new Dialogue("Wh-", PokemonWorld.sb));
		dialogues.add(new Dialogue("WHAT!", PokemonWorld.sb));
		dialogues.add(new Dialogue("UNACCEPTABLE!", PokemonWorld.sb));
		dialogues.add(new Dialogue("JUST YOU WAIT UNTIL I TELL MY MOM!", PokemonWorld.sb));
		dialogues.add(new Dialogue("THEN YOU'LL HAVE IT!", PokemonWorld.sb));
		dialogues.add(new Dialogue("UNACCEPTABLE!!!", PokemonWorld.sb));
		dialogues.add(new Dialogue("UNACCEPTABLE!!!!!", PokemonWorld.sb));
		dialogues.add(new Dialogue("(Goes and sulks in a corner)", PokemonWorld.sb));
		
	}
	
	
}
