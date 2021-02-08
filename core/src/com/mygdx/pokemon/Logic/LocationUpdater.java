package com.mygdx.pokemon.Logic;

import com.mygdx.pokemon.Screens.PokemonWorld;

public class LocationUpdater {
	boolean[] locationBooleans;

	int arraySkip;

	public LocationUpdater(boolean[] locationBooleans) {
		// TODO Auto-generated constructor stub
		this.locationBooleans = locationBooleans;
	}

	public void updateLocations() {
		//GIVES EACH DOOR A DIFFERENT LOCATION TO GO TO
		if (PokemonWorld.doorNames.booleans[0]) {
			locationBooleans[1] = true;
			arraySkip = 1;
			if (locationBooleans[1]) {
				PokemonWorld.doorNames.booleans[0] = false;
			}
		}
		
		System.out.println(arraySkip);

		if (PokemonWorld.doorNames.booleans[1]) {
			locationBooleans[0] = true;
			arraySkip = 0;
			if (locationBooleans[0]) {
				PokemonWorld.doorNames.booleans[1] = false;
			}
		}

		if (PokemonWorld.doorNames.booleans[2]) {
			locationBooleans[2] = true;
			arraySkip = 2;
			if (locationBooleans[2]) {
				PokemonWorld.doorNames.booleans[2] = false;
			}
		}
		if (PokemonWorld.doorNames.booleans[3]) {
			locationBooleans[1] = true;
			arraySkip = 1;
			if (locationBooleans[1]) {
				PokemonWorld.doorNames.booleans[3] = false;
			}
		}
		if (PokemonWorld.doorNames.booleans[4]) {
			locationBooleans[3] = true;
			arraySkip = 3;
			PokemonWorld.doorNames.booleans[4] = false;
		}
		if (PokemonWorld.doorNames.booleans[5]) {
			locationBooleans[2] = true;
			arraySkip = 2;
			PokemonWorld.doorNames.booleans[5] = false;
		}
		

		for (int i = 0; i < locationBooleans.length; i++) {
			if (i != arraySkip) {
				locationBooleans[i] = false;
			}
		}
	}
}
