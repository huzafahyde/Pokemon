package com.mygdx.pokemon.Screens.Battle;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pokemon.GameClass;
import com.mygdx.pokemon.Logic.Dialogue;
import com.mygdx.pokemon.Logic.EventHandler;
import com.mygdx.pokemon.Logic.PokemonCreature;
import com.mygdx.pokemon.Logic.Data.PokemonList;
import com.mygdx.pokemon.Logic.SoundManager.SoundState;
import com.mygdx.pokemon.Screens.PokemonWorld;

public class BattleScreen implements Screen, InputProcessor {
	GameClass game;
	SpriteBatch sb;

	Sprite bottomBase, topBase;

	BattleHud hud;

	Sprite player, enemy;

	OrthographicCamera camera;
	Viewport viewport;

	ShapeRenderer temp;

	float elapsedTime = 0;

	int ID;

	public ArrayList<PokemonCreature> enemyParty;

	PokemonCreature playerCreature;
	PokemonCreature enemyCreature;

	public static AttackState attackState;

	public static AttackState enemyState;

	int turns;
	int currentTurn = 1;

	public static int currentPlayerID = 0, currentEnemyID = 0;
	public int playerPartySize, enemyPartySize;

	boolean isAttacking = false;
	boolean isTrainer;
	boolean isPlayerTurn;
	boolean isEnemyTurn;
	boolean enemyAttacking;
	boolean isDefeated;

	Dialogue moveDialogue;
	Dialogue enemyDialogue;

	int move = 0;

	InputMultiplexer inputMultiplexer = new InputMultiplexer();

	public BattleScreen(GameClass game, SpriteBatch sb, int ID, boolean isTrainer) {
		this.game = game;
		this.sb = sb;
		this.ID = ID;
		this.isTrainer = isTrainer;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera(1920, 1080);
		camera.setToOrtho(false);
		viewport = new FitViewport(1920, 1080, camera);

		bottomBase = new Sprite(new Texture("Battle/Base/Player/Default.png"));
		bottomBase.setScale(5);
		bottomBase.setPosition(0, 290);

		topBase = new Sprite(new Texture("Battle/Base/Enemy/Default.png"));
		topBase.setScale(4);
		topBase.setPosition(Gdx.graphics.getWidth() / 2 - topBase.getWidth(), Gdx.graphics.getHeight() * 3 / 5f);

		player = new Sprite(new Texture("badlogic.jpg"));
		player.setSize(256, 256);
		player.setPosition(bottomBase.getX() + bottomBase.getWidth() / 2,
				bottomBase.getY() - bottomBase.getHeight() - 30);
		enemy = new Sprite(new Texture("badlogic.jpg"));
		enemy.setSize(256, 256);
		enemy.setPosition(topBase.getX() - topBase.getWidth() / 2, topBase.getY());

		temp = new ShapeRenderer();

		enemyParty = new ArrayList<PokemonCreature>();

		hud = new BattleHud(sb, viewport);

		setEnemy();
		inputMultiplexer.addProcessor(this);
		inputMultiplexer.addProcessor(hud.stage);
		Gdx.input.setInputProcessor(inputMultiplexer);

		attackState = AttackState.NONE;
		enemyState = AttackState.NONE;

		moveDialogue = new Dialogue(sb);
		enemyDialogue = new Dialogue(sb);
	}

	public void animate() {
		//ANIMATES BOTH PLAYERS
		bottomBase.draw(sb);
		topBase.draw(sb);

		player.draw(sb);
		player.setRegion(PokemonWorld.party.party.get(currentPlayerID).backAnimation.getKeyFrame(elapsedTime, true));
		player.setPosition(bottomBase.getX() + bottomBase.getWidth() / 2, bottomBase.getY() - bottomBase.getHeight()
				- 30 + PokemonWorld.party.party.get(currentPlayerID).yOffset2);

		enemy.draw(sb);
		enemy.setRegion(enemyParty.get(currentEnemyID).frontAnimation.getKeyFrame(elapsedTime, true));
		enemy.setPosition(topBase.getX() - topBase.getWidth() / 2,
				topBase.getY() + enemyParty.get(currentEnemyID).yOffset1);
	}

	public void faint(PokemonCreature pokemon) {
		//SWITCHES POKEMON
		if (pokemon.equals(PokemonWorld.party.party.get(currentPlayerID))) {
			if (currentPlayerID < playerPartySize-1) {
				currentPlayerID++;
			} else {
				System.out.println(5);
				game.setScreen(GameClass.pokemonWorld);
			}
		} else {
			if (currentEnemyID < enemyPartySize-1) {
				currentEnemyID++;
			} else {
				System.out.println(5);
				exitWin();
			}
		}
	}

