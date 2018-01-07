package com.martenscedric;

import com.badlogic.gdx.Gdx;
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
		this.assetManager.load("art/nuke-button.png", Texture.class);
		this.assetManager.load("art/phone-cover.png", Texture.class);
		this.assetManager.load("art/switter-banner.png", Texture.class);
		this.assetManager.load("art/button-press.png", Texture.class);
		this.assetManager.load("art/noise.png", Texture.class);
		this.assetManager.load("art/battery.png", Texture.class);
		this.assetManager.load("art/switch_on.png", Texture.class);
		this.assetManager.load("art/switch_off.png", Texture.class);
		this.assetManager.load("art/button-background_animation.png", Texture.class);
		this.assetManager.load("art/failure.png", Texture.class);

		this.assetManager.finishLoading();

		GameManager gameManager = new GameManager();
		gameManager.assetManager = assetManager;
		gameManager.sceneManager = this;
		gameManager.soundMap.put("button-press", Gdx.audio.newSound(Gdx.files.internal("audio/button-press.wav")));
		gameManager.soundMap.put("switch-flick", Gdx.audio.newSound(Gdx.files.internal("audio/switch-flick.wav")));
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
