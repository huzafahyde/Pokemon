package com.mygdx.pokemon.Screens.Battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pokemon.Logic.PokemonCreature;
import com.mygdx.pokemon.Screens.PokemonWorld;
import com.mygdx.pokemon.Screens.Battle.BattleScreen.AttackState;

public class BattleHud {
	public Stage stage;

	SpriteBatch sb;

	OrthographicCamera camera;
	Viewport viewport;

	TextButton move1;
	TextButton move2;
	TextButton move3;
	TextButton move4;

	public Image playerPokemonInfo;
	public Image enemyPokemonInfo;

	public WidgetGroup playerInfo;
	public WidgetGroup enemyInfo;

	Label enemyNameLabel;
	Label playerNameLabel;

	Label enemylevelLabel;
	Label playerlevelLabel;

	Label currentHealthLabel;
	Label maxHealthLabel;
	LabelStyle lStyle;

	public BattleHud(SpriteBatch sb, Viewport viewport) {
		//SETS UP BATTLEHUD
		this.sb = sb;

		BitmapFont font = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"),
				Gdx.files.internal("Main/pokemonFont.png"), false);
		TextureAtlas uIAtlas = new TextureAtlas("UI/Main/skin/uiskin.atlas");
		Skin skin = new Skin(uIAtlas);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);

		this.viewport = viewport;
		// viewport = new StretchViewport(1920, 1080, camera);
		stage = new Stage(viewport, this.sb);

		playerPokemonInfo = new Image(new Texture("Battle/Hud/Player.png"));
		playerPokemonInfo.setSize(400, 150);
		enemyPokemonInfo = new Image(new Texture("Battle/Hud/Enemy.png"));
		enemyPokemonInfo.setSize(400, 150);

		Image playerHealth = new Image(new Texture("UI/PokemonMenu/White.png"));
		playerHealth.setScale(400 / 128f, 150 / 47f);
		playerHealth.setPosition(225, 60);
		playerHealth.setColor(new Color(0.09411765f, 0.75294118f, 0.12254902f, 1));
		playerHealth.setSize(48, 400 / 128f);
		playerHealth.setName("health");
		
		Image EXP = new Image(new Texture("UI/PokemonMenu/White.png"));
		EXP.setPosition(72, 8);
		EXP.setScale(400 / 128f, 150 / 47f);
		EXP.setSize(97, 2.875f);
		EXP.setColor(Color.BLUE);

		Image enemyHealth = new Image(new Texture("UI/PokemonMenu/White.png"));
		enemyHealth.setScale(400 / 128f, 150 / 47f);
		enemyHealth.setPosition(165, 34);
		enemyHealth.setColor(new Color(0.09411765f, 0.75294118f, 0.12254902f, 1));
		enemyHealth.setSize(52, 400 / 128f * 1.33f);
		enemyHealth.setName("health");

		lStyle = new LabelStyle(font, Color.BLACK);
		playerNameLabel = new Label("", lStyle);
		playerNameLabel.moveBy(50, 100);
		enemyNameLabel = new Label("", lStyle);
		enemyNameLabel.moveBy(10, 100);

		playerlevelLabel = new Label("", lStyle);
		playerlevelLabel.moveBy(350, 102);
		enemylevelLabel = new Label("", lStyle);
		enemylevelLabel.moveBy(295, 88);
		maxHealthLabel = new Label("", lStyle);
		maxHealthLabel.moveBy(320, 38);
		currentHealthLabel = new Label("", lStyle);
		currentHealthLabel.moveBy(230, 38);

		/////////////////////////////////////

		playerInfo = new WidgetGroup();
		playerInfo.addActor(playerPokemonInfo);
		playerInfo.addActor(playerHealth);
		playerInfo.addActor(playerNameLabel);
		playerInfo.addActor(playerlevelLabel);
		playerInfo.addActor(currentHealthLabel);
		playerInfo.addActor(maxHealthLabel);
		playerInfo.addActor(EXP);
		

		enemyInfo = new WidgetGroup();
		enemyInfo.addActor(enemyPokemonInfo);
		enemyInfo.addActor(enemyHealth);
		enemyInfo.addActor(enemyNameLabel);
		enemyInfo.addActor(enemylevelLabel);

		////////////////////////////////////

		Table table = new Table();
		table.setFillParent(true);
		table.left();

		table.add(enemyInfo).left().size(400, 150).padBottom(150);
		table.row();
		table.add(playerInfo).right().size(400, 150).padLeft(810).padTop(150);

		/////////////////////////////////////////////////////////////////////////////

		TextButtonStyle style = new TextButtonStyle();
		style.font = font;
		style.up = skin.getDrawable("default-pane");
		style.down = skin.getDrawable("default-pane-noborder");

		move1 = new TextButton("Move1", style);
		move1.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.size() >= 1) {
					BattleScreen.attackState = AttackState.Attack1;
				}
			}
		});
		move2 = new TextButton("Move2", style);
		move2.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.size() >= 2) {
					BattleScreen.attackState = AttackState.Attack2;
				}
			}
		});
		move3 = new TextButton("Move3", style);
		move3.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.size() >= 3) {
					BattleScreen.attackState = AttackState.Attack3;
				}
			}
		});
		move4 = new TextButton("Move4", style);
		move4.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.size() >= 4) {
					BattleScreen.attackState = AttackState.Attack4;
				}
			}
		});

		TextButton bagButton = new TextButton("Bag", style);
		TextButton runButton = new TextButton("Run", style);
		TextButton pokemonButton = new TextButton("Pokemon", style);

		Table table2 = new Table();
		// table2.setFillParent(true);
		// table2.right();

		table2.setPosition(1210, 500);
		table2.add(move1).size(350, 150).padBottom(50);
		table2.add(move2).size(350, 150).padBottom(50);
		table2.row();
		table2.add(move3).size(350, 150).padBottom(50);
		table2.add(move4).size(350, 150).padBottom(50);
		table2.row();
		table2.add(bagButton).left().size(200, 100);
		table2.add(pokemonButton).right().size(200, 100);
		table2.add(runButton).expandX().size(200, 100);

		// table.debug();
		// table2.debug();
		stage.addActor(table);
		stage.addActor(table2);
	}

	public void setLabels(PokemonCreature pokemon) {
		playerNameLabel.setText(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).name);
		enemyNameLabel.setText(pokemon.name);
		playerlevelLabel.setText(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).level + "");
		enemylevelLabel.setText(pokemon.level + "");
		maxHealthLabel.setText(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).maxHealth + "");
		currentHealthLabel.setText(Math.max(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).currentHealth, 0) + "");
	}

	public void setMoveNames() {
		if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(0) != null) {
			move1.setText(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(0).name);
		}

		if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.size() >= 2) {
			if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(1) != null) {
				move2.setText(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(1).name);
			}
		} else {
			move2.setText("");
		}

		if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.size() >= 3) {
			if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(2) != null) {
				move3.setText(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(2).name);
			}
		} else {
			move3.setText("");
		}

		if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.size() >= 4) {
			if (PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(3) != null) {
				move4.setText(PokemonWorld.party.party.get(BattleScreen.currentPlayerID).moveset.get(3).name);
			}
		} else {
			move4.setText("");
		}
	}

	public void update(PokemonCreature pokemon) {
		if ((float) PokemonWorld.party.party.get(BattleScreen.currentPlayerID).currentHealth / PokemonWorld.party.party.get(BattleScreen.currentPlayerID).maxHealth > 0.5f) {
			playerInfo.findActor("health").setColor(new Color(0.09411765f, 0.75294118f, 0.12254902f, 1));
		} else if ((float) PokemonWorld.party.party.get(BattleScreen.currentPlayerID).currentHealth
				/ PokemonWorld.party.party.get(BattleScreen.currentPlayerID).maxHealth > 0.2f) {
			playerInfo.findActor("health").setColor(new Color(0.97254902f, 0.69019608f, 0, 1));
		} else {
			playerInfo.findActor("health").setColor(new Color(0.97254902f, 0.34509804f, 0.15686275f, 1));
		}

		float ratio1 = (float) (Math.min(
				(float) PokemonWorld.party.party.get(BattleScreen.currentPlayerID).currentHealth / PokemonWorld.party.party.get(BattleScreen.currentPlayerID).maxHealth, 1));

		if (!PokemonWorld.party.party.get(BattleScreen.currentPlayerID).fainted) {
			playerInfo.findActor("health").setSize(48f * ratio1, 400 / 128f);
		} else {
			playerInfo.findActor("health").setSize(0, 400 / 128f);
		}

		if ((float) pokemon.currentHealth / pokemon.maxHealth > 0.5f) {
			enemyInfo.findActor("health").setColor(new Color(0.09411765f, 0.75294118f, 0.12254902f, 1));
		} else if ((float) pokemon.currentHealth / pokemon.maxHealth > 0.2f) {
			enemyInfo.findActor("health").setColor(new Color(0.97254902f, 0.69019608f, 0, 1));
		} else {
			enemyInfo.findActor("health").setColor(new Color(0.97254902f, 0.34509804f, 0.15686275f, 1));
		}

		float ratio2 = (float) (Math.min((float) pokemon.currentHealth / pokemon.maxHealth, 1));
		
		if (!pokemon.fainted) {
			enemyInfo.findActor("health").setSize(52f * ratio2, 400 / 128f * 1.33f);
		} else {
			enemyInfo.findActor("health").setSize(0, 400 / 128f * 1.33f);
		}

		setLabels(pokemon);
	}
}
