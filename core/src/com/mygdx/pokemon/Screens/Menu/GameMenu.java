package com.mygdx.pokemon.Screens.Menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.mygdx.pokemon.Screens.PokemonWorld;
import com.mygdx.pokemon.Screens.Menu.SubMenu.PokemonMenu;

public class GameMenu {
	public Stage stage;
	Skin skin;
	
	public OrthographicCamera camera;
	public Viewport viewport;
	
	TextureAtlas uIAtlas;
	TextButtonStyle style;
	BitmapFont font;
	
	TextButton pokemon;
	TextButton pokeDex;
	TextButton bag;
	TextButton options;
	TextButton card;
	TextButton save;
	
	public static boolean isMenu;
	public static boolean isPokemon;
	
	public PokemonMenu pokemonMenu; 
	
	SpriteBatch sb;
	
	public GameMenu(SpriteBatch sb) {
		this.sb = sb;
		//MAIN 6 BUTTON MENU
		
		pokemonMenu = new PokemonMenu(this.sb);
		
		uIAtlas = new TextureAtlas("UI/Main/skin/uiskin.atlas");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		viewport = new FitViewport(1920, 1080);
		stage = new Stage(viewport, this.sb);
		
		Table table = new Table();
		table.right();
		table.setFillParent(true);
		
		skin = new Skin(uIAtlas);
		
		font = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"),Gdx.files.internal("Main/pokemonFont.png"),false);
		style = new TextButtonStyle();
		style.font = font;
		style.up = skin.getDrawable("default-pane");
		style.down = skin.getDrawable("default-pane-noborder");
	
		pokemon = new TextButton("Pokemon", style);
		pokemon.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.isMenu) {
					isMenu = false;
					isPokemon = true;
				}
			}
		});
		
		pokeDex = new TextButton("PokeDex", style);
		pokeDex.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.isMenu) {
				}
			}
		});
		
		bag = new TextButton("Bag", style);
		bag.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.isMenu) {
				}
			}
		});
		
		options = new TextButton("Options", style);
		options.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.isMenu) {
				}
			}
		});
		
		card = new TextButton("Card", style);
		card.addListener(new ChangeListener() {
	
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.isMenu) {
				}
			}
		});
		
		save = new TextButton("Save", style);
		save.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.isMenu) {
				}
			}
		});
		
		table.add(pokemon).expandY().height(Gdx.graphics.getHeight()/6).width(200);
		table.row();
		table.add(pokeDex).expandY().height(Gdx.graphics.getHeight()/6).width(200);
		table.row();
		table.add(bag).expandY().height(Gdx.graphics.getHeight()/6).width(200);
		table.row();
		table.add(options).expandY().height(Gdx.graphics.getHeight()/6).width(200);
		table.row();
		table.add(card).expandY().height(Gdx.graphics.getHeight()/6).width(200);
		table.row();
		table.add(save).expandY().height(Gdx.graphics.getHeight()/6).width(200);
		table.row();
		
		stage.addActor(table);
		
		
	}
	
	public void act() {
		stage.act();
		pokemonMenu.stage.act();
	}
	
}
