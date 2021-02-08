package com.mygdx.pokemon.Logic.Data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationFrames {
	public TextureRegion[] MaleWalkLeftFrames;
	public TextureRegion[] MaleWalkRightFrames;
	public TextureRegion[] MaleWalkDownFrames;
	public TextureRegion[] MaleWalkUpFrames;

	public TextureRegion[] FemaleWalkLeftFrames;
	public TextureRegion[] FemaleWalkRightFrames;
	public TextureRegion[] FemaleWalkDownFrames;
	public TextureRegion[] FemaleWalkUpFrames;

	public TextureRegion[] MomWalkLeftFrames;
	public TextureRegion[] MomWalkRightFrames;
	public TextureRegion[] MomWalkDownFrames;
	public TextureRegion[] MomWalkUpFrames;

	public TextureRegion[] ElmWalkLeftFrames;
	public TextureRegion[] ElmWalkRightFrames;
	public TextureRegion[] ElmWalkDownFrames;
	public TextureRegion[] ElmWalkUpFrames;

	public TextureRegion[][] RivalWalkFrames;

	public AnimationFrames() {
		// PROBABLY AN EASIER WAY TO DO THIS BUT THIS WORKS AND IS SELF EXPLANATORY

		MaleWalkLeftFrames = new TextureRegion[3];
		MaleWalkRightFrames = new TextureRegion[3];
		MaleWalkDownFrames = new TextureRegion[3];
		MaleWalkUpFrames = new TextureRegion[3];

		MaleWalkLeftFrames[0] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkleft1.png"));
		MaleWalkLeftFrames[1] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkleft2.png"));
		MaleWalkLeftFrames[2] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkleft3.png"));

		MaleWalkRightFrames[0] = new TextureRegion(
				new Texture("Entities/Trainer/Male/Walk/images/Base/walkright1.png"));
		MaleWalkRightFrames[1] = new TextureRegion(
				new Texture("Entities/Trainer/Male/Walk/images/Base/walkright2.png"));
		MaleWalkRightFrames[2] = new TextureRegion(
				new Texture("Entities/Trainer/Male/Walk/images/Base/walkright3.png"));

		MaleWalkDownFrames[0] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkdown1.png"));
		MaleWalkDownFrames[1] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkdown2.png"));
		MaleWalkDownFrames[2] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkdown3.png"));

		MaleWalkUpFrames[0] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkup1.png"));
		MaleWalkUpFrames[1] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkup2.png"));
		MaleWalkUpFrames[2] = new TextureRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkup3.png"));
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		FemaleWalkLeftFrames = new TextureRegion[3];
		FemaleWalkRightFrames = new TextureRegion[3];
		FemaleWalkDownFrames = new TextureRegion[3];
		FemaleWalkUpFrames = new TextureRegion[3];

		FemaleWalkLeftFrames[0] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_04.png"));
		FemaleWalkLeftFrames[1] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_05.png"));
		FemaleWalkLeftFrames[2] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_06.png"));

		FemaleWalkRightFrames[0] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_10.png"));
		FemaleWalkRightFrames[1] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_11.png"));
		FemaleWalkRightFrames[2] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_12.png"));

		FemaleWalkDownFrames[0] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_01.png"));
		FemaleWalkDownFrames[1] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_02.png"));
		FemaleWalkDownFrames[2] = new TextureRegion(
				new Texture("Entities/Trainer/Female/Walk/images/Base/walk_03.png"));

		FemaleWalkUpFrames[0] = new TextureRegion(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_07.png"));
		FemaleWalkUpFrames[1] = new TextureRegion(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_08.png"));
		FemaleWalkUpFrames[2] = new TextureRegion(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_09.png"));
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		MomWalkLeftFrames = new TextureRegion[3];
		MomWalkRightFrames = new TextureRegion[3];
		MomWalkDownFrames = new TextureRegion[3];
		MomWalkUpFrames = new TextureRegion[3];

		MomWalkLeftFrames[0] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_04.gif"));
		MomWalkLeftFrames[1] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_07.gif"));
		MomWalkLeftFrames[2] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_10.gif"));

		MomWalkRightFrames[0] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_05.gif"));
		MomWalkRightFrames[1] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_02.gif"));
		MomWalkRightFrames[2] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_08.gif"));

		MomWalkDownFrames[0] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_09.gif"));
		MomWalkDownFrames[1] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_06.gif"));
		MomWalkDownFrames[2] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_12.gif"));

		MomWalkUpFrames[0] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_03.gif"));
		MomWalkUpFrames[1] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_01.png"));
		MomWalkUpFrames[2] = new TextureRegion(new Texture("Entities/NPCs/Mom/images/MOM_11.gif"));
		////////////////////////////////////////////////////////////////////////////////////////////////

		ElmWalkLeftFrames = new TextureRegion[3];
		ElmWalkRightFrames = new TextureRegion[3];
		ElmWalkDownFrames = new TextureRegion[3];
		ElmWalkUpFrames = new TextureRegion[3];

		ElmWalkLeftFrames[0] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_04.png"));
		ElmWalkLeftFrames[1] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_07.png"));
		ElmWalkLeftFrames[2] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_10.png"));

		ElmWalkRightFrames[0] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_05.png"));
		ElmWalkRightFrames[1] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_02.png"));
		ElmWalkRightFrames[2] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_08.png"));

		ElmWalkDownFrames[0] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_09.png"));
		ElmWalkDownFrames[1] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_06.png"));
		ElmWalkDownFrames[2] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_12.png"));

		ElmWalkUpFrames[0] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_03.png"));
		ElmWalkUpFrames[1] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_01.png"));
		ElmWalkUpFrames[2] = new TextureRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_11.png"));

		//////////////////////////////////////////////////////////////////////////////////////////////////

		RivalWalkFrames = new TextureRegion[5][3];

		RivalWalkFrames[0] = new TextureRegion[] {
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_04.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_07.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_10.png")) };
		
		RivalWalkFrames[1] = new TextureRegion[] {
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_05.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_02.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_08.png")) };
		
		RivalWalkFrames[2] = new TextureRegion[] {
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_09.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_06.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_12.png")) };
		
		RivalWalkFrames[3] = new TextureRegion[] {
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_03.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_01.png")),
				new TextureRegion(new Texture("Entities/NPCs/Rival/images/Rival_11.png")) };

	}
}
