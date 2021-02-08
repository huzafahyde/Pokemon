package com.mygdx.pokemon.Logic.Data;

import java.util.ArrayList;

public class EnemyParties {
	public ArrayList<int[]> parties;
	
	public EnemyParties() {
		parties = new ArrayList<int[]>();
		
		//POSSIBLE ENEMY PARTIES WITH THEIR RESPECTIVE POKEMON IDS
		parties.add(new int[] {0});
		parties.add(new int[] {0});

		parties.add(new int[] {0});
		parties.add(new int[] {3});
		parties.add(new int[] {6});
		
	
	}
}
