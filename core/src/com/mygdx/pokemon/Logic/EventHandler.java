package com.mygdx.pokemon.Logic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.pokemon.Logic.Data.DialogueList;
import com.mygdx.pokemon.Logic.Data.NPCInteractDialogue;
import com.mygdx.pokemon.Logic.Data.PokemonList;
import com.mygdx.pokemon.Logic.SoundManager.SoundState;
import com.mygdx.pokemon.Screens.PokemonWorld;
import com.mygdx.pokemon.Screens.Battle.BattleScreen;

public class EventHandler {

	DialogueList dialogueList;
	public static NPCInteractDialogue interactDialogue;

	// AMOUNT OF DIALUGE MOM HAS
	int momDialogue = 7;

	public boolean isMomEvent;
	boolean isMomTutorialEventDone = false;

	int profDialogue = 5;

	public boolean isProfEvent;
	boolean isProfEventDone = false;
	boolean isProfEvent2Done = false;

	float width, height;
	float moveDelay = 0;

	public static PokemonChosen pokemonChosen;

	public static boolean isAdding;

	public EventHandler(float width, float height) {
		// SETS DATABASE
		dialogueList = new DialogueList();
		interactDialogue = new NPCInteractDialogue();

		this.height = height;
		this.width = width;

		pokemonChosen = PokemonChosen.None;
	}

	public void update(float delta) {
		// CALLS ALL POSSIBLE INTERACTIONS
		momTutorial();
		elmEvent(delta);
		PC();
		television();
		bookshelf();
		broken();
		fridge();
		choosePokemonEvent();
		elmEvent2(delta);
		rivalBattle();
	}

	public void updateNPCRectangles() {

	}
	

	public void momTutorial() {
		if (dialogueList.dialogues.get(6).done) {
			if (PokemonWorld.npcList.mom.sprite.getX() < 120) {
				PokemonWorld.npcList.mom.moveRight(2);
			} else {
				PokemonWorld.isEvent = false;
				isMomTutorialEventDone = true;
				isMomEvent = false;
				PokemonWorld.npcList.mom.sprite.setRegion(new Texture("Entities/NPCs/Mom/images/MOM_02.gif"));
			}
		} else if (PokemonWorld.area.player.sprite.getY() < 120 && PokemonWorld.doorNames.locationBooleans[1]
				&& !isMomTutorialEventDone) {
			isMomEvent = true;
			PokemonWorld.isEvent = true;
			PokemonWorld.area.currentDirection.y = 0;
			PokemonWorld.area.player.sprite
					.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkdown2.png"));
			if (PokemonWorld.npcList.mom.sprite.getX() > PokemonWorld.area.player.sprite.getX()) {
				PokemonWorld.npcList.mom.moveLeft(2);
			} else {
				PokemonWorld.npcList.mom.sprite.setRegion(new Texture("Entities/NPCs/Mom/images/MOM_01.png"));
				for (int i = 0; i < momDialogue; i++) {
					dialogueList.dialogues.get(i).resize(width, height);
					System.out.println(i);
					dialogueList.dialogues.get(i).set(0.4f, -80, 20);
					if (!dialogueList.dialogues.get(0).done) {
						dialogueList.dialogues.get(0).display();
					}

					if (dialogueList.dialogues.get(i).done) {
						if (i + 1 != momDialogue) {
							dialogueList.dialogues.get(i + 1).display();
						}
					}

				}
			}
		}
		// IF PLAYER IS AT A CERTAIN MAP AND MOVES DOWN THE MOM GOES TO THE X VALUE OF
		// PLAYER
		// SHE STARTS TALKING AND WALKS BACK

	}

