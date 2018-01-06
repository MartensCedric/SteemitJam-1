package com.martenscedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import java.util.List;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

public class PlayScreen extends StageScreen {

    private Batch batch;
    private Phone phone;
    private GameManager gameManager;
    private AssetManager assetManager;

    public PlayScreen(GameManager gameManager, List<Tweet> trumpTweets, List<Tweet> kimTweets) {
        super(gameManager);
        this.gameManager = gameManager;
        this.assetManager = gameManager.assetManager;
        this.batch = new SpriteBatch();
        this.phone = new Phone(gameManager, Utils.getDefaultSkin());
        this.phone.setX(WIDTH * 0.5f);
        this.phone.setY(HEIGHT/2 - phone.getHeight()/2 -10);

        for(int i = 0; i < trumpTweets.size(); i++)
        {
            phone.addTweet(trumpTweets.get(i));

            if(kimTweets.size() > i)
            {
                phone.addTweet(kimTweets.get(i));
            }
        }
        this.getStage().addActor(phone);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(254f/255f, 231f/255f, 97f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Texture textureBtnBackground = assetManager.get("art/button-background.png", Texture.class);
        Texture textureNukeButton = assetManager.get("art/nuke-button.png", Texture.class);
        Texture texturePhoneCover = assetManager.get("art/phone-cover.png", Texture.class);
        Texture textureSwitterBanner = assetManager.get("art/switter-banner.png", Texture.class);

        batch.begin();
        batch.draw(textureBtnBackground, 50, HEIGHT/2 - textureBtnBackground.getHeight()/2);
        batch.draw(textureNukeButton, 50, HEIGHT/2 - textureNukeButton.getHeight()/2);
        batch.draw(texturePhoneCover, 380, HEIGHT/2 - texturePhoneCover.getHeight()/2 + 1);
        batch.draw(textureSwitterBanner, 400, 520);
        batch.end();
        super.render(delta);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        super.show();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
