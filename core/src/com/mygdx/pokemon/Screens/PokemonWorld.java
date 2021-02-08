package com.mygdx.pokemon.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pokemon.GameClass;
import com.mygdx.pokemon.Entities.Player;
import com.mygdx.pokemon.Logic.Area;
import com.mygdx.pokemon.Logic.EventHandler;
import com.mygdx.pokemon.Logic.LocationUpdater;
import com.mygdx.pokemon.Logic.Party;
import com.mygdx.pokemon.Logic.SoundManager;
import com.mygdx.pokemon.Logic.Data.BasePokemonList;
import com.mygdx.pokemon.Logic.Data.DoorNames;
import com.mygdx.pokemon.Logic.Data.EnemyParties;
import com.mygdx.pokemon.Logic.Data.InteractData;
import com.mygdx.pokemon.Logic.Data.NPCList;
import com.mygdx.pokemon.Logic.Data.PokemonList;
import com.mygdx.pokemon.Screens.Animation.PokemonSelectHud;
import com.mygdx.pokemon.Screens.Battle.BattleScreen;
import com.mygdx.pokemon.Screens.Menu.GameMenu;

public class PokemonWorld implements Screen, InputProcessor {
	public static GameClass game;

	public static SpriteBatch sb;
	public static Area area;

	int[] layersAbove;
	int[] layersBelow;

	int countAbove = 0;
	int countBelow = 0;

	int[] trimmedLayersAbove;
	int[] trimmedLayersBelow;

	public static OrthographicCamera camera;

	boolean isBuilding = true;

	public static DoorNames doorNames;
	public static InteractData interactData;

	public LocationUpdater locationUpdater;

	boolean isSwitching = false;
	public static boolean isMenu = false;
	public static boolean isEvent;
	public static boolean isInteraction, isChoosingPokemon, hasChosen = false, hasChanged, isBattling;

	private Viewport viewport;

	float elapsedTime;

	Rectangle tempRect;
	ShapeRenderer renderer;

	public static boolean lookLeft = false;
	public static boolean lookRight = false;
	public static boolean lookUp = false;
	public static boolean lookDown = false;

	public static String direction;

	int clickCount = 0;

	GameMenu menu;

	public InputMultiplexer inputMultiplexer;

	public static NPCList npcList;

	public static EventHandler handler;
	
	int width, height;
	
	Sprite test;
	
	public static Party party;
	
	public static BasePokemonList basePokemonList;
	
	public static EnemyParties enemyParties;
	
	public PokemonList pokemonList;
	
	public static SoundManager soundManager;
	
	Sound menuSound;
	
	public boolean isSound;
	
	PokemonSelectHud hud;
	
	boolean addInput;
	
	public PokemonWorld(GameClass game, SpriteBatch sb) {
		//GAME MANAGEER FOR EXPLORATION AND MOVING
		this.game = game;
		PokemonWorld.sb = sb;
		
		basePokemonList = new BasePokemonList();
		pokemonList = new PokemonList();
		party = new Party();
		
		doorNames = new DoorNames();
		interactData = new InteractData();

		menu = new GameMenu(sb);

		area = new Area(camera, "Maps/TiledMaps/PlayerHouseTopFloor.tmx");
		area.player.sprite.setSize(32, 32);

		isBuilding = true;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		
		layersAbove = new int[20];
		layersBelow = new int[20];

		viewport = new FitViewport(1920, 1080, camera);

		elapsedTime = 0;

		tempRect = new Rectangle();
		renderer = new ShapeRenderer();

		npcList = new NPCList(sb);

		locationUpdater = new LocationUpdater(doorNames.locationBooleans);


		handler = new EventHandler(width, height);

		direction = "";
		
		enemyParties = new EnemyParties();
		
		test = new Sprite(new Texture("badlogic.jpg"));
		
		soundManager = new SoundManager();
		menuSound = Gdx.audio.newSound(Gdx.files.internal("Sounds/MenuOpen.WAV"));
		
		hud = new PokemonSelectHud();
	}
	
	public void updateMenu() {
		menu.pokemonMenu.updateInfo();
	}
	
