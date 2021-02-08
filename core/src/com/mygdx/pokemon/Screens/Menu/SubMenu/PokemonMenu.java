package com.mygdx.pokemon.Screens.Menu.SubMenu;

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
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar.ProgressBarStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.pokemon.Logic.AnimatorActor;
import com.mygdx.pokemon.Screens.PokemonWorld;
import com.mygdx.pokemon.Screens.Menu.GameMenu;

public class PokemonMenu {

	SpriteBatch sb;

	public Stage stage;
	Skin skin;

	OrthographicCamera camera;
	Viewport viewport;

	TextureAtlas uIAtlas;
	TextButtonStyle style;
	BitmapFont font;
	BitmapFont healthFont;
	ProgressBarStyle pBarStyle;

	float blockScale = 0.5f, x = -128;
	float imageScale = 2f;
	/////////////

	WidgetGroup[] group;

	/////////////

	TextButton leave;

	LabelStyle labelStyle;
	LabelStyle healthStyle;

	float updateTimer = 0;

	public Table table = new Table();
	public Table table2 = new Table();

	public PokemonMenu(SpriteBatch sb) {
		// SHOWS ALL 6 POKEMON

		group = new WidgetGroup[6];

		this.sb = sb;

		uIAtlas = new TextureAtlas("UI/Main/skin/uiskin.atlas");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1920, 1080);
		viewport = new FitViewport(1920, 1080);
		stage = new Stage(viewport, this.sb);

		table.left();
		table.setFillParent(true);

		table2.center();
		table2.setFillParent(true);

		skin = new Skin(uIAtlas);