	//EXP SYSTEM NEEDED
	
	public void exitWin() {
		//EXIT SCREEN
		PokemonWorld.isBattling = false;
		game.setScreen(GameClass.pokemonWorld);
	}
	
	@Override
	public void render(float delta) {
		// System.out.println(attackState);

		//System.out.println(currentEnemyID + ", " + enemyPartySize);
	PokemonWorld.soundManager.soundState = SoundState.Battle;
		System.out.println(PokemonWorld.soundManager.soundState);
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		elapsedTime += delta;
		
		playerCreature = PokemonWorld.party.party.get(currentPlayerID);
		playerPartySize = PokemonWorld.party.party.size();

		hud.stage.draw();
		sb.setProjectionMatrix(camera.combined);
		sb.begin();

		animate();

		sb.end();

		enemyCreature = enemyParty.get(currentEnemyID);
		hud.update(enemyCreature);
		// hud.update(playerCreature);

		hud.setMoveNames();

		manageTurns();
		PokemonWorld.soundManager.play();

		// System.err.println(attackState);
	}

	public enum AttackState {
		Attack1, Attack2, Attack3, Attack4, NONE, OTHER
	}

	public void setEnemy() {
		for (int i = 0; i < PokemonWorld.enemyParties.parties.get(ID).length; i++) {
			enemyParty.add(PokemonList.generatePokemon(PokemonWorld.enemyParties.parties.get(ID)[i]));
		}
		enemyPartySize = enemyParty.size();
	}

	public void manageTurns() {
		//MANAGES TURNSTATES
		if ((attackState != AttackState.NONE && !isAttacking) || isEnemyTurn) {
			currentTurn++;
			if (!playerCreature.fainted && (isPlayerTurn || !isEnemyTurn)) {
				playerMove();
			}
			if (isEnemyTurn) {
				enemyState = AttackState.OTHER;
				if (enemyAttacking) {
					move = generateMove(enemyCreature);
					enemyAttacking = false;
				}
				enemyMove(move);
			}
			if (attackState == AttackState.NONE && enemyState == AttackState.NONE) {
				isAttacking = false;
				moveDialogue.done = false;
				moveDialogue.isNextPressed = false;
				moveDialogue.numchars = 0;
				enemyDialogue.done = false;
				enemyDialogue.isNextPressed = false;
				enemyDialogue.numchars = 0;
			}
			Gdx.input.setInputProcessor(null);
		} else {
			Gdx.input.setInputProcessor(inputMultiplexer);
		}

		if (playerCreature.currentHealth <= 0) {
			playerCreature.fainted = true;
			PokemonWorld.party.party.get(currentPlayerID).fainted = true;
			faint(playerCreature);
		}

		if (enemyCreature.currentHealth <= 0) {
			enemyCreature.fainted = true;
			enemyParty.get(currentEnemyID).fainted = true;
			faint(enemyCreature);
		}

	}

	public boolean playerMove() {
		//PLAYER ATTACK
		String moveName = "";
		switch (attackState) {
		case Attack1:
			moveName = playerCreature.moveset.get(0).name;
			break;
		case Attack2:
			moveName = playerCreature.moveset.get(1).name;
			break;
		case Attack3:
			moveName = playerCreature.moveset.get(2).name;
			break;
		case Attack4:
			moveName = playerCreature.moveset.get(3).name;
			break;
		default:
			break;
		}
		String dialogueString = playerCreature.name + " used " + moveName + "!";
		if (!moveDialogue.done) {
			moveDialogue.displayBattle(dialogueString);
		} else {
			enemyCreature.currentHealth -= getDamage(playerCreature, enemyCreature);
			isAttacking = true;
			attackState = AttackState.NONE;
			isEnemyTurn = true;
			isPlayerTurn = false;
			enemyAttacking = true;
			return true;
		}
		return false;
	}

