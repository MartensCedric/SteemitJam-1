package com.martenscedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

public class PlayScreen extends StageScreen {

    private Batch batch;
    private Phone phone;

    public PlayScreen(GameManager gameManager) {
        super(gameManager);
        this.batch = new SpriteBatch();
        this.phone = new Phone(Utils.getDefaultSkin());
        this.phone.setX(WIDTH * 0.5f);
        this.phone.setY(HEIGHT/2 - phone.getHeight()/2);

        for(int i = 0; i < 25; i++)
        {
            Tweet tweet = new Tweet();
            tweet.setContents("Message number : " + i);
            phone.addTweet(tweet);
        }

        this.getStage().addActor(phone);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
