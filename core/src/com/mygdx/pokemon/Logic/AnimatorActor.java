package com.mygdx.pokemon.Logic;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class AnimatorActor extends Actor {
	Animation<TextureRegion> animation;
	
	float elapsedTime = 0;
	float x, y, width, height;
	
	TextureRegion region;
	
	//ANIMATION ACTOR NEEDED FOR SCENE2D
	
	public AnimatorActor(Animation<TextureRegion> animation, float x, float y, float width, float height) {
		this.animation = animation;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		elapsedTime += delta;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		region = new TextureRegion(animation.getKeyFrame(elapsedTime, true));
		
		batch.draw(region, x, y, width, height);
	}
}
