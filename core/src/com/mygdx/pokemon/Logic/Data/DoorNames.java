package com.mygdx.pokemon.Logic.Data;

import com.badlogic.gdx.math.Vector2;

public class DoorNames {
	public String[] names;
	public boolean[] booleans;
	public boolean[] locationBooleans;
	public String [] fileLocations;
	public Vector2[] direction;
	public Vector2[] initialPositions;
	public float[] zoom;
	
	public DoorNames() {
		//TRIGGERS FOR DOORS
		names = new String[30];
		names[0] = "toPlayerHouseBottomFloor";
		names[1] = "toPlayerHouseTopFloor";
		names[2] = "toPlayerHomeTown";
		names[3] = "toPlayerHouse";
		names[4] = "toProfessorLabFront";
		names[5] = "toHomeTownFromLab";

		//IF TOUCHING DOOR BOOLEANS
		booleans = new boolean[30];
		for (int i = 0; i < booleans.length; i++) {
			booleans[i] = false;
		}
		
		//DIFFERENT LOCATION BOOLEANS
		locationBooleans = new boolean[30];
		for (int i = 0; i < locationBooleans.length; i++) {
			locationBooleans[i] = false;
		}
		locationBooleans[0] = true;
		
		//MAP FILE LOCATIONS
		fileLocations = new String[30];
		for (int i = 0; i < fileLocations.length; i++) {
			fileLocations[i] = "Maps/TiledMaps/";
		}
		fileLocations[0] += "PlayerHouseBottomFloor.tmx";
		fileLocations[1] += "PlayerHouseTopFloor.tmx";
		fileLocations[2] += "PlayerHomeTown.tmx";
		fileLocations[3] += "PlayerHouseBottomFloor.tmx";
		fileLocations[4] += "Professor.tmx";
		fileLocations[5] += "PlayerHomeTown.tmx";
		
		//DIRECTION NEEDED TO ENTER
		direction = new Vector2[30];
		direction[0] = new Vector2(-2,0);
		direction[1] = new Vector2(-2,0);
		direction[2] = new Vector2(0,-2);
		direction[3] = new Vector2(0,2);
		direction[4] = new Vector2(0,2);
		direction[5] = new Vector2(0,-2);
		
		//INTIALPOSITIONS WHERE SPRITE SHOULD BE WHEN ENTERING
		initialPositions = new Vector2[30];
		initialPositions[0] =  new Vector2(50f, 150f);
		initialPositions[1] = new Vector2(70f, 150f);
		initialPositions[2] = new Vector2(150, 780f);
		initialPositions[3] =  new Vector2(50f, 20f);
		initialPositions[4] =  new Vector2(75, 20f);
		initialPositions[5] =  new Vector2(710, 770);
		
		//USELESS FOR NOW
		zoom = new float[30];
		zoom[0] = 0.15f;
		zoom[1] = 0.15f;
		zoom[2] = 0.15f;
		zoom[3] = 0.15f;
		zoom[4] = 0.15f;
		zoom[5] = 0.15f;
		zoom[6] = 0.15f;
	}
	
	
}
