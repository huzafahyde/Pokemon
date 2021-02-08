package com.mygdx.pokemon.Logic;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class SoundManager {
	ArrayList<Music> backgroundMusic;
	
	public static SoundState soundState;
	
	public SoundManager() {
		backgroundMusic = new ArrayList<Music>();
		
		backgroundMusic.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Background/HomeTown.mp3")));
		backgroundMusic.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Background/MomTheme.mp3")));
		backgroundMusic.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Background/LabTheme.mp3")));
		backgroundMusic.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Background/Battle.mp3")));
		backgroundMusic.add(Gdx.audio.newMusic(Gdx.files.internal("Music/Background/RivalTheme.mp3")));
		
		soundState = SoundState.None;
	}
	
	public void manageMusic() {
		//SETS SOUNDSTATE TO WHERE THE PLAYER IS
		if (EventHandler.hasRivalStarted) {
			soundState = SoundState.Rival;
		} else
		if (PokemonWorld.isBattling) {
			System.out.println("IUGIHsfgidwgidsgdsioudgs8oydsgdfikkjhds");
			soundState = SoundState.Battle;
		} else
		if (PokemonWorld.handler.isMomEvent) {
			soundState = SoundState.Mom;
		} else 
		if ((PokemonWorld.doorNames.locationBooleans[0] || PokemonWorld.doorNames.locationBooleans[1] || PokemonWorld.doorNames.locationBooleans[2] ) && !EventHandler.hasRivalBattlePassed) {
			soundState = SoundState.HomeTown;
		} else if (PokemonWorld.doorNames.locationBooleans[3]) {
			soundState = SoundState.Lab;
		}
	}
	
	public enum SoundState { 
		None, HomeTown, Mom, Lab, Battle, Rival
	}
	
	public void play() {
		manageMusic();
		//PLAY CERTAIN MUSIC AT CERTAIN TIMES
		if (soundState == SoundState.HomeTown) {
			if (!backgroundMusic.get(0).isLooping()) backgroundMusic.get(0).play();
		} else {
			backgroundMusic.get(0).stop();
		}
		
		if (soundState == SoundState.Mom) {
			if (!backgroundMusic.get(1).isLooping()) backgroundMusic.get(1).play();
		} else {
			backgroundMusic.get(1).stop();
		}
		
		if (soundState == SoundState.Lab) {
			if (!backgroundMusic.get(2).isLooping()) backgroundMusic.get(2).play();
		} else {
			backgroundMusic.get(2).stop();
		}
		if (soundState == SoundState.Battle) {
			if (!backgroundMusic.get(3).isLooping()) backgroundMusic.get(3).play();
		} else {
			backgroundMusic.get(3).stop();
		}
		if (soundState == SoundState.Rival) {
			if (!backgroundMusic.get(4).isLooping()) backgroundMusic.get(4).play();
		} else {
			backgroundMusic.get(4).stop();
		}
		
	}
	
	
}