	public boolean enemyMove(int move) {
		//ENEMY ATTACK
		String moveName = "";
		switch (move) {
		case 1:
			if (enemyCreature.moveset.size() >= 1) {
				moveName = enemyCreature.moveset.get(0).name;
			}
			break;
		case 2:
			if (enemyCreature.moveset.size() >= 2) {
				moveName = enemyCreature.moveset.get(1).name;
			}
			break;
		case 3:
			if (enemyCreature.moveset.size() >= 3) {
				moveName = enemyCreature.moveset.get(2).name;
			}
			break;
		case 4:
			if (enemyCreature.moveset.size() >= 4) {
				moveName = enemyCreature.moveset.get(3).name;
			}
			break;
		default:
			break;
		}
		String dialogueString = enemyCreature.name + " used " + moveName + "!";
		if (!enemyDialogue.done) {
			enemyDialogue.displayBattle(dialogueString);
		} else {
			playerCreature.currentHealth -= getDamage(enemyCreature, playerCreature);
			isAttacking = true;
			enemyState = AttackState.NONE;
			isEnemyTurn = false;
			isPlayerTurn = true;
			return true;
		}
		return false;
	}

	public int generateMove(PokemonCreature enemyPokemon) {
		//GENERATES THE ENEMY'S MOVE
		int move = (int) (Math.random() * enemyPokemon.moveset.size() + 1);
		switch (move) {
		case 1:
			enemyState = AttackState.Attack1;
			break;
		case 2:
			enemyState = AttackState.Attack2;
			break;
		case 3:
			enemyState = AttackState.Attack3;
			break;
		case 4:
			enemyState = AttackState.Attack4;
			break;
		default:
			break;
		}

		return move;
	}

