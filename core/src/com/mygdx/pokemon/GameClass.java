package com.mygdx.pokemon;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.pokemon.Screens.PokemonWorld;
import com.mygdx.pokemon.Screens.Animation.IntroCutscene;

public class GameClass extends Game {
	public SpriteBatch batch;

	public static PokemonWorld pokemonWorld;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		pokemonWorld = new PokemonWorld(this, batch);
		
		setScreen(new IntroCutscene(this));
		//this.setScreen(pokemonWorld);
		//setScreen(new BattleScreen(this, batch));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
