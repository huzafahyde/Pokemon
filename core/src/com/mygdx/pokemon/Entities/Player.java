package com.mygdx.pokemon.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pokemon.Logic.Data.AnimationFrames;

public class Player {
	public final float SPEED = 2;
	
	public Sprite sprite;
		
	public static boolean isBoy = true;
	public static boolean isGirl = false;
	
	//WALKING ANIMATIONS
	public Animation<TextureRegion> walkLeft;
	public Animation<TextureRegion> walkRight;
	public Animation<TextureRegion> walkUp;
	public Animation<TextureRegion> walkDown;
	
	//INTERACTION RECTANGLES
	public static Rectangle leftRectangle;
	public static Rectangle upRectangle;
	public static Rectangle rightRectangle;
	public static Rectangle downRectangle;
	
	//DATABASE OF FRAMES
	AnimationFrames frames;
	
	
	public static String name = "Huzafa";
	
	public Player() {
		// TODO Auto-generated constructor stub
		frames = new AnimationFrames();
		
		//SETS ANIMATION BASED ON GENDER
		if (isBoy) {
			sprite = new Sprite(new Texture("Entities/Trainer/Male/Walk/images/Base/walkdown2.png"));
			sprite.setScale(0.25f); 
			walkLeft = new Animation<TextureRegion>(1/6f, frames.MaleWalkLeftFrames);
			walkRight = new Animation<TextureRegion>(1/6f, frames.MaleWalkRightFrames);
			walkUp = new Animation<TextureRegion>(1/6f, frames.MaleWalkUpFrames);
			walkDown = new Animation<TextureRegion>(1/6f, frames.MaleWalkDownFrames);
		} else if (isGirl) {
			sprite = new Sprite(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_02.png"));
			sprite.setScale(0.35f); 
			walkLeft = new Animation<TextureRegion>(1/6f, frames.FemaleWalkLeftFrames);
			walkRight = new Animation<TextureRegion>(1/6f, frames.FemaleWalkRightFrames);
			walkUp = new Animation<TextureRegion>(1/6f, frames.FemaleWalkUpFrames);
			walkDown = new Animation<TextureRegion>(1/6f, frames.FemaleWalkDownFrames);
		} else {
			sprite = new Sprite(new Texture("Entities/Trainer/Male/Walk/images/Base/walkdown2.png"));
			sprite.setScale(0.25f); 
			walkLeft = new Animation<TextureRegion>(1/6f, frames.MaleWalkLeftFrames);
			walkRight = new Animation<TextureRegion>(1/6f, frames.MaleWalkRightFrames);
			walkUp = new Animation<TextureRegion>(1/6f, frames.MaleWalkUpFrames);
			walkDown = new Animation<TextureRegion>(1/6f, frames.MaleWalkDownFrames);
		}
		
		//GENERATES INTERACTION RECTANGLES
		leftRectangle = new Rectangle(sprite.getX() - sprite.getWidth()  + 24, sprite.getY() + 3, sprite.getWidth() - 17, sprite.getHeight() - 20);
		upRectangle = new Rectangle(sprite.getX() + 7, sprite.getY() + sprite.getHeight() - 17, sprite.getWidth() - 17, sprite.getHeight() - 17);
		rightRectangle = new Rectangle(sprite.getX() + sprite.getWidth() - 10, sprite.getY() + 3, sprite.getWidth() - 17, sprite.getHeight() - 20);
		downRectangle = new Rectangle(sprite.getX() + 7, sprite.getY() - 12, sprite.getWidth() - 17, sprite.getHeight() - 17);
		
	}
	
	public void updateRectangles() {
		//ALWAYS UPDATES WHEN MOVING
		leftRectangle = new Rectangle(sprite.getX() - sprite.getWidth()  + 24, sprite.getY() + 3, sprite.getWidth() - 17, sprite.getHeight() - 20);
		upRectangle = new Rectangle(sprite.getX() + 7, sprite.getY() + sprite.getHeight() - 17, sprite.getWidth() - 17, sprite.getHeight() - 17);
		rightRectangle = new Rectangle(sprite.getX() + sprite.getWidth() - 10, sprite.getY() + 3, sprite.getWidth() - 17, sprite.getHeight() - 20);
		downRectangle = new Rectangle(sprite.getX() + 7, sprite.getY() - 12, sprite.getWidth() - 17, sprite.getHeight() - 17);
	}
	
}
