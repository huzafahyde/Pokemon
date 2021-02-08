package com.mygdx.pokemon.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.pokemon.Entities.Player;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class Area {
	public Player player;

	public boolean blocked = false;
	boolean isMoving = false;

	public TiledMap tiledMap;
	public OrthogonalTiledMapRenderer tiledMapRenderer;

	MapProperties properties;
	int mapWidth, mapHeight, tilePixelWidth, tilePixelHeight, mapPixelWidth, mapPixelHeight;

	public float w, h;

	public Vector2 currentDirection;

	public Area(OrthographicCamera camera, String location) {
		//SETS PLAYER
		
		player = new Player();
		player.sprite.setScale(0.85f);
		player.sprite.setPosition(80f, 18f);

		currentDirection = new Vector2(0, 0);

		tiledMap = new TmxMapLoader().load(location);

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		properties = new MapProperties();
		properties = tiledMap.getProperties();

		mapWidth = properties.get("width", Integer.class);
		mapHeight = properties.get("height", Integer.class);
		tilePixelWidth = properties.get("tilewidth", Integer.class);
		tilePixelHeight = properties.get("tileheight", Integer.class);

		mapPixelWidth = mapWidth * tilePixelWidth;
		mapPixelHeight = mapHeight * tilePixelHeight;

	}

	public void pollKeyboard() {
		//MOVES PLAYER
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.sprite.translateY(player.SPEED);
			currentDirection.y = player.SPEED;
		} else if (!Gdx.input.isKeyPressed(Input.Keys.DOWN) && !Gdx.input.isKeyPressed(Input.Keys.S)) {
			currentDirection.y = 0;
		}

		//////////////////////////////////////////////////////

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.sprite.translateX(-player.SPEED);

			currentDirection.x = -player.SPEED;
		} else if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.D)) {
			currentDirection.x = 0;
		}

		//////////////////////////////////////////////////////

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.sprite.translateY(-player.SPEED);

			currentDirection.y = -player.SPEED;
		} else if (!Gdx.input.isKeyPressed(Input.Keys.UP) && !Gdx.input.isKeyPressed(Input.Keys.W)) {
			currentDirection.y = 0;
		}

		//////////////////////////////////////////////////////

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.sprite.translateX(player.SPEED);

			currentDirection.x = player.SPEED;
		} else if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.A)) {
			currentDirection.x = 0;
		}

		//////////////////////////////////////////////////////

		//BOOLEANS DONT DO ANYTHING BUT ARE THERE FOR FUTURE REFERENCE
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.LEFT)
				|| Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			isMoving = true;
		} else {
			isMoving = false;
		}
	}

	public void checkCollision() {
		//IF IT COLLIDES THEN IT PUSHES THE PLAYER BACK 
		MapObjects objects = tiledMapRenderer.getMap().getLayers().get("Collision").getObjects();
		if (objects != null) {
			for (int i = 0; i < objects.getCount(); i++) {
				MapObject object = objects.get(i);
				if (object instanceof RectangleMapObject) {
					Rectangle rect = ((RectangleMapObject) object).getRectangle();
					Rectangle playerRect = player.sprite.getBoundingRectangle();
					playerRect.x = player.sprite.getX() + 7;
					playerRect.y = player.sprite.getY() + 3;
					playerRect.width = player.sprite.getWidth() - 17;
					playerRect.height = player.sprite.getHeight() - 20;
					if (playerRect.overlaps(rect)) {
						System.out.println("HIT");
						blocked = true;
						player.sprite.translate(-currentDirection.x, -currentDirection.y);
					} else {
						blocked = false;
					}
				}
			}
		}
	}

	public void checkForDoors(String[] doorNames, boolean[] doorBooleans, Vector2[] directions) {
		//IF PLAYER IS IN DOOR AND MOVING RIGHT WAY THEN TRAVEL
		MapObjects objects = tiledMapRenderer.getMap().getLayers().get("Doors").getObjects();
		if (objects != null) {
			for (int i = 0; i < objects.getCount(); i++) {
				MapObject object = objects.get(i);
				if (object instanceof RectangleMapObject) {
					Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
					Rectangle playerRect = player.sprite.getBoundingRectangle();
					playerRect.x = player.sprite.getX() + 7;
					playerRect.y = player.sprite.getY() + 3;
					playerRect.width = player.sprite.getWidth() - 17;
					playerRect.height = player.sprite.getHeight() - 20;
					for (int j = 0; j < doorNames.length; j++) {
						if (playerRect.overlaps(rectangle) && object.getProperties().get("Door").equals(doorNames[j])
								&& currentDirection.equals(directions[j])) {
							doorBooleans[j] = true;
							//System.err.println(doorBooleans[j]);
						}
					}
				}
			}
		}
	}

	public void checkForInteraction(String[] interactNames, boolean[] booleans, String[] directions) {
		//IF THE PLAYER IS IN INTERACTION ZONE AND FACING RIGHT WAY
		MapObjects objects = tiledMapRenderer.getMap().getLayers().get("Interact").getObjects();
		if (objects != null) {
			for (int i = 0; i < objects.getCount(); i++) {
				MapObject object = objects.get(i);
				if (object instanceof RectangleMapObject) {
					Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
					Rectangle playerRect = player.sprite.getBoundingRectangle();
					playerRect.x = player.sprite.getX() + 7;
					playerRect.y = player.sprite.getY() + 3;
					playerRect.width = player.sprite.getWidth() - 17;
					playerRect.height = player.sprite.getHeight() - 20;
					for (int j = 0; j < interactNames.length; j++) {
						if (playerRect.overlaps(rectangle)
								&& object.getProperties().get("Interact").equals(interactNames[j])
								&& PokemonWorld.direction.equals(directions[j])
								&& Gdx.input.isKeyPressed(Input.Keys.C)) {
							// System.err.println(interactNames[j]);
							booleans[j] = true;
							// System.err.println(booleans[j]);
						} else {
							// booleans[j] = false;
						}
					}
				}
			}
		}
	}

	public void checkForNPCCollision(boolean[] locationBooleans) {
		//COLLIDES WITH NPCS
		if (locationBooleans[1]) {
			Rectangle playerRect = player.sprite.getBoundingRectangle();
			playerRect.x = player.sprite.getX() + 7;
			playerRect.y = player.sprite.getY() + 3;
			playerRect.width = player.sprite.getWidth() - 17;
			playerRect.height = player.sprite.getHeight() - 20;
			Rectangle momRect = PokemonWorld.npcList.mom.sprite.getBoundingRectangle();
			momRect.x = PokemonWorld.npcList.mom.sprite.getX() + 7;
			momRect.y = PokemonWorld.npcList.mom.sprite.getY() + 3;
			momRect.width = PokemonWorld.npcList.mom.sprite.getWidth() - 17;
			momRect.height = PokemonWorld.npcList.mom.sprite.getHeight() - 20;
			if (playerRect.overlaps(momRect)) {
				System.out.println("HIT");
				blocked = true;
				player.sprite.translate(-currentDirection.x, -currentDirection.y);
			} else {
				blocked = false;
			}
		}
		if (locationBooleans[3]) {
			Rectangle playerRect = player.sprite.getBoundingRectangle();
			playerRect.x = player.sprite.getX() + 7;
			playerRect.y = player.sprite.getY() + 3;
			playerRect.width = player.sprite.getWidth() - 17;
			playerRect.height = player.sprite.getHeight() - 20;
			Rectangle profRect = PokemonWorld.npcList.mom.sprite.getBoundingRectangle();
			profRect.x = PokemonWorld.npcList.professor.sprite.getX() + 7;
			profRect.y = PokemonWorld.npcList.professor.sprite.getY() + 3;
			profRect.width = PokemonWorld.npcList.professor.sprite.getWidth() - 17;
			profRect.height = PokemonWorld.npcList.professor.sprite.getHeight() - 20;
			if (playerRect.overlaps(profRect)) {
				System.out.println("HIT");
				blocked = true;
				player.sprite.translate(-currentDirection.x, -currentDirection.y);
			} else {
				blocked = false;
			}
		}
	}

	public void checkForNPCInteraction() {
		//ONLY MANAGES FOR MOMS DIALOGUE 
		//SHOULD BE IN DIFFERENT CLASS
		if (PokemonWorld.doorNames.locationBooleans[1] && Gdx.input.isKeyPressed(Input.Keys.V)) {
			if (Player.upRectangle.overlaps(PokemonWorld.npcList.mom.rectangle)) {
				EventHandler.interactDialogue.dialogues.get(0).display();
				PokemonWorld.npcList.mom.sprite.setRegion((new Texture("Entities/NPCs/Mom/images/MOM_06.gif")));
			}if (Player.leftRectangle.overlaps(PokemonWorld.npcList.mom.rectangle)) {
				EventHandler.interactDialogue.dialogues.get(0).display();
				PokemonWorld.npcList.mom.sprite.setRegion((new Texture("Entities/NPCs/Mom/images/MOM_02.gif")));
			}if (Player.downRectangle.overlaps(PokemonWorld.npcList.mom.rectangle)) {
				EventHandler.interactDialogue.dialogues.get(0).display();
				PokemonWorld.npcList.mom.sprite.setRegion((new Texture("Entities/NPCs/Mom/images/MOM_01.png")));
			}if (Player.rightRectangle.overlaps(PokemonWorld.npcList.mom.rectangle)) {
				EventHandler.interactDialogue.dialogues.get(0).display();
				PokemonWorld.npcList.mom.sprite.setRegion((new Texture("Entities/NPCs/Mom/images/MOM_07.gif")));
			}
		}
	}
}