	public void updateHud() {
		//UPDATES WHICH HUD GETS THE INPUTPROCESSOR
		if (GameMenu.isPokemon) {
			menu.pokemonMenu.stage.draw();
			inputMultiplexer.removeProcessor(menu.stage);
			inputMultiplexer.addProcessor(menu.pokemonMenu.stage);
		}
		if (GameMenu.isMenu) {
			inputMultiplexer.removeProcessor(menu.pokemonMenu.stage);
			inputMultiplexer.addProcessor(menu.stage);
		}
		if (isChoosingPokemon && ! addInput) {
			addInput = true;
			inputMultiplexer.addProcessor(hud.stage);
		}
	}

	@Override
	public void show() {

		inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(menu.stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	public float offset = 0f;
	
	@Override
	public void render(float delta) {
		//RUNS SOUND MANAGER
		soundManager.play();

		System.out.println(SoundManager.soundState);
		//TESTING FOR NOW
		if (Gdx.input.isKeyJustPressed(Input.Keys.T)) {
			isBattling = true;
			game.setScreen(new BattleScreen(game, sb, 1, false));
		}
		
		System.out.println(isBattling);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		sortLayers();
		countRealLayers();

		elapsedTime += delta;
		offset+=delta;
		
		area.tiledMapRenderer.setView(camera);
		
		area.tiledMapRenderer.render(layersBelow);
		
		sb.begin();

		sb.setProjectionMatrix(camera.combined);
		area.tiledMapRenderer.setView(camera);
		area.player.sprite.draw(sb);
		
		sb.end();

		moveCamera();

		area.tiledMapRenderer.render(layersAbove);

		if (!isMenu && !isEvent && !isInteraction) {
			area.pollKeyboard();
		}

		//CHECKING
		area.checkCollision();
		area.checkForDoors(doorNames.names, doorNames.booleans, doorNames.direction);
		area.checkForInteraction(interactData.interactTriggers, interactData.booleans, interactData.directions);
		area.checkForNPCCollision(doorNames.locationBooleans);
		area.checkForNPCInteraction();

		camera.update();

		if (isBuilding) {
			camera.zoom = 0.25f;
		}

		countAbove = 0;
		countBelow = 0;

		if (doorNames.booleans[0]) {
			isSwitching = true;
		}

		//SETS MENU
		if (clickCount % 2 != 0) {
			menu.stage.draw();
			isMenu = true;
		} else {
			isMenu = false;
		}
		update(delta);
		
		menu.act();
		
		for (int i = 0; i < party.party.size(); i++) {
			party.party.get(i).updateEXP();
		}
		
		if (isChoosingPokemon) {
			hud.stage.draw();
		}
		
		hud.stage.act();
		
	}

	public void update(float delta) {
		//ORGANIZATION
		changeLocation();
		updateDirection();
		locationUpdater.updateLocations();
		npcList.update(delta);
		handler.update(delta);
		menu.camera.update();
		area.player.updateRectangles();
		updateHud();
		updateMenu();
		menu.pokemonMenu.increaseTimer(delta);
	}

	@Override
	public void resize(int width, int height) {
		
		this.height = height;
		this.width = width;

		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.set(width / 2f, height / 2f, 0);
		viewport.update(width, height, false);

		menu.camera.viewportWidth = width;
		menu.camera.viewportHeight = height;
		menu.camera.position.set(width / 2f, height / 2f, 0);
		menu.viewport.update(width, height, false);
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

	public void sortLayers() { 
		//SORTS LAYERS TO SEE WHICH ONES SHOULD GO ABOVE AND WHICH ONES SHOULD GO UNDER
		layersAbove = new int[20];
		for (int i = 0; i < area.tiledMapRenderer.getMap().getLayers().getCount(); i++) {
			if (area.tiledMapRenderer.getMap().getLayers().get(i).getProperties().get("Position").equals("Above")) {
				layersAbove[i] = i;
			} else if (area.tiledMapRenderer.getMap().getLayers().get(i).getProperties().get("Position")
					.equals("Below")) {
				layersBelow[i] = i;
			}
		}
	}

	public void countRealLayers() {
		//TRIMS LAYERS
		for (int i = 0; i < layersAbove.length; i++) {
			if (layersAbove[i] != 0) {
				countAbove++;
			}
		}
		for (int i = 0; i < layersBelow.length; i++) {
			if (layersBelow[i] != 0) {
				countBelow++;
			}
		}

		trimmedLayersAbove = new int[countAbove];
		trimmedLayersBelow = new int[countBelow];
		int indexA = 0;
		for (int i = 0; i < layersAbove.length; i++) {
			if (layersAbove[i] != 0) {
				trimmedLayersAbove[indexA++] = i;
			}
		}
		int indexB = 0;
		for (int i = 0; i < layersBelow.length; i++) {
			if (layersBelow[i] != 0) {
				trimmedLayersBelow[indexB++] = i;
			}
		}
	}

	public void changeLocation() {
		//SWITCHES LOCATIONS BASED ON THE DOOR BOOLEANS
		for (int i = 0; i < doorNames.names.length; i++) {
			if (doorNames.booleans[i]) {
				TmxMapLoader.Parameters params = new TmxMapLoader.Parameters();
				params.textureMagFilter = TextureFilter.Nearest;
				params.textureMinFilter = TextureFilter.Nearest;
				TiledMap map = new TmxMapLoader().load(doorNames.fileLocations[i], params);
				area.tiledMapRenderer.getMap().dispose();
				area.tiledMapRenderer.setMap(map);
				area.player.sprite.setPosition(doorNames.initialPositions[i].x, doorNames.initialPositions[i].y);
			}
		}
	}

	public void moveCamera() {
		//FOLLOWS SPRITE
		camera.position.x = (int) Math.round(area.player.sprite.getX() * 100f) / 100f;
		camera.position.y = (int) Math.round(area.player.sprite.getY() * 100f) / 100f;
	}

	public void updateDirection() {
		//PROBABLY SHOULD HAVE USED ENUM FOR THE DIRECTIONS BUT THIS WORKS
		if (area.currentDirection.x == -2) {
			area.player.sprite.setRegion(area.player.walkLeft.getKeyFrame(elapsedTime, true));
			lookLeft = true;
			lookRight = false;
			lookUp = false;
			lookDown = false;
			direction = "Left";
		}

		if (area.currentDirection.x == 2) {
			area.player.sprite.setRegion(area.player.walkRight.getKeyFrame(elapsedTime, true));
			lookLeft = false;
			lookRight = true;
			lookUp = false;
			lookDown = false;
			direction = "Right";
		}

		if (area.currentDirection.y == -2) {
			area.player.sprite.setRegion(area.player.walkDown.getKeyFrame(elapsedTime, true));
			lookLeft = false;
			lookRight = false;
			lookUp = false;
			lookDown = true;
			direction = "Down";
		}

		if (area.currentDirection.y == 2) {
			area.player.sprite.setRegion(area.player.walkUp.getKeyFrame(elapsedTime, true));
			lookLeft = false;
			lookRight = false;
			lookUp = true;
			lookDown = false;
			direction = "Up";
		}

		if (area.currentDirection.x == 0 && lookLeft) {
			if (Player.isBoy) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkleft2.png"));
			} else if (Player.isGirl) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_05.png"));
			}
		}
		if (area.currentDirection.x == 0 && lookRight) {
			if (Player.isBoy) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkright2.png"));
			} else if (Player.isGirl) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_11.png"));
			}
		}
		if (area.currentDirection.y == 0 && lookUp) {
			if (Player.isBoy) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkup2.png"));
			} else if (Player.isGirl) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_08.png"));
			}
		}
		if (area.currentDirection.y == 0 && lookDown) {
			if (Player.isBoy) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkdown2.png"));
			} else if (Player.isGirl) {
				area.player.sprite.setRegion(new Texture("Entities/Trainer/Female/Walk/images/Base/walk_02.png"));
			}
		}

	}

	@Override
	public boolean keyDown(int keycode) {
		//LETS YOU OPEN AND CLOSE THE MENU
		if (keycode == Input.Keys.E && area.currentDirection.x == 0 && area.currentDirection.y == 0 && !isEvent) {
			clickCount++;
		}
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
