package com.mygdx.pokemon.Screens.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.pokemon.GameClass;
import com.mygdx.pokemon.Screens.MainMenu.MainMenu;

public class IntroCutscene implements Screen, InputProcessor{
	SpriteBatch batch;
	
	GameClass game;
	
	boolean skipped = false;
	
	float scale = 0.01f;
	
	Sprite sprite;
	
	TextureAtlas HoOhAtlas;
	TextureRegion textureRegion;
	
	Animation<TextureRegion> animation;
	float elapsedTime = 0f;
	
	Texture background;
	Sprite foregroundTrees;
	Sprite backgroundTrees;
	Sprite sun;
	
	Sprite fadeout;
	
	float startTimer = 0;
	float alpha = 1;
	float fadeAlpha = 0;
	
	Music backgroundMusic;
	
	public IntroCutscene(GameClass game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(this);
		
		sprite = new Sprite(new Texture("badlogic.jpg"));
		HoOhAtlas = new TextureAtlas("Intro/SPRITEs/hooh.pack");
		animation = new Animation<TextureRegion>(1/8f, HoOhAtlas.getRegions());
		
		sprite.setSize(128, 128);
		sprite.setScale(0.01f);
		sprite.setOrigin(0, 0);
		
		background =  new Texture("Intro/SPRITEs/Background.png");;
		
		foregroundTrees = new Sprite(new Texture("Intro/SPRITEs/ForeGround Trees.png"));
		foregroundTrees.setOrigin(0, 0);
		foregroundTrees.setSize(Gdx.graphics.getWidth(), foregroundTrees.getHeight() * 5.5f);
		
		backgroundTrees = new Sprite(new Texture("Intro/SPRITEs/Background Trees.png"));;
		backgroundTrees.setSize(Gdx.graphics.getWidth(), backgroundTrees.getHeight() * 5.5f);
		backgroundTrees.setOrigin(0, 0);
		
		sun = new Sprite(new Texture("Intro/SPRITEs/Sun.png"));;
		sun.setPosition(Gdx.graphics.getWidth()/2 - sun.getWidth() / 2, 200);
		
		sprite.setPosition(sun.getX() + sun.getWidth()/2,475);
		
		fadeout = new Sprite(new Texture("Intro/SPRITEs/White.png"));
		fadeout.setAlpha(0);
		
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Music/Background/IntroTheme.mp3"));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//PLAYS A CUTSCENE
		
		if (startTimer>3.5f && !backgroundMusic.isLooping()) backgroundMusic.play();
		
		textureRegion = animation.getKeyFrame(elapsedTime, true);
		sprite.setRegion(textureRegion);
		
		elapsedTime += Gdx.graphics.getDeltaTime();
		
		batch.begin();

		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		sun.draw(batch);
		
		foregroundTrees.draw(batch);
		backgroundTrees.draw(batch);
		
		sprite.draw(batch);
		
		if (backgroundTrees.getY() >= -100) {
			backgroundTrees.translateY(-2);
			foregroundTrees.translateY(-1);
		}
		
		fadeout.draw(batch);
		
		batch.end();
		//System.out.println(scale);
		startTimer += delta;
		
		if (sun.getY()< 450) {
			sun.translateY(2);
		}
		
		if (startTimer > 4) {
			scale += delta /2;
			sprite.setScale(scale);
		}
		
		if (startTimer > 12) {
			if (alpha >= 0.01) {
				alpha-=0.01f;
			}
			sprite.setAlpha(alpha);
		}
		
		if (startTimer > 13) {
			if (fadeAlpha <= 1f) {
				fadeAlpha += 0.01f;
				fadeout.setAlpha(fadeAlpha);
			}
			if (fadeAlpha >= 1) {
				fadeout.setAlpha(1);
			}
		}
		
		if (startTimer > 16) {
			skipped = true;
		}

		//SETS SCREEN TO NEXT ONE
		if (skipped) {
			backgroundMusic.stop();
			game.setScreen(new MainMenu(game));
		}
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void dispose() {
		
		batch.dispose();
		HoOhAtlas.dispose();
		sprite.getTexture().dispose();
		background.dispose();
		foregroundTrees.getTexture().dispose();
		backgroundTrees.getTexture().dispose();
		sun.getTexture().dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		skipped = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		
		return false;
	}

}
