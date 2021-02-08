package com.mygdx.pokemon.Logic.Data;

import java.util.ArrayList;

import com.mygdx.pokemon.Logic.Move;

public class MoveList {
	public int[] powers;
	public int[] accuracies;
	public int[] moveIDs;
	public int[] powerPoints;

	public String[] types;
	public String[] names;
	public String[] contacts;

	public ArrayList<Move> moves = new ArrayList<Move>();

	public MoveList() {
		powers = new int[] { 60, 40, 110, 40, 35, 0 };

		//NOT USED BUT WILL BE LATER
		accuracies = new int[] { 100, 100, 80, 100, 100, 100 };

		//PRETTY MUCH USELESS
		moveIDs = new int[] { 0, 1, 2, 3, 4, 5};

		//FUTURE FEATURE
		powerPoints = new int[] { 20, 25, 5, 30, 35, 30 };

		types = new String[] { "Flying", "Fire", "Water", "Normal", "Normal", "Normal" };

		names = new String[] { "Aerial Ace", "Ember", "Hydro Pump", "Scratch", "Tackle", "Leer" };

		//WHETHER THEY HIT FOR PHYSICAL DAMAGE OR SPECIAL DAMAGE
		contacts = new String[] { "Physical", "Special", "Special" , "Physical", "Physical", "Special"};

		//ADDS ALL POSSIBLE MOVES
		for (int i = 0; i < names.length; i++) {
			moves.add(Move.createMove(moveIDs[i], powers[i], accuracies[i], powerPoints[i], types[i], names[i],
					contacts[i]));
		}

	}
}
