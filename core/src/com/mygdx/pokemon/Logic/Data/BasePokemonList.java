package com.mygdx.pokemon.Logic.Data;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.pokemon.Logic.PokemonCreature;

public class BasePokemonList {
	
	//HOLDS ALL POSSIBLE CREATURES
	public ArrayList<PokemonCreature> pokemonCreatures;
	
	public String[] names;
	
	public int[] maxHealths, currentHealths;
	
	//BASE STATS
	public int[] bHEA, bATT, bSPA, bDEF, bSPD, bSPE;
	
	//INITIAL LEVELS FOR TESTING
	public int[] bLevels;
	
	//Y OFFSETS WHEN BATTLING BECAUSE WHOEVER PUBLISHED THE SPRITES ONLINE OBVIOUSLY HAS NEVER CODED BEFORES
	public int[] yOffsets1;
	public int[] yOffsets2;
	
	//POSSIBLE MOVES TO GET AND THE NEEDED LEVELS
	public ArrayList<int[]> possibleMoves;
	public ArrayList<int[]> requiredLevels;

	//SAME FOR EACH POKEMON
	public ArrayList<Integer> totalEXPneeded;
	public ArrayList<Integer> EXPneededNextLevel;

	//MOVES THAT DO DIFFERENT AMOUNT OF DAMAGE
	public ArrayList<String[]> effectiveMoves;
	public ArrayList<String[]> superEffectiveMoves;
	public ArrayList<String[]> notEffectiveMoves;
	public ArrayList<String[]> noDamageMoves;
	
