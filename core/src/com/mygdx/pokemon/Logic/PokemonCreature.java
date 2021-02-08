package com.mygdx.pokemon.Logic;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.pokemon.Logic.Data.MoveList;

public class PokemonCreature {
	public String name;
	
	public int ID;
	
	//DEFEATED BOOLEAN
	public boolean fainted = false;
	
	public Animation<TextureRegion> upAnimation, rightAnimation, downAnimation, leftAnimation, frontAnimation, backAnimation;
	
	public int maxHealth;
	public int currentHealth;
	
	//RANDOMISER STAT
	public int IV[];
	
	public int level;
	public int baseHEA, baseATT, baseDEF, baseSPA, baseSPD, baseSPE;
	
	public ArrayList<Move> possibleMoves;
	public ArrayList<Integer> requiredLevels;
	
	public static MoveList moveList = new MoveList();
	
	public ArrayList<Move> moveset;
	
	//MOVES THAT DO CERTAIN AMOUNTS OF DAMAGE
	public ArrayList<String> effectiveMoves;
	public ArrayList<String> superEffectiveMoves;
	public ArrayList<String> notEffectiveMoves;
	public ArrayList<String> noDamageMoves;
	
	//NOT IMPLEMENTED YET
	public ArrayList<Integer> totalEXPNeeded;
	public ArrayList<Integer> EXPNeededNextLevel;
	
	public int currentEXPNeeded;
	public int currentEXP;
	
	public int yOffset1, yOffset2;
	
	public PokemonCreature() {
		// TODO Auto-generated constructor stub
		IV = new int[6];
		possibleMoves = new ArrayList<Move>();
		requiredLevels = new ArrayList<Integer>();
		moveset = new ArrayList<Move>();
		
		effectiveMoves = new ArrayList<String>();
		superEffectiveMoves = new ArrayList<String>();
		notEffectiveMoves = new ArrayList<String>();
		noDamageMoves = new ArrayList<String>();
	}
	
	public void generateMoves(int[] moves) {
		for (int i = 0; i < moves.length; i++) {
			possibleMoves.add(moveList.moves.get(moves[i]));
		}
	}
	
	public void startMoveset(int[] requiredLevels) {
		for (int i = 0; i < requiredLevels.length; i++) {
			//IF THEY HAVE THE REQUIRED LEVEL THEN ADD MOVE
			if (level >= requiredLevels[i]) {
				moveset.add(possibleMoves.get(i));
			}
		}
	}
	
	public void generateEffectiveness(String[] effM, String[] supM, String[] nefM, String[] nodM) {
		for (int i = 0; i < effM.length; i++) {
			effectiveMoves.add(effM[i]);
		}
		for (int i = 0; i < supM.length; i++) {
			superEffectiveMoves.add(supM[i]);
		}
		for (int i = 0; i < nefM.length; i++) {
			notEffectiveMoves.add(nefM[i]);
		}
		for (int i = 0; i < nodM.length; i++) {
			noDamageMoves.add(nodM[i]);
		}
	}
	
	public void setEXP() {
		currentEXP = totalEXPNeeded.get(level - 1);
	}
	
	public void updateEXP() {
		currentEXPNeeded = EXPNeededNextLevel.get(level - 1);
	}
}
