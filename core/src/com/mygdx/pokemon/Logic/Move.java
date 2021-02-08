package com.mygdx.pokemon.Logic;

public class Move {
	public int power;
	public int accuracy;
	public int moveID;
	public int PP;
	
	public String type;
	public String name;
	public String contact;
	
	public static Move createMove(int ID, int power, int accuracy, int PP, String type, String name, String contact) {
		Move move = new Move();
		move.moveID = ID;
		move.power = power;
		move.accuracy = accuracy;
		move.PP = PP;
		move.type = type;
		move.name = name;
		move.contact = contact;
		return move;
	}
}
