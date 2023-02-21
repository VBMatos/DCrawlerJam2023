package com.game.enums;

import com.badlogic.gdx.Screen;
import com.game.screens.game.GameScreen;
import com.game.screens.menu.MainMenuScreen;
import com.game.DCrawlerJam;

public enum EnumGameState {

	MAIN_MENU{
		public Screen getScreen(DCrawlerJam game) {
			return new MainMenuScreen(game);
		}
	}, 
	GAME{
		public Screen getScreen(DCrawlerJam game) {
			return new GameScreen(game);
		}
	}; 

	public abstract Screen getScreen(DCrawlerJam game);
}
