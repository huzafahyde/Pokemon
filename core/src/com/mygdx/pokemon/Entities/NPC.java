package com.mygdx.pokemon.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class NPC {
	/*
	 * THIS CLASS DEFINES THE NON PLAYER CHARACTERS IN THE GAME.
	 */

	public Sprite sprite;

	// THE NUMBER OF LOCATIONS THEY CAN APPEAR IN
	int[] locations;

	SpriteBatch batch;

	// DIFFERENT ANIMATIONS
	public Animation<TextureRegion> walkLeft;
	public Animation<TextureRegion> walkRight;
	public Animation<TextureRegion> walkUp;
	public Animation<TextureRegion> walkDown;

	// COLLISION
	public Rectangle rectangle;
	
	public boolean isGone = false;

	// TIMER FOR ANIMATION
	float elapsedTime = 0;
	
	public void setIsGone(boolean isGone) {
		this.isGone = isGone;
	}

	public NPC(int[] locations, String file, SpriteBatch batch, TextureRegion[] left, TextureRegion[] right,
			TextureRegion[] up, TextureRegion[] down) {
		// DEFINES NPC
		sprite = new Sprite(new Texture(file));

		this.batch = batch;
		this.locations = locations;

		walkLeft = new Animation<TextureRegion>(1 / 6f, left);
		walkRight = new Animation<TextureRegion>(1 / 6f, right);
		walkUp = new Animation<TextureRegion>(1 / 6f, up);
		walkDown = new Animation<TextureRegion>(1 / 6f, down);

		rectangle = new Rectangle();
	}

	public void update(float delta) {
		batch.begin();

		// ONLY DRAWS ON CERTAIN LOCATIONS
		for (int i = 0; i < locations.length; i++) {
			if (PokemonWorld.doorNames.locationBooleans[locations[i]] && !isGone) {
				sprite.draw(batch);
			}
		}

		batch.end();

		// INCREASE TIMER
		elapsedTime += delta;

		// DRAW MORE PRECISE RECTANGLE
		rectangle.x = sprite.getX() + 7;
		rectangle.y = sprite.getY() + 3;
		rectangle.width = sprite.getWidth() - 17;
		rectangle.height = sprite.getHeight() - 20;
	}

	// METHODS FOR MOVING AROUND AND SETTING ANIMATIONS

	public void moveLeft(float x) {
		sprite.translateX(-x);
		sprite.setRegion(walkLeft.getKeyFrame(elapsedTime, true));
	}

	public void moveUp(float y) {
		sprite.translateY(y);
		sprite.setRegion(walkUp.getKeyFrame(elapsedTime, true));
	}

	public void moveRight(float x) {
		sprite.translateX(x);
		sprite.setRegion(walkRight.getKeyFrame(elapsedTime, true));
	}

	public void moveDown(float y) {
		sprite.translateY(-y);
		sprite.setRegion(walkDown.getKeyFrame(elapsedTime, true));
	}
}
