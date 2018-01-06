package com.martenscedric;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

		List<Tweet> trumpTweets = new ArrayList<>();
		List<Tweet> kimTweets = new ArrayList<>();

		readTweetFile("trump.txt", trumpTweets);
		readTweetFile("kim.txt", kimTweets);

		this.assetManager.finishLoading();

		GameManager gameManager = new GameManager();
		gameManager.assetManager = assetManager;
		gameManager.sceneManager = this;

		this.pushScreen(new PlayScreen(gameManager, trumpTweets, kimTweets));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	private void readTweetFile(String filename, List<Tweet> list)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line = br.readLine();

			while (line != null) {
				list.add(parseTweet(line, filename.equals("trump.txt") ? Author.TRUMP :Author.KIM));
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Tweet parseTweet(String contents, Author author)
	{
		Tweet tweet = new Tweet();
		tweet.setAuthor(author);
		tweet.setContents(contents);
		tweet.setGeneratedHeat(0);
		tweet.setMinHeat(0);
		return tweet;
	}
}
