package com.mygdx.pokemon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.pokemon.GameClass;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new GameClass(), config);
		//config.fullscreen = true;
		config.title = "Pokemon Grey";
		config.width = 1920;
		config.height = 1080;
	}
}