	public BasePokemonList() {
		pokemonCreatures = new ArrayList<PokemonCreature>();
		
		//ALL OF THIS IS JUST BASE IFNO FOR EACH POKEMON SO THEY CAN BE INITIALIZED
		
		names = new String[] {"Bulbasaur", "Ivysaur", "Venusaur", "Charmander", "Charmeleon", "Charizard", "Squirtle" , "Wartortle" , "Blastoise"};
		
		bHEA = new int[] {45, 60, 80, 39, 58, 78, 44, 59, 79};
		bATT = new int[] {49, 62, 82, 52, 64, 84, 120, 63, 83};//48
		bDEF = new int[] {49, 63, 83, 43, 58, 78, 65, 80, 100};
		bSPA = new int[] {65, 80, 100, 60, 80, 109, 50, 65, 85}; //85
		bSPD = new int[] {65, 80, 100, 50, 65, 85, 64, 80, 105};
		bSPE = new int[] {45, 60, 80, 65, 80, 100, 43, 58, 78};
		
		bLevels = new int[] {5, 10, 15, 5, 10, 15, 5, 10, 15};
		
		maxHealths = new int[] {100, 150, 200, 110, 160, 210, 10, 20, 20};
		currentHealths = new int[] {100, 150, 200, 110, 160, 210, 10, 20, 20};
		
		yOffsets1 = new int[] {-60, -50, -40, -50, -40, 0, -60, -45, -30};
		yOffsets2 = new int[] {-60, -35, -45, -35, -30, -7, -40, -30, -32};
		
		totalEXPneeded = new ArrayList<Integer>();
		for (int i = 1; i <= 100; i++) {
			totalEXPneeded.add((int)(4*(Math.pow(i, 3)))/5);
			System.out.println(totalEXPneeded.get(i-1));
		}
		
		EXPneededNextLevel = new ArrayList<Integer>();
		for (int i = 0; i < 99; i++) {
			EXPneededNextLevel.add(totalEXPneeded.get(i + 1) - totalEXPneeded.get(i));
			System.out.println(EXPneededNextLevel.get(i));
		}
		
		possibleMoves = new ArrayList<int[]>();
		requiredLevels = new ArrayList<int[]>();
		
		possibleMoves.add(new int[] {4, 5});		requiredLevels.add(new int[] {5, 5});
		possibleMoves.add(new int[] {0, 1});		requiredLevels.add(new int[] {3, 4});
		possibleMoves.add(new int[] {0});			requiredLevels.add(new int[] {3});
		possibleMoves.add(new int[] {3, 5});	 	requiredLevels.add(new int[] {5, 5});
		possibleMoves.add(new int[] {0, 1});		requiredLevels.add(new int[] {2, 9});
		possibleMoves.add(new int[] {0, 1});		requiredLevels.add(new int[] {2, 4});
		possibleMoves.add(new int[] {4, 5});		requiredLevels.add(new int[] {5, 5});
		possibleMoves.add(new int[] {0, 2});		requiredLevels.add(new int[] {5, 6});
		possibleMoves.add(new int[] {0, 2});		requiredLevels.add(new int[] {5, 6});
		
		effectiveMoves = new ArrayList<String[]>();
		superEffectiveMoves = new ArrayList<String[]>();
		notEffectiveMoves = new ArrayList<String[]>();
		noDamageMoves = new ArrayList<String[]>();
		
		effectiveMoves.add(new String[] {"Fire", "Ice", "Flying", "Psychic"});
		effectiveMoves.add(new String[] {"Fire", "Ice", "Flying", "Psychic"});
		effectiveMoves.add(new String[] {"Fire", "Ice", "Flying", "Psychic"});
		effectiveMoves.add(new String[] {"Water", "Ground", "Rock"});
		effectiveMoves.add(new String[] {"Water", "Ground", "Rock"});
		effectiveMoves.add(new String[] {"Water", "Electric"});
		effectiveMoves.add(new String[] {"Electric", "Grass"});
		effectiveMoves.add(new String[] {"Electric", "Grass"});
		effectiveMoves.add(new String[] {"Electric", "Grass"});
		
		superEffectiveMoves.add(new String[] {});
		superEffectiveMoves.add(new String[] {});
		superEffectiveMoves.add(new String[] {}); 	
		superEffectiveMoves.add(new String[] {}); 	
		superEffectiveMoves.add(new String[] {}); 	
		superEffectiveMoves.add(new String[] {"Rock"});
		superEffectiveMoves.add(new String[] {}); 	
		superEffectiveMoves.add(new String[] {}); 	
		superEffectiveMoves.add(new String[] {}); 	
		
		notEffectiveMoves.add(new String[] {"Water", "Electric", "Grass", "Fighting"});
		notEffectiveMoves.add(new String[] {"Water", "Electric", "Grass", "Fighting"});
		notEffectiveMoves.add(new String[] {"Water", "Electric", "Grass", "Fighting"});
		notEffectiveMoves.add(new String[] {"Fire", "Grass", "Ice", "Bug", "Steel"});
		notEffectiveMoves.add(new String[] {"Fire", "Grass", "Ice", "Bug", "Steel"});
		notEffectiveMoves.add(new String[] {"Fire", "Grass", "Fighting", "Ice", "Bug", "Steel"});
		notEffectiveMoves.add(new String[] {"Fire", "Water", "Ice", "Steel"});
		notEffectiveMoves.add(new String[] {"Fire", "Water", "Ice", "Steel"});
		notEffectiveMoves.add(new String[] {"Fire", "Water", "Ice", "Steel"});
		
		noDamageMoves.add(new String[] {});
		noDamageMoves.add(new String[] {});
		noDamageMoves.add(new String[] {});
		noDamageMoves.add(new String[] {});
		noDamageMoves.add(new String[] {});	
		noDamageMoves.add(new String[] {"Ground"});	
		noDamageMoves.add(new String[] {});
		noDamageMoves.add(new String[] {});
		noDamageMoves.add(new String[] {});
		
		//THIS CHUNK IS USELESS SINCE IT ISNT USED
		
		for (int i = 0; i < names.length; i++) {
			Texture sheet = new Texture("Entities/Pokemon/WorldSprites/images/" + names[i] + ".png");
			Animation<TextureRegion> animation = new Animation<TextureRegion>(0.5f, new TextureRegion(sheet, 0, 64, 32, 32), new TextureRegion(sheet, 0, 96, 32, 32));
			pokemonCreatures.add(new PokemonCreature());
			pokemonCreatures.get(i).ID = i;
			pokemonCreatures.get(i).downAnimation = animation;
			pokemonCreatures.get(i).name = names[i];
			pokemonCreatures.get(i).maxHealth = maxHealths[i];
			pokemonCreatures.get(i).currentHealth = currentHealths[i];
			pokemonCreatures.get(i).baseHEA = bHEA[i];
			pokemonCreatures.get(i).generateMoves(possibleMoves.get(i));
			pokemonCreatures.get(i).startMoveset(requiredLevels.get(i));
		}
		
	}
}
