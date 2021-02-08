package com.mygdx.pokemon.Screens.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pokemon.GameClass;
import com.mygdx.pokemon.Entities.Player;
import com.mygdx.pokemon.Logic.Dialogue;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class TutorialAnimation implements Screen, InputProcessor {
	public SpriteBatch sb;

	GameClass game;

	StringBuilder name;
	String nameLabel;
	String actualName;

	Sprite professor;
	Sprite pokeball;
	Sprite marill;
	Sprite boy;
	Sprite girl;
	Sprite character;
	Sprite fadeout;

	float animationTimer;

	BitmapFont font;

	Dialogue dialogue1;
	Dialogue dialogue2;
	Dialogue dialogue3;
	Dialogue dialogue4;
	Dialogue dialogue5;
	Dialogue dialogue6;
	Dialogue dialogue7;
	Dialogue dialogue8;
	Dialogue dialogue9;
	Dialogue dialogue10;
	Dialogue dialogue11;
	Dialogue dialogue12;
	Dialogue dialogue13;
	Dialogue dialogue14;
	Dialogue dialogue15;
	Dialogue dialogue16;
	Dialogue dialogue17;

	boolean done = true;
	boolean isBoy = false;
	boolean isGirl = false;
	boolean hasChosen = false;
	boolean hasNamed = false;
	boolean isEntering = false;
	boolean isStopped = false;

	float professorAlpha = 0;
	float pokeballAlpha = 0;
	float marillAlpha = 0;
	float fadeoutAlpha = 0;

	float marillTimer = 0;

	Texture marill1, marill2;

	TextureRegion region;

	TextureRegion[] shrinking;

	Animation<TextureRegion> shrinkingAnimation;

	float elapsedTime = 0f;
	
	Music background;
	Music excitingBackground;

	public TutorialAnimation(SpriteBatch sb, GameClass game) {
		// TODO Auto-generated constructor stub
		this.sb = sb;
		this.game = game;
	}

	@Override
	public void show() {

		name = new StringBuilder();

		font = new BitmapFont();
		font.getData().setScale(4f);

		professor = new Sprite(new Texture("Intro/SPRITEs/Tutorial/Sprites/Professor Oak.png"));
		professor.setPosition(Gdx.graphics.getWidth() / 3, Gdx.graphics.getHeight() * (5 / 8f));
		professor.setScale(3);

		professor.setAlpha(professorAlpha);

		pokeball = new Sprite(new Texture("Intro/SPRITEs/Tutorial/Sprites/PokeBall.png"));
		pokeball.setPosition(400, 550);
		pokeball.setScale(2);
		pokeball.setAlpha(pokeballAlpha);

		marill1 = new Texture("Intro/SPRITEs/Tutorial/Sprites/Marill_1.png");
		marill2 = new Texture("Intro/SPRITEs/Tutorial/Sprites/Marill_2.png");

		marill = new Sprite(marill1);
		marill.setPosition(400, 550);
		marill.setScale(2f);

		boy = new Sprite(new Texture("Intro/SPRITEs/Tutorial/Sprites/Male.png"));
		boy.setScale(4);
		boy.setPosition(1200, 500);
		girl = new Sprite(new Texture("Intro/SPRITEs/Tutorial/Sprites/Female.png"));
		girl.setScale(4);
		girl.setPosition(1600, 500);
		character = new Sprite(new Texture("badlogic.jpg"));
		character.setPosition(1400, 500);
		character.setScale(2);
		
		fadeout = new Sprite(new Texture("Intro/SPRITEs/Tutorial/black.jpg"));
		fadeout.setSize(1920, 1080);
		fadeout.setAlpha(fadeoutAlpha);

		dialogue1 = new Dialogue("Hello?", sb);
		dialogue2 = new Dialogue("Who's there?", sb);
		dialogue3 = new Dialogue("Oh! It's just you!", sb);
		dialogue4 = new Dialogue("Welcome to the world of Pokemon!", sb);
		dialogue5 = new Dialogue("My name is Professor Hyde. But you can just call me Huzafa.", sb);
		dialogue6 = new Dialogue("Before we go any further, I want to tell you a few things you should \nknow.", sb);
		dialogue7 = new Dialogue("This world is inhabited by creatures we call 'Pokemon'.", sb);
		dialogue8 = new Dialogue("We humans live alongside Pokemon.", sb);
		dialogue9 = new Dialogue("Sometimes we play together, other times we work together.", sb);
		dialogue10 = new Dialogue("Some people use their Pokemon to battle and develop closer bonds.", sb);
		dialogue11 = new Dialogue("Now tell me a bit about yourself.", sb);
		dialogue12 = new Dialogue("Are you a boy or a girl?", sb);
		dialogue13 = new Dialogue("Please tell me your name: (Less than 8 characters)", sb);
		dialogue14 = new Dialogue(actualName + "! \nAre you ready?", sb);
		dialogue15 = new Dialogue("Your very own tale of grand adventure is about to unfold!", sb);
		dialogue16 = new Dialogue("Fun experiences, difficult experiences, there's so much waiting \nfor you!", sb);
		dialogue17 = new Dialogue("Dreams! Adventure! Let's go to the world of Pokemon!", sb);

		shrinking = new TextureRegion[4];
		shrinkingAnimation = new Animation<TextureRegion>(1f, shrinking);
		
		Gdx.input.setInputProcessor(this);
		
		background = Gdx.audio.newMusic(Gdx.files.internal("Music/Background/MenuTheme.mp3"));
		excitingBackground = Gdx.audio.newMusic(Gdx.files.internal("Music/Background/IntroTheme2.mp3"));
	}

	@Override
	public void render(float delta) {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// dialogue.display("Who's there??", delta, skip);

		animationTimer += delta;

		if (!background.isLooping()) background.play();
		
		sb.begin();

		pokeball.draw(sb);
		professor.draw(sb);

		dialogue1.displayIntro();
		if (dialogue1.done) {
			dialogue2.displayIntro();
		}
		if (dialogue2.done) {
			background.stop();
			if (!excitingBackground.isLooping()) excitingBackground.play();
			if (professorAlpha < 1) {
				professorAlpha += 0.01f;
				professor.setAlpha(professorAlpha);
			} else {
				professorAlpha = 1;
				professor.setAlpha(professorAlpha);
				dialogue3.displayIntro();
			}
		}
		if (dialogue3.done) {
			dialogue4.displayIntro();
		}
		if (dialogue4.done) {
			dialogue5.displayIntro();
		}
		if (dialogue5.done) {
			dialogue6.displayIntro();
		}
		if (dialogue6.done) {
			dialogue7.displayIntro();
			if (pokeballAlpha < 1) {
				pokeballAlpha += 0.01f;
				pokeball.setAlpha(pokeballAlpha);
			} else {
				pokeballAlpha = 1;
				pokeball.setAlpha(pokeballAlpha);
				marill.draw(sb);
				if (marillAlpha < 1) {
					marillAlpha += 0.01f;
					marill.setAlpha(marillAlpha);
				} else {
					marill.setAlpha(1);
				}
			}
		}
		if (dialogue7.done) {
			dialogue8.displayIntro();
			marillTimer += delta;
			if (marillTimer >= 1) {
				marill.setTexture(marill2);
			}
			if (marillTimer >= 1.2f) {
				marillTimer = 0;
				marill.setTexture(marill1);
			}
		}
		if (dialogue8.done) {
			dialogue9.displayIntro();
		}
		if (dialogue9.done) {
			dialogue10.displayIntro();
		}
		if (dialogue10.done) {
			dialogue11.displayIntro();
			marill.setScale(0.00000001f);
			pokeball.setScale(0.00000001f);
			if (professor.getX() > Gdx.graphics.getWidth() / 4 - professor.getWidth()) {
				professor.translateX(-2);
			}
		}
		if (dialogue11.done) {
			dialogue12.displayIntro();
		}
		if (dialogue12.done) {
			boy.draw(sb);
			girl.draw(sb);
		}
		
		//SELECTS GENDER
		if (isBoy) {
			Player.isBoy = true;
			if (boy.getX() < 1200 + 200) {
				boy.translateX(2);
			}
			girl.setScale(0.0001f);
			shrinking[0] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/maleShrinking_1.png"));
			shrinking[1] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/maleShrinking_2.png"));
			shrinking[2] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/maleShrinking_3.png"));
			shrinking[3] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/maleShrinking_4.png"));
		}
		if (isGirl) {
			Player.isGirl = true;
			if (girl.getX() > 1600 - 200) {
				girl.translateX(-2);
			}
			boy.setScale(0.0001f);
			shrinking[0] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/femaleShrinking_1.png"));
			shrinking[1] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/femaleShrinking_2.png"));
			shrinking[2] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/femaleShrinking_3.png"));
			shrinking[3] = new TextureRegion(new Texture("Intro/SPRITEs/Tutorial/Sprites/femaleShrinking_4.png"));

		}
		if (hasChosen) {
			dialogue13.displayIntro();
		}

		nameLabel = "Name: " + name;
		if (dialogue13.done) {
			isEntering = true;
			font.draw(sb, nameLabel, 100, 280);
			if (isStopped) {
				dialogue14.message = name + "! \nAre you ready?";
				dialogue14.displayIntro();

			}
		}
		if (dialogue14.done) {
			dialogue15.displayIntro();
		}
		if (dialogue15.done) {
			dialogue16.displayIntro();
		}
		if (dialogue16.done) {
			dialogue17.displayIntro();
		}
		if (dialogue17.done) {
			boy.setScale(0.000001f);
			girl.setScale(0.000001f);
			elapsedTime += delta;
			character.draw(sb);
			region = shrinkingAnimation.getKeyFrame(elapsedTime, false);
			character.setRegion(region);
			if (fadeoutAlpha< 1) {
				fadeoutAlpha+= 0.01f;
				fadeout.setAlpha(fadeoutAlpha);
			} else {
				fadeoutAlpha = 1;
				fadeout.setAlpha(fadeoutAlpha);
			}
		} 
		if (elapsedTime > 5) {
			Player.name = name.toString();
			excitingBackground.stop();
			game.setScreen(new PokemonWorld(game, sb));
		}
		
		fadeout.draw(sb);
		
		sb.end();

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
		

	}

	@Override
	public boolean keyDown(int keycode) {
		//ALLOWS NAME BACKSPACING
		if (isEntering) {
			if (keycode == Input.Keys.ENTER) {
				isStopped = true;
			}
			if (keycode == Input.Keys.BACKSPACE) {
				name.deleteCharAt(name.length() - 1);
				if (name.length() > 0) {
					name.setLength(name.length() - 1);
				}
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		
		//ALLOWS NAME ENTERING
		if (isEntering && !isStopped) {
			name.append(character);
		}
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if (dialogue12.done) {
			Rectangle boyRect = boy.getBoundingRectangle();
			Rectangle girlRect = girl.getBoundingRectangle();

			if (boyRect.contains(new Vector2(screenX, screenY)) && !isGirl) {
				isBoy = true;
				boy.setAlpha(0.5f);
				hasChosen = true;
			}
			if (girlRect.contains(new Vector2(screenX, screenY)) && !isBoy) {
				isGirl = true;
				girl.setAlpha(0.5f);
				hasChosen = true;
			}
		}

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
