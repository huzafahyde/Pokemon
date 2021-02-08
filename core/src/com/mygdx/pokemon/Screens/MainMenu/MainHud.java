package com.mygdx.pokemon.Screens.MainMenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainHud {
	public Stage stage;
	private Viewport viewport;

	Texture logo;
	
	TextButton newGameButton;
	TextButton loadGameButton;
	
	TextButtonStyle textButtonStyle;
	Skin skin;
	
	int buttonWidth = 600, buttonHeight = 90;
	
	BitmapFont font;
	
	TextureAtlas uIAtlas;
	
	Image logoImage;

	public MainHud(SpriteBatch sb) {
		//MAIN 6 BUTTON HUD
		logo = new Texture("UI/Main/Logo.png");
		uIAtlas = new TextureAtlas("UI/Main/skin/uiskin.atlas");
		viewport = new FitViewport(1920, 1080, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		logoImage = new Image(logo);
		
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		skin = new Skin(uIAtlas);
		
		font = new BitmapFont();
		font.getData().setScale(3);
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("default-pane");
		textButtonStyle.down = skin.getDrawable("default-pane-noborder");
		
		newGameButton = new TextButton("New Game", textButtonStyle);
		newGameButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				MainMenu.isNewGame = true;
			}
		});
		
		loadGameButton = new TextButton("Continue", textButtonStyle);
		loadGameButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
			}
		});
		
		table.add(logoImage).height(400).width(900);
		table.row();
		table.add(newGameButton).padTop(10).width(buttonWidth).height(buttonHeight).padLeft(50);
		table.row();
		table.add(loadGameButton).padTop(10).width(buttonWidth).height(buttonHeight).padLeft(50);
		
		stage.addActor(table);
		Gdx.input.setInputProcessor(stage);
	}

}
