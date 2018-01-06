package com.martenscedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

public class PlayScreen extends StageScreen {

    private Batch batch;
    private Phone phone;
    private GameManager gameManager;

    public PlayScreen(GameManager gameManager) {
        super(gameManager);
        this.gameManager = gameManager;
        this.batch = new SpriteBatch();
        this.phone = new Phone(gameManager, Utils.getDefaultSkin());
        this.phone.setX(WIDTH * 0.5f);
        this.phone.setY(HEIGHT/2 - phone.getHeight()/2);

        for(int i = 0; i < 25; i++)
        {
            Tweet tweet = new Tweet();
            tweet.setAuthor(Author.values()[i % 2]);
            tweet.setContents("Message number : " + i);
            phone.addTweet(tweet);
        }
        /**
        //ImageButton nukeButton = new ImageButton(Utils.getDefaultSkin());
        nukeButton.setX(50);
        nukeButton.setY(WIDTH/2 - 150);
        nukeButton.setWidth(300);
        nukeButton.setHeight(300);*/
        this.getStage().addActor(phone);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(254f/255f, 231f/255f, 97f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Texture textureBtnBackground = gameManager.assetManager.get("art/button-background.png", Texture.class);
        batch.begin();
        batch.draw(textureBtnBackground, 50, HEIGHT/2 - textureBtnBackground.getHeight()/2);
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