	public void elmEvent(float delta) {
		if (!isProfEventDone) {
			if (dialogueList.dialogues.get(20).done) {
				if (PokemonWorld.npcList.npcs.get(1).sprite.getY() < 296) {
					PokemonWorld.npcList.npcs.get(1).moveUp(2);
				} else if (PokemonWorld.npcList.npcs.get(1).sprite.getX() < 448) {
					PokemonWorld.npcList.npcs.get(1).moveRight(2);
				} else {
					PokemonWorld.npcList.npcs.get(1).sprite
							.setRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_01.png"));
					moveDelay += delta;
					System.out.println(moveDelay);
					if (moveDelay > 1f) {
						PokemonWorld.isEvent = false;
						isProfEventDone = true;
					}
				}
			} else if (PokemonWorld.doorNames.locationBooleans[3] && PokemonWorld.area.player.sprite.getY() > 220
					&& !isProfEventDone) {
				if (PokemonWorld.npcList.npcs.get(1).sprite.getX() > PokemonWorld.area.player.sprite.getX()) {
					PokemonWorld.npcList.npcs.get(1).moveLeft(2);
					PokemonWorld.isEvent = true;
					PokemonWorld.area.currentDirection.y = 0;
					PokemonWorld.area.currentDirection.x = 0;
					PokemonWorld.area.player.sprite
							.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkup2.png"));
				} else if (PokemonWorld.npcList.npcs.get(1).sprite.getY() > PokemonWorld.area.player.sprite.getY()
						+ 30) {
					PokemonWorld.npcList.npcs.get(1).moveDown(2);
				} else {
					PokemonWorld.npcList.npcs.get(1).sprite
							.setRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_06.png"));

					for (int i = 12; i < 21; i++) {
						dialogueList.dialogues.get(i).resize(width, height);
						System.out.println(i);
						dialogueList.dialogues.get(i).set(0.4f, -80, 20);
						if (!dialogueList.dialogues.get(12).done) {
							dialogueList.dialogues.get(12).display();
						}

						if (dialogueList.dialogues.get(i).done) {
							if (i + 1 != 21) {
								dialogueList.dialogues.get(i + 1).display();
							}
						}

					}
				}
			}
		}
	}

	public void PC() {
		if (PokemonWorld.interactData.booleans[0]) {
			dialogueList.dialogues.get(7).done = false;
			dialogueList.dialogues.get(7).display();
			PokemonWorld.isInteraction = true;
		}

		if (dialogueList.dialogues.get(7).done) {
			PokemonWorld.interactData.booleans[0] = false;
			PokemonWorld.isInteraction = false;
		}
	}

	public void television() {
		if (PokemonWorld.interactData.booleans[1]) {
			dialogueList.dialogues.get(8).done = false;
			dialogueList.dialogues.get(8).display();
			PokemonWorld.isInteraction = true;
		}

		if (dialogueList.dialogues.get(8).done) {
			PokemonWorld.interactData.booleans[1] = false;
			PokemonWorld.isInteraction = false;
		}
	}

	public void bookshelf() {
		if (PokemonWorld.interactData.booleans[2]) {
			dialogueList.dialogues.get(9).done = false;
			dialogueList.dialogues.get(9).display();
			PokemonWorld.isInteraction = true;
		}

		if (dialogueList.dialogues.get(9).done) {
			PokemonWorld.interactData.booleans[2] = false;
			PokemonWorld.isInteraction = false;
		}
	}

	public void broken() {
		if (PokemonWorld.interactData.booleans[3]) {
			dialogueList.dialogues.get(10).done = false;
			dialogueList.dialogues.get(10).display();
			PokemonWorld.isInteraction = true;
		}

		if (dialogueList.dialogues.get(10).done) {
			PokemonWorld.interactData.booleans[3] = false;
			PokemonWorld.isInteraction = false;
		}
	}

	public void fridge() {
		if (PokemonWorld.interactData.booleans[4]) {
			dialogueList.dialogues.get(11).done = false;
			dialogueList.dialogues.get(11).display();
			PokemonWorld.isInteraction = true;
		}

		if (dialogueList.dialogues.get(11).done) {
			PokemonWorld.interactData.booleans[4] = false;
			PokemonWorld.isInteraction = false;
		}
	}

	public enum PokemonChosen {
		None, Bulbasaur, Charmander, Squirtle
	}

