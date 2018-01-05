package com.martenscedric;

import com.badlogic.gdx.assets.AssetManager;

public class SteemitJam extends SceneManager {

	private AssetManager assetManager;
	@Override
	public void create () {
		super.create();
		this.assetManager = new AssetManager();

		GameManager gameManager = new GameManager();
		gameManager.assetManager = assetManager;
		gameManager.sceneManager = this;
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
