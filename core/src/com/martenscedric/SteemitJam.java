package com.martenscedric;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class SteemitJam extends SceneManager {

	private AssetManager assetManager;
	@Override
	public void create () {
		super.create();
		this.assetManager = new AssetManager();
		this.assetManager.load("art/kim.png", Texture.class);
		this.assetManager.load("art/trump.png", Texture.class);
		this.assetManager.load("art/button-background.png", Texture.class);
		this.assetManager.finishLoading();

		GameManager gameManager = new GameManager();
		gameManager.assetManager = assetManager;
		gameManager.sceneManager = this;

		this.pushScreen(new PlayScreen(gameManager));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