		font = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"), Gdx.files.internal("Main/pokemonFont.png"),
				false);
		font.getData().setScale(2);

		healthFont = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"),
				Gdx.files.internal("Main/pokemonFont.png"), false);
		healthFont.getData().setScale(1.25f);

		style = new TextButtonStyle();
		style.font = font;
		style.up = skin.getDrawable("default-pane");
		style.down = skin.getDrawable("default-pane-noborder");

		///////////////
		labelStyle = new LabelStyle(font, Color.WHITE);
		healthStyle = new LabelStyle(healthFont, Color.WHITE);

		for (int i = 0; i < PokemonWorld.party.party.size(); i++) {
			group[i] = new WidgetGroup();
			if (PokemonWorld.party.party.get(i) != null) {
				Label name = new Label(PokemonWorld.party.party.get(i).name, labelStyle);
				name.setPosition(96, 80);
				AnimatorActor animatorActor = new AnimatorActor(PokemonWorld.party.party.get(i).downAnimation, -128, 16,
						128 + 64, 128 + 64);
				Image background = new Image(new Texture("UI/PokemonMenu/menuBlock.png"));
				background.setScale(blockScale);
				background.setX(x);
				Image healthBar = new Image(new Texture("UI/PokemonMenu/HealthBar.png"));
				healthBar.setScale(6);
				healthBar.setPosition(88, 32);
				Image health = new Image(new Texture("UI/PokemonMenu/White.png"));
				health.setName("health");
				health.setScale(6);
				health.setPosition(88 + 14 * 6, 44);
				health.setSize(44f * ((float) PokemonWorld.party.party.get(i).currentHealth
						/ PokemonWorld.party.party.get(i).maxHealth), 3);
				String healthString = PokemonWorld.party.party.get(i).currentHealth + "  /  "
						+ PokemonWorld.party.party.get(i).maxHealth;
				Label healthLabel = new Label(healthString, healthStyle);
				healthLabel.setPosition(200, 0);

				group[i].addActor(background);
				group[i].addActor(name);
				group[i].addActor(animatorActor);
				group[i].addActor(healthBar);
				group[i].addActor(health);
				group[i].addActor(healthLabel);
			}
		}

		leave = new TextButton("Back", style);
		leave.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				
				GameMenu.isPokemon = false;
				GameMenu.isMenu = true;
			}
		});

		table.add(group[0]).expandX().size(400, 150).padBottom(40);
		table.add(group[1]).expandX().size(400, 150).padBottom(40).padTop(64);
		table.row();
		table.add(group[2]).expandX().size(400, 150).padBottom(40);
		table.add(group[3]).expandX().size(400, 150).padBottom(40).padTop(64);
		table.row();
		table.add(group[4]).expandX().size(400, 150).padBottom(40);
		table.add(group[5]).expandX().size(400, 150).padBottom(40).padTop(64);

		table2.add(leave).expandX().padTop(Gdx.graphics.getHeight() / 2).size(200, 100);

		stage.addActor(table);
		stage.addActor(table2);
	}

	public void increaseTimer(float delta) {
		updateTimer += delta;
	}

	public void updateInfo() {
		if (updateTimer > 1f) {
			updateTimer = 0;
			for (int i = 0; i < PokemonWorld.party.party.size(); i++) {
				group[i] = new WidgetGroup();
				if (PokemonWorld.party.party.get(i) != null) {
					Label name = new Label(PokemonWorld.party.party.get(i).name, labelStyle);
					name.setPosition(96, 80);
					AnimatorActor animatorActor = new AnimatorActor(PokemonWorld.party.party.get(i).downAnimation, -128,
							16, 128 + 64, 128 + 64);
					Image background = new Image(new Texture("UI/PokemonMenu/menuBlock.png"));
					background.setScale(blockScale);
					background.setX(x);
					Image healthBar = new Image(new Texture("UI/PokemonMenu/HealthBar.png"));
					healthBar.setScale(6);
					healthBar.setPosition(88, 32);
					Image health = new Image(new Texture("UI/PokemonMenu/White.png"));
					health.setName("health");
					health.setScale(6);
					health.setPosition(88 + 14 * 6, 44);
					health.setSize(44f * ((float) PokemonWorld.party.party.get(i).currentHealth
							/ PokemonWorld.party.party.get(i).maxHealth), 3);
					String healthString = PokemonWorld.party.party.get(i).currentHealth + "  /  "
							+ PokemonWorld.party.party.get(i).maxHealth;
					Label healthLabel = new Label(healthString, healthStyle);
					healthLabel.setPosition(200, 0);

					group[i].addActor(background);
					group[i].addActor(name);
					group[i].addActor(animatorActor);
					group[i].addActor(healthBar);
					group[i].addActor(health);
					group[i].addActor(healthLabel);
				}
				table.clear();
				table.add(group[0]).expandX().size(400, 150).padBottom(40);
				table.add(group[1]).expandX().size(400, 150).padBottom(40).padTop(64);
				table.row();
				table.add(group[2]).expandX().size(400, 150).padBottom(40);
				table.add(group[3]).expandX().size(400, 150).padBottom(40).padTop(64);
				table.row();
				table.add(group[4]).expandX().size(400, 150).padBottom(40);
				table.add(group[5]).expandX().size(400, 150).padBottom(40).padTop(64);
			}
			updateColor();
		}
	}

	public void updateColor() {
		for (int i = 0; i < PokemonWorld.party.party.size(); i++) {
			if (PokemonWorld.party.party.get(i) != null) {
				if ((float) PokemonWorld.party.party.get(i).currentHealth
						/ PokemonWorld.party.party.get(i).maxHealth > 0.5f) {
					group[i].findActor("health").setColor(new Color(0.09411765f, 0.75294118f, 0.12254902f, 1));
				} else if ((float) PokemonWorld.party.party.get(i).currentHealth
						/ PokemonWorld.party.party.get(i).maxHealth > 0.2f) {
					group[i].findActor("health").setColor(new Color(0.97254902f, 0.69019608f, 0, 1));
				} else {
					group[i].findActor("health").setColor(new Color(0.97254902f, 0.34509804f, 0.15686275f, 1));
				}
			}
		}
	}
}
