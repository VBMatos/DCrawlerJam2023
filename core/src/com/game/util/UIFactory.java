package com.game.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.game.enums.EnumGameState;
import com.game.DCrawlerJam;

public class UIFactory {
	
	public static ImageButton createImageButton(Texture texture) {
		return new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
	}
	
	public static InputListener createExitListener() {
		return new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
				return false;
			}
		};
	}

	public static InputListener createChangeScreenListener(final EnumGameState gameState, final DCrawlerJam game) {
		return new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(gameState.getScreen(game));
				return false;
			}
		};
	}
}