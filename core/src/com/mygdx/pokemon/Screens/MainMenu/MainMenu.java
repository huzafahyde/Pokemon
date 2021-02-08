package com.mygdx.pokemon.Screens.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pokemon.GameClass;
import com.mygdx.pokemon.Screens.Animation.TutorialIntro;

public class MainMenu implements Screen {
	GameClass game;
	
	SpriteBatch batch;
	
	MainHud hud;
	
	Music background;
	
	public static boolean isNewGame = false;

	public MainMenu(GameClass game) {
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	@Override
	public void show() {
		
		
		batch = new SpriteBatch();

		hud = new MainHud(batch);
		
		background = Gdx.audio.newMusic(Gdx.files.internal("Music/Background/TitleTheme.mp3"));
	}

	@Override
	public void render(float delta) {
		//TITLE MENU
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		hud.stage.draw();
		
		if (!background.isLooping()) background.play();
		
		if (isNewGame) {
			background.stop(); 
			game.setScreen(new TutorialIntro(game, batch));
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
	public void dispose () {
		batch.dispose();
	}
}