	public void choosePokemonEvent() {
		if (PokemonWorld.interactData.booleans[5] && !PokemonWorld.hasChosen) {
			PokemonWorld.isInteraction = true;
			PokemonWorld.isChoosingPokemon = true;
			PokemonWorld.isEvent = true;
			if (pokemonChosen == PokemonChosen.Bulbasaur) {
				if (!isAdding) {
					PokemonWorld.party.party.add(PokemonList.generatePokemon(0));
					isAdding = true;
					PokemonWorld.isChoosingPokemon = false;
					PokemonWorld.hasChosen = true;
					PokemonWorld.isEvent = false;
					PokemonWorld.hasChanged = true;
					PokemonWorld.isInteraction = false;
				}
			} else if (pokemonChosen == PokemonChosen.Charmander) {
				if (!isAdding) {
					PokemonWorld.party.party.add(PokemonList.generatePokemon(3));
					isAdding = true;
					PokemonWorld.isChoosingPokemon = false;
					PokemonWorld.hasChosen = true;
					PokemonWorld.isEvent = false;
					PokemonWorld.hasChanged = true;
					PokemonWorld.isInteraction = false;
				}
			} else if (pokemonChosen == PokemonChosen.Squirtle) {
				if (!isAdding) {
					PokemonWorld.party.party.add(PokemonList.generatePokemon(6));
					isAdding = true;
					PokemonWorld.isChoosingPokemon = false;
					PokemonWorld.hasChosen = true;
					PokemonWorld.isEvent = false;
					PokemonWorld.hasChanged = true;
					PokemonWorld.isInteraction = false;
				}
			}
		}
	}

	float moveDelay2 = 0;
	public static boolean hasRivalBattlePassed;
	public static boolean hasRivalStarted;
	boolean hasRivalBattleStarted;
	boolean rivalWalkAway;

	public void rivalBattle() {
		if (PokemonWorld.doorNames.locationBooleans[2] && PokemonWorld.hasChosen && !hasRivalBattlePassed) {
			hasRivalStarted = true;
			PokemonWorld.npcList.npcs.get(2).setIsGone(false);
			if (PokemonWorld.npcList.npcs.get(2).sprite.getX() < 600 && !rivalWalkAway) {
				PokemonWorld.area.player.sprite
						.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkleft2.png"));
				PokemonWorld.isEvent = true;
				PokemonWorld.npcList.npcs.get(2).moveRight(2);
			} else {
				PokemonWorld.area.player.sprite
						.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkleft2.png"));
				PokemonWorld.npcList.npcs.get(2).sprite
						.setRegion(new Texture("Entities/NPCs/Rival/images/Rival_02.png"));
				for (int i = 35; i < 42; i++) {
					dialogueList.dialogues.get(i).resize(width, height);
					System.out.println(i);
					dialogueList.dialogues.get(i).set(0.4f, -80, 20);
					if (!dialogueList.dialogues.get(35).done) {
						dialogueList.dialogues.get(35).display();
					}

					if (dialogueList.dialogues.get(i).done) {
						if (i + 1 != 42) {
							dialogueList.dialogues.get(i + 1).display();
						}
					}

					if (dialogueList.dialogues.get(41).done) {
						if (!hasRivalBattleStarted) {
							hasRivalStarted = false;
							hasRivalBattleStarted = true;
							if (PokemonWorld.isEvent) {
								PokemonWorld.soundManager.soundState = SoundState.Rival;
							}
							if (PokemonWorld.party.party.get(0).name.equals("Bulbasaur")) {
								PokemonWorld.game
										.setScreen(new BattleScreen(PokemonWorld.game, PokemonWorld.sb, 3, false));
							}
							if (PokemonWorld.party.party.get(0).name.equals("Charmander")) {
								PokemonWorld.game
										.setScreen(new BattleScreen(PokemonWorld.game, PokemonWorld.sb, 4, false));
							}
							if (PokemonWorld.party.party.get(0).name.equals("Squirtle")) {
								PokemonWorld.game
										.setScreen(new BattleScreen(PokemonWorld.game, PokemonWorld.sb, 2, false));
							}
						}
					}
				}
				if (hasRivalBattleStarted) {
					for (int i = 42; i < 50; i++) {
						dialogueList.dialogues.get(i).resize(width, height);
						System.out.println(i);
						dialogueList.dialogues.get(i).set(0.4f, -80, 20);
						if (!dialogueList.dialogues.get(42).done) {
							dialogueList.dialogues.get(42).display();
						}

						if (dialogueList.dialogues.get(i).done) {
							if (i + 1 != 49) {
								dialogueList.dialogues.get(i + 1).display();
							} else {
								rivalWalkAway = true;
								if (PokemonWorld.npcList.npcs.get(2).sprite.getX() > 200) {
									PokemonWorld.npcList.npcs.get(2).moveLeft(2);
								} else {
									PokemonWorld.npcList.npcs.get(2).isGone = true;
									PokemonWorld.isEvent = false;
									hasRivalBattlePassed = true;
								}
							}
						}
					}
				}
			}
		} else {
			PokemonWorld.npcList.npcs.get(2).setIsGone(true);
		}
	}

