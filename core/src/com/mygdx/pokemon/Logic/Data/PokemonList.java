package com.mygdx.pokemon.Logic.Data;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.pokemon.Logic.PokemonCreature;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class PokemonList {
	public static ArrayList<PokemonCreature> pokemonCreatures;

	//MAIN LIST FOR ALL POSSIBLE POKEMON
	
	public PokemonList() {
		pokemonCreatures = new ArrayList<PokemonCreature>();
	}

	public static PokemonCreature generatePokemon(int ID) {
		PokemonCreature pokemon = new PokemonCreature();
		for (int i = 0; i < 6; i++) {
			pokemon.IV[i] = (int) (Math.random() * 31);
		}
		Texture sheet = new Texture(
				"Entities/Pokemon/WorldSprites/images/" + PokemonWorld.basePokemonList.names[ID] + ".png");
		Animation<TextureRegion> animation = new Animation<TextureRegion>(0.5f, new TextureRegion(sheet, 0, 64, 32, 32),
				new TextureRegion(sheet, 0, 96, 32, 32));
		pokemon.downAnimation = animation;

		sheet = new Texture("Entities/Pokemon/BackSprites/images/" + PokemonWorld.basePokemonList.names[ID] + ".png");
		animation = new Animation<TextureRegion>(1f, new TextureRegion(sheet, 0, 0, 81, 81),
				new TextureRegion(sheet, 81, 0, 81, 81), new TextureRegion(sheet, 0, 0, 81, 81));
		pokemon.backAnimation = animation;

		sheet = new Texture("Entities/Pokemon/FrontSprites/images/" + PokemonWorld.basePokemonList.names[ID] + ".png");
		animation = new Animation<TextureRegion>(0.8f, new TextureRegion(sheet, 0, 0, 81, 81),
				new TextureRegion(sheet, 81, 0, 81, 81), new TextureRegion(sheet, 0, 0, 81, 81));
		pokemon.frontAnimation = animation;

		pokemon.name = PokemonWorld.basePokemonList.names[ID];
		pokemon.level = PokemonWorld.basePokemonList.bLevels[ID];
		pokemon.yOffset1 = PokemonWorld.basePokemonList.yOffsets1[ID];
		pokemon.yOffset2 = PokemonWorld.basePokemonList.yOffsets2[ID];
		pokemon.generateMoves(PokemonWorld.basePokemonList.possibleMoves.get(ID));
		pokemon.startMoveset(PokemonWorld.basePokemonList.requiredLevels.get(ID));

		pokemon.baseHEA = PokemonWorld.basePokemonList.bHEA[ID];
		pokemon.baseATT = PokemonWorld.basePokemonList.bATT[ID];
		pokemon.baseDEF = PokemonWorld.basePokemonList.bDEF[ID];
		pokemon.baseSPA = PokemonWorld.basePokemonList.bSPA[ID];
		pokemon.baseSPD = PokemonWorld.basePokemonList.bSPD[ID];
		pokemon.baseSPE = PokemonWorld.basePokemonList.bSPE[ID];
	
		pokemon.totalEXPNeeded = PokemonWorld.basePokemonList.totalEXPneeded;
		pokemon.EXPNeededNextLevel = PokemonWorld.basePokemonList.EXPneededNextLevel;
		
		pokemon.setEXP();
		
		pokemon.generateEffectiveness(PokemonWorld.basePokemonList.effectiveMoves.get(ID),
				PokemonWorld.basePokemonList.superEffectiveMoves.get(ID),
				PokemonWorld.basePokemonList.notEffectiveMoves.get(ID),
				PokemonWorld.basePokemonList.noDamageMoves.get(ID));
		
		// pokemon.maxHealth = PokemonWorld.basePokemonList.maxHealths[ID];
		// pokemon.currentHealth = PokemonWorld.basePokemonList.currentHealths[ID];
		pokemonCreatures.add(pokemon);
		
		generateStats(ID);
		return pokemon;
	}

	public static void generateStats(int ID) {
		for (int i = 0; i < pokemonCreatures.size(); i++) {
			//int bHEA = PokemonWorld.basePokemonList.bHEA[ID];
			pokemonCreatures
					.get(i).maxHealth = ((((pokemonCreatures.get(i).baseHEA + pokemonCreatures.get(i).IV[0]) * 2) * pokemonCreatures.get(i).level)
							/ 100) + pokemonCreatures.get(i).level + 10;
			pokemonCreatures.get(
					i).currentHealth = ((((pokemonCreatures.get(i).baseHEA + pokemonCreatures.get(i).IV[0]) * 2) * pokemonCreatures.get(i).level)
							/ 100) + pokemonCreatures.get(i).level + 10;
			//GENERATES HEALTH BASED ON BASE STATS
		}
	}
}
