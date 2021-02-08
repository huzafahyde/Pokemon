package com.mygdx.pokemon.Logic.Data;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pokemon.Entities.NPC;

public class NPCList {
	public ArrayList<NPC> npcs;
	
	public NPC mom;
	public NPC professor;
	public NPC rival;

	AnimationFrames frames;

	public NPCList(SpriteBatch batch) {
		//ALL POSSIBLE NPCS
		
		npcs = new ArrayList<NPC>();
		
		frames = new AnimationFrames();
		mom = new NPC(new int[] { 1 }, "Entities/NPCs/Mom/images/MOM_02.gif", batch, frames.MomWalkLeftFrames,
				frames.MomWalkRightFrames, frames.MomWalkUpFrames, frames.MomWalkDownFrames);
		mom.sprite.setPosition(120, 90);
		mom.sprite.setScale(1.15f);
		npcs.add(mom);

		professor = new NPC(new int[] { 3 }, "Entities/NPCs/ProfessorElm/images/Professor_01.png", batch, frames.ElmWalkLeftFrames,
				frames.ElmWalkRightFrames, frames.ElmWalkUpFrames, frames.ElmWalkDownFrames);
		professor.sprite.setPosition(448, 296);
		professor.sprite.setScale(1.25f);
		npcs.add(professor);
		
		rival = new NPC(new int[] {2}, "Entities/NPCs/Rival/images/Rival_02.png", batch, frames.RivalWalkFrames[0], frames.RivalWalkFrames[1], frames.RivalWalkFrames[2], frames.RivalWalkFrames[3]);
		rival.sprite.setScale(1.25f);
		rival.sprite.setPosition(150, 780);
		npcs.add(rival);

	}

	public void update(float delta) {
		//METHOD THAT CALLS ON OTHER METHOD FOR SOME REASON
		mom.update(delta);
		professor.update(delta);
		rival.update(delta);
	}
}
