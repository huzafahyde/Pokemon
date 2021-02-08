package com.mygdx.pokemon.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DialogueHud {
	SpriteBatch sb;
	
	public Stage stage;
	private Viewport viewport;

	Texture borderTexture;
	Image border;
	
	LabelStyle style;
	Label messageLabel;
	
	WidgetGroup group;
	
	BitmapFont font;
	
	String message; 
	
	Table table;
	
	public DialogueHud(SpriteBatch sb) {
		//SELF EXPLANATORY
		//MAKES A HUD WITH SCENE2D SO IT IS EASIER TO MANAGE
		
		this.sb = sb;
		
		viewport = new FitViewport(1920, 1080);
		stage = new Stage(viewport, this.sb);
		
		borderTexture = new Texture("UI/Dialogue/DialogueBoxDefault.png");
		border = new Image(borderTexture);
		border.setScale(2);

		font = new BitmapFont(Gdx.files.internal("Main/pokemonFont.fnt"), Gdx.files.internal("Main/pokemonFont.png"),false);
		font.getData().setScale(2);
		
		style = new LabelStyle(font, Color.BLUE);
		
		messageLabel = new Label("HELLO", style);
		messageLabel.setPosition(40, 60);;
		
		group = new WidgetGroup();
		
		group.addActor(border);
		group.addActor(messageLabel);
		
		table = new Table();
		table.setFillParent(true);
		
		table.add(group);
		
		stage.addActor(table);
		
	}
	
	public void updateMessage(String message) {
		messageLabel.setText(message);
	}
	
}