	public void elmEvent2(float delta) {
		if (!isProfEvent2Done) {
			if (dialogueList.dialogues.get(34).done) {
				if (PokemonWorld.npcList.npcs.get(1).sprite.getY() < 296
						|| PokemonWorld.npcList.npcs.get(1).sprite.getY() > 296) {
					if (PokemonWorld.npcList.npcs.get(1).sprite.getY() < 296) {
						PokemonWorld.npcList.npcs.get(1).moveUp(2);
					} else if (PokemonWorld.npcList.npcs.get(1).sprite.getY() > 296) {
						PokemonWorld.npcList.npcs.get(1).moveDown(2);
					}
				} else if (PokemonWorld.npcList.npcs.get(1).sprite.getX() < 448) {
					PokemonWorld.npcList.npcs.get(1).moveRight(2);
				} else {
					PokemonWorld.npcList.npcs.get(1).sprite
							.setRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_01.png"));
					moveDelay2 += delta;
					if (moveDelay2 > 0.5f) {
						PokemonWorld.isEvent = false;
						isProfEvent2Done = true;
					}
				}
			} else

			if (PokemonWorld.doorNames.locationBooleans[3] && PokemonWorld.hasChosen
					&& PokemonWorld.area.player.sprite.getX() > 320) {
				PokemonWorld.isEvent = true;
				if (PokemonWorld.npcList.npcs.get(1).sprite.getX() > PokemonWorld.area.player.sprite.getX() + 30) {
					PokemonWorld.npcList.npcs.get(1).moveLeft(2);
				} else if (PokemonWorld.npcList.npcs.get(1).sprite.getY() < PokemonWorld.area.player.sprite.getY()
						|| PokemonWorld.npcList.npcs.get(1).sprite.getY() > PokemonWorld.area.player.sprite.getY()) {
					if (PokemonWorld.npcList.npcs.get(1).sprite.getY() < PokemonWorld.area.player.sprite.getY()) {
						PokemonWorld.npcList.npcs.get(1).moveUp(2);
					} else if (PokemonWorld.npcList.npcs.get(1).sprite.getY() > PokemonWorld.area.player.sprite
							.getY()) {
						PokemonWorld.npcList.npcs.get(1).moveDown(2);
					}
				} else {
					PokemonWorld.area.player.sprite
							.setRegion(new Texture("Entities/Trainer/Male/Walk/images/Base/walkright2.png"));
					PokemonWorld.npcList.npcs.get(1).sprite
							.setRegion(new Texture("Entities/NPCs/ProfessorElm/images/Professor_07.png"));
					for (int i = 21; i < 35; i++) {
						dialogueList.dialogues.get(i).resize(width, height);
						System.out.println(i);
						dialogueList.dialogues.get(i).set(0.4f, -80, 20);
						if (!dialogueList.dialogues.get(21).done) {
							dialogueList.dialogues.get(21).display();
						}

						if (dialogueList.dialogues.get(i).done) {
							if (i + 1 != 35) {
								dialogueList.dialogues.get(i + 1).display();
							}
						}
					}
				}

			}
		}
	}
}
