package com.mygdx.pokemon.Screens.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pokemon.GameClass;

public class TutorialIntro implements Screen, InputProcessor{
	GameClass game;
	
	SpriteBatch sb;
	
	Stage stage;
	Table table;
	
	TextButtonStyle style;
	
	TextButton controlInfo;
	TextButton noHelpNeeded;

	int buttonWidth = 600, buttonHeight = 90;
	
	Skin skin;
	
	BitmapFont font;
	
	TextureAtlas uIAtlas;
	
	boolean controlMenu;
	
	Sprite controls;
	
	private Viewport viewport;
	
	int clicks;
	
	InputMultiplexer inputMultiplexer;
	
	public static Music background;
	
	public TutorialIntro(GameClass game, SpriteBatch sb) {
		// TODO Auto-generated constructor stub
		this.game = game;
		this.sb = sb;
	}
	
	@Override
	public void show() {
		//MAKES A HUD FOR MAIN MENU
		uIAtlas = new TextureAtlas("UI/Main/skin/uiskin.atlas");
		viewport = new FitViewport(1920, 1080, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.setFillParent(true);
		
		skin = new Skin(uIAtlas);
		
		font = new BitmapFont();
		font.getData().setScale(3);
		
		style = new TextButtonStyle();
		style.font = font;
		style.up = skin.getDrawable("default-pane");
		style.down = skin.getDrawable("default-pane-noborder");
		
		controlInfo = new TextButton("Control Info", style);
		controlInfo.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				clicks++;
			}
		});
		
		noHelpNeeded = new TextButton("No Help Needed", style);
		noHelpNeeded.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				System.out.println(2);
				background.stop();
				game.setScreen(new TutorialAnimation(sb, game));
			}
		});
		
		table.add(controlInfo).expandX().width(buttonWidth).height(buttonHeight);
		table.add(noHelpNeeded).expandX().width(buttonWidth).height(buttonHeight);
		
		stage.addActor(table);
		
		inputMultiplexer = new InputMultiplexer(Gdx.input.getInputProcessor());
        inputMultiplexer.addProcessor(this);
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);

		
		controls = new Sprite(new Texture("Intro/SPRITEs/Tutorial/Controls.png"));
		
		background = Gdx.audio.newMusic(Gdx.files.internal("Music/Background/MenuTheme.mp3"));
		
		///////////////////////////////////////////////////////////////////////////
		
	}

	@Override
	public void render(float delta) {
		

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.draw();
		
		if (clicks % 2 == 0) {
			controlMenu = false;
		} else {
			controlMenu = true;
		}
		
		if (!background.isLooping()) {
			background.play();
		}
		
		sb.begin();
		
		if (controlMenu) {
			controls.draw(sb);
		}
		
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
		
		if (clicks % 2 != 0) {
			clicks++;
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