	public int getDamage(PokemonCreature attPokemon, PokemonCreature defPokemon) {

		//CALCULATES DAMAGE
		float baseMovePower = 0;
		float basePokemonPower = 0;
		float basePokemonDefense = 0;
		float damage = 1;
		float modifier = 1f;

		if (isPlayerTurn) {
			switch (attackState) {
			case Attack1:
				if (PokemonWorld.party.party.get(0).moveset.size() >= 1) {
					baseMovePower = attPokemon.moveset.get(0).power;
					if (attPokemon.moveset.get(0).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
				}
				for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(0).type.equals(defPokemon.effectiveMoves.get(i))) {
						modifier = 2f;
					}
				}
				for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(0).type.equals(defPokemon.superEffectiveMoves.get(i))) {
						modifier = 4f;
					}
				}
				for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(0).type.equals(defPokemon.notEffectiveMoves.get(i))) {
						modifier = 0.5f;
					}
				}
				for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
					if (attPokemon.moveset.get(0).type.equals(defPokemon.noDamageMoves.get(i))) {
						modifier = 0f;
					}
				}
				System.out.println("Modifier" + modifier);
				break;
			case Attack2:
				if (PokemonWorld.party.party.get(0).moveset.size() >= 2) {
					baseMovePower = attPokemon.moveset.get(1).power;
					if (attPokemon.moveset.get(1).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
				}
				for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(1).type.equals(defPokemon.effectiveMoves.get(i))) {
						modifier = 2f;
					}
				}
				for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(1).type.equals(defPokemon.superEffectiveMoves.get(i))) {
						modifier = 4f;
					}
				}
				for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(1).type.equals(defPokemon.notEffectiveMoves.get(i))) {
						modifier = 0.5f;
					}
				}
				for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
					if (attPokemon.moveset.get(1).type.equals(defPokemon.noDamageMoves.get(i))) {
						modifier = 0f;
					}
				}
				System.out.println("Modifier" + modifier);
				break;
			case Attack3:
				if (PokemonWorld.party.party.get(0).moveset.size() >= 3) {
					baseMovePower = attPokemon.moveset.get(2).power;
					if (attPokemon.moveset.get(2).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
				}
				for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(2).type.equals(defPokemon.effectiveMoves.get(i))) {
						modifier = 2f;
					}
				}
				for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(2).type.equals(defPokemon.superEffectiveMoves.get(i))) {
						modifier = 4f;
					}
				}
				for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(2).type.equals(defPokemon.notEffectiveMoves.get(i))) {
						modifier = 0.5f;
					}
				}
				for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
					if (attPokemon.moveset.get(2).type.equals(defPokemon.noDamageMoves.get(i))) {
						modifier = 0f;
					}
				}
				System.out.println("Modifier" + modifier);
				break;
			case Attack4:
				if (PokemonWorld.party.party.get(0).moveset.size() >= 4) {
					baseMovePower = attPokemon.moveset.get(3).power;
					if (attPokemon.moveset.get(3).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
				}
				for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(3).type.equals(defPokemon.effectiveMoves.get(i))) {
						modifier = 2f;
					}
				}
				for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(3).type.equals(defPokemon.superEffectiveMoves.get(i))) {
						modifier = 4f;
					}
				}
				for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
					if (attPokemon.moveset.get(3).type.equals(defPokemon.notEffectiveMoves.get(i))) {
						modifier = 0.5f;
					}
				}
				for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
					if (attPokemon.moveset.get(3).type.equals(defPokemon.noDamageMoves.get(i))) {
						modifier = 0f;
					}
				}
				System.out.println("Modifier" + modifier);
				break;
			default:
				break;
			}
			damage = ((((((attPokemon.level * 2f) / 5f) + 2f) * baseMovePower * (basePokemonPower / basePokemonDefense))
					/ 50f) + 2f) * modifier;
		} else if (isEnemyTurn) {
			switch (move) {
			case 1:
				if (attPokemon.moveset.size() >= 1) {
					baseMovePower = attPokemon.moveset.get(0).power;
					if (attPokemon.moveset.get(0).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
					for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(0).type.equals(defPokemon.effectiveMoves.get(i))) {
							modifier = 2f;
						}
					}
					for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(0).type.equals(defPokemon.superEffectiveMoves.get(i))) {
							modifier = 4f;
						}
					}
					for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(0).type.equals(defPokemon.notEffectiveMoves.get(i))) {
							modifier = 0.5f;
						}
					}
					for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
						if (attPokemon.moveset.get(0).type.equals(defPokemon.noDamageMoves.get(i))) {
							modifier = 0f;
						}
					}
				}
				break;
			case 2:
				if (attPokemon.moveset.size() >= 2) {
					baseMovePower = attPokemon.moveset.get(1).power;
					if (attPokemon.moveset.get(1).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
					for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(1).type.equals(defPokemon.effectiveMoves.get(i))) {
							modifier = 2f;
						}
					}
					for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(1).type.equals(defPokemon.superEffectiveMoves.get(i))) {
							modifier = 4f;
						}
					}
					for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(1).type.equals(defPokemon.notEffectiveMoves.get(i))) {
							modifier = 0.5f;
						}
					}
					for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
						if (attPokemon.moveset.get(1).type.equals(defPokemon.noDamageMoves.get(i))) {
							modifier = 0f;
						}
					}
				}
				break;
			case 3:
				if (attPokemon.moveset.size() >= 3) {
					baseMovePower = attPokemon.moveset.get(2).power;
					if (attPokemon.moveset.get(2).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
					for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(2).type.equals(defPokemon.effectiveMoves.get(i))) {
							modifier = 2f;
						}
					}
					for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(2).type.equals(defPokemon.superEffectiveMoves.get(i))) {
							modifier = 4f;
						}
					}
					for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(2).type.equals(defPokemon.notEffectiveMoves.get(i))) {
							modifier = 0.5f;
						}
					}
					for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
						if (attPokemon.moveset.get(2).type.equals(defPokemon.noDamageMoves.get(i))) {
							modifier = 0f;
						}
					}
				}
				break;
			case 4:
				if (attPokemon.moveset.size() >= 4) {
					baseMovePower = attPokemon.moveset.get(3).power;
					if (attPokemon.moveset.get(3).contact.equalsIgnoreCase("Physical")) {
						basePokemonPower = attPokemon.baseATT;
						basePokemonDefense = defPokemon.baseDEF;
					} else {
						basePokemonPower = attPokemon.baseSPA;
						basePokemonDefense = defPokemon.baseSPD;
					}
					for (int i = 0; i < defPokemon.effectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(3).type.equals(defPokemon.effectiveMoves.get(i))) {
							modifier = 2f;
						}
					}
					for (int i = 0; i < defPokemon.superEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(3).type.equals(defPokemon.superEffectiveMoves.get(i))) {
							modifier = 4f;
						}
					}
					for (int i = 0; i < defPokemon.notEffectiveMoves.size(); i++) {
						if (attPokemon.moveset.get(3).type.equals(defPokemon.notEffectiveMoves.get(i))) {
							modifier = 0.5f;
						}
					}
					for (int i = 0; i < defPokemon.noDamageMoves.size(); i++) {
						if (attPokemon.moveset.get(3).type.equals(defPokemon.noDamageMoves.get(i))) {
							modifier = 0f;
						}
					}
				}
				break;
			default:
				break;
			}
			damage = ((((((attPokemon.level * 2f) / 5f) + 2f) * baseMovePower * (basePokemonPower / basePokemonDefense))
					/ 50f) + 2f) * modifier;
		}
		System.out.println(damage);
		return Math.round(damage);
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
