package com.mygdx.pokemon.Screens.Animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pokemon.Logic.AnimatorActor;
import com.mygdx.pokemon.Logic.EventHandler;
import com.mygdx.pokemon.Logic.Data.PokemonList;
import com.mygdx.pokemon.Logic.EventHandler.PokemonChosen;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class PokemonSelectHud {
	public Stage stage;
	
	public Table table;
	
	public PokemonSelectHud() {
		// TODO Auto-generated constructor stub
		Viewport viewport = new FitViewport(1920, 1080);
		stage = new Stage(viewport);
		
		table = new Table();

		BitmapFont font = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"),
				Gdx.files.internal("Main/pokemonFont.png"), false);
		TextureAtlas uIAtlas = new TextureAtlas("UI/Main/skin/uiskin.atlas");
		Skin skin = new Skin(uIAtlas);
		
		TextButtonStyle style = new TextButtonStyle();
		style.font = font;
		style.up = skin.getDrawable("default-pane");
		style.down = skin.getDrawable("default-pane-noborder");
		
		Image pokeball1 = new Image(new Texture("Pokeball/Pokeball.png"));
		pokeball1.setSize(128, 128);
		
		Image pokeball2 = new Image(new Texture("Pokeball/Pokeball.png"));
		pokeball2.setSize(128, 128);
		
		Image pokeball3 = new Image(new Texture("Pokeball/Pokeball.png"));
		pokeball3.setSize(128, 128);
		
		TextButton button1 = new TextButton("", style);
		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				EventHandler.pokemonChosen = PokemonChosen.Bulbasaur;
				System.out.println("Bulbasaur");
			}
		});
		button1.setSize(256, 256);
		
		TextButton button2 = new TextButton("", style);
		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				EventHandler.pokemonChosen = PokemonChosen.Charmander;
				System.out.println("Charmander");
			}
		});
		button2.setSize(256, 256);
		
		TextButton button3 = new TextButton("", style);
		button3.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				EventHandler.pokemonChosen = PokemonChosen.Squirtle;
				System.out.println("Squirtle");
			}
		});
		button3.setSize(256, 256);
		
		WidgetGroup group1 = new WidgetGroup(button1, pokeball1);
		
		WidgetGroup group2 = new WidgetGroup(button2, pokeball2);
		
		WidgetGroup group3 = new WidgetGroup(button3, pokeball3);
		
		AnimatorActor choice1 = new AnimatorActor(PokemonList.generatePokemon(0).frontAnimation, 110, 50, 512, 512);
		
		AnimatorActor choice2 = new AnimatorActor(PokemonList.generatePokemon(3).frontAnimation, 750, 50, 512, 512);
		
		AnimatorActor choice3 = new AnimatorActor(PokemonList.generatePokemon(6).frontAnimation, 1350, 50, 512, 512);
		
		table.setFillParent(true);
		table.center();
		table.add(group1).padRight(600);
		table.add(group2).padRight(200);
		table.add(group3).padLeft(400);
		table.setPosition(-100, 100);
		//table.debug();
		
		Table table2 = new Table();
		table2.center();
		table2.add(choice1);
		table2.add(choice2);
		table2.add(choice3);
		table2.debug();
		
		//stage.setDebugAll(true);
		stage.addActor(table);
		stage.addActor(table2);
	}
}
