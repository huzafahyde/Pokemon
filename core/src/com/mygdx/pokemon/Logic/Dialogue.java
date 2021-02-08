package com.mygdx.pokemon.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class Dialogue {

	OrthographicCamera camera;
	
	SpriteBatch sb;

	public float TIMEPERCHAR = 0.1f;

	float timeperchar = 0;
	public int numchars = 0;
	BitmapFont font;

	public String message;
	public String battleMessage;
	String fontMessage;

	Sprite border;

	float fontX = 100, fontY = 160;

	boolean skip = false;
	public boolean done = false;
	public boolean isNextPressed = false;
	
	DialogueHud hud;

	public Dialogue(String message, SpriteBatch sb) {
		this.message = message;
		this.sb = sb;
		
		//MAKES FONT
		font = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"), Gdx.files.internal("Main/pokemonFont.png"),
				false);
		font.getData().setScale(1f);
		
		border = new Sprite(new Texture("UI/Dialogue/DialogueBoxDefault.png"));
		border.setScale(1.5f, 1);
		border.setPosition(185, 130);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1920,1080);
		
		hud = new DialogueHud(this.sb);
		this.sb.setProjectionMatrix(camera.combined);
	}
	
	//SAME CONSTRUCTOR AS BEFORE BUT YOU CAN RESET MESSSAGE AFTER, USED FOR BATTLING
	public Dialogue(SpriteBatch sb) {
		font = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"), Gdx.files.internal("Main/pokemonFont.png"),
				false);
		font.getData().setScale(1f);
		this.sb = sb;
		border = new Sprite(new Texture("UI/Dialogue/DialogueBoxDefault.png"));
		border.setScale(1.5f, 1);
		border.setPosition(185, 130);
		camera = new OrthographicCamera();
		camera.setToOrtho(false,1920,1080);
		this.sb.setProjectionMatrix(camera.combined);
		hud = new DialogueHud(this.sb);
	}
	
	public void set(float scale, float x, float y) {
		border.setScale(scale);
		border.setPosition(x, y);
		font.getData().setScale(scale);
		fontX = (PokemonWorld.area.player.sprite.getX() - border.getWidth() / 2) / 19.2f - 37;;
	}

	public void display() {
		//>>>>>>>>>>>>
		//SAME AS OTHER THREE EXCEPT FOR MINOR DIFFERENCES THAT SOMEHOW NEEDED TO BE MADE
		//>>>>>>>>>>>>
		
		//IF NUMBER OF CHARACTERS IN LESS THAN LENGTH ADD CHARACTER
		if (numchars < message.length()) {
			timeperchar += Gdx.graphics.getDeltaTime();
			if (timeperchar >= TIMEPERCHAR) {
				timeperchar = 0;
				numchars++;
			}
			done = false;
		} else if (isNextPressed) {
			done = true;
		}

		//CHANGES SPEED IF SPACE IS PRESSED
		if (skip) {
			TIMEPERCHAR = 0.0005f;
		} else {
			TIMEPERCHAR = 0.02f;
		}

		//SETS MESSAGE EACH TIME TO SUBSTRING
		if (!done) {
			fontMessage = message.substring(0, numchars);
			hud.updateMessage(fontMessage);
			hud.stage.getViewport().update(1920, 1080, false);
			hud.stage.getCamera().position.x = 1870;
			hud.stage.getCamera().position.y = 1030;
			hud.stage.getCamera().update();
			hud.stage.draw();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			skip = true;
		} else {
			skip = false;
		}

		//LETS YOU READ AND SKIP WHEN READY
		if (Gdx.input.isButtonPressed(Input.Keys.LEFT)) {
			isNextPressed = true;
		} else {
			isNextPressed = false;
		}
	}

	public void displayIntro() {
		border.draw(sb);

		if (numchars < message.length()) {
			timeperchar += Gdx.graphics.getDeltaTime();
			if (timeperchar >= TIMEPERCHAR) {
				timeperchar = 0;
				numchars++;
			}
			done = false;
		} else if (isNextPressed) {
			done = true;
		}

		if (skip) {
			TIMEPERCHAR = 0.0005f;
		} else {
			TIMEPERCHAR = 0.02f;
		}

		if (!done) {
			fontMessage = message.substring(0, numchars);
			font.draw(sb, fontMessage, border.getX() - border.getWidth()/5, font.getCapHeight() + border.getY() + border.getHeight() * 3/5f);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			skip = true;
		} else {
			skip = false;
		}

		if (Gdx.input.isButtonPressed(Input.Keys.LEFT)) {
			isNextPressed = true;
		} else {
			isNextPressed = false;
		}
	}
	
	public void displayBattle(String message) {
		this.battleMessage = message;
		if (numchars < battleMessage.length()) {
			timeperchar += Gdx.graphics.getDeltaTime();
			if (timeperchar >= TIMEPERCHAR) {
				timeperchar = 0;
				numchars++;
			}
			done = false;
		} else if (isNextPressed) {
			done = true;
		}

		if (skip) {
			TIMEPERCHAR = 0.0005f;
		} else {
			TIMEPERCHAR = 0.02f;
		}
		
		if (!done) {
			fontMessage = battleMessage.substring(0, numchars);
			hud.updateMessage(fontMessage);
			hud.stage.getViewport().update(1920, 1080, false);
			hud.stage.getCamera().position.x = 1870;
			hud.stage.getCamera().position.y = 1030;
			hud.stage.getCamera().update();
			hud.stage.draw();
		}

		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			skip = true;
		} else {
			skip = false;
		}

		if (Gdx.input.isButtonPressed(Input.Keys.LEFT)) {
			isNextPressed = true;
		} else {
			isNextPressed = false;
		}
	}
	
	public void resize(float width, float height) {
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.set(width / 2f, height / 2f, 0);
		camera.update();
	}
}
