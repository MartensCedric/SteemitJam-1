package com.martenscedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import javax.rmi.CORBA.Util;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

public class PlayScreen extends StageScreen {

    private Batch batch;
    private ShapeRenderer shapeRenderer;
    private AssetManager assetManager;
    private Phone phone;
    private float battery = 1.0f;
    private float warningNuke = 0f;

    public PlayScreen(GameManager gameManager) {
        super(gameManager);
        this.assetManager = gameManager.assetManager;
        this.batch = new SpriteBatch();
        this.shapeRenderer = new ShapeRenderer();
        this.shapeRenderer.setAutoShapeType(true);
        this.phone = new Phone(gameManager, Utils.getDefaultSkin());
        this.phone.setX(WIDTH * 0.5f);
        this.phone.setY(HEIGHT/2 - phone.getHeight()/2 -10);
        this.getStage().addActor(phone);
    }

    @Override
    public void render(float delta) {

        battery -= delta/25.0f;

        warningNuke -= delta;
        if(warningNuke < 0)
        {
            warningNuke = 0;
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)
                && Gdx.input.getX() >= 100 && Gdx.input.getX() < 300
                && Gdx.input.getY() >= HEIGHT/2 - 100 && Gdx.input.getY() < HEIGHT/2 + 100)
        {
            warningNuke = 3f;
        }

        this.phone.update(delta);
        Gdx.gl.glClearColor(254f/255f, 231f/255f, 97f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Texture textureBtnBackground = assetManager.get("art/button-background.png", Texture.class);
        Texture textureNukeButton = assetManager.get("art/nuke-button.png", Texture.class);
        Texture texturePhoneCover = assetManager.get("art/phone-cover.png", Texture.class);
        Texture textureSwitterBanner = assetManager.get("art/switter-banner.png", Texture.class);
        Texture textureBattery = assetManager.get("art/battery.png", Texture.class);

        batch.begin();
        batch.draw(textureBtnBackground, 50, HEIGHT/2 - textureBtnBackground.getHeight()/2);
        batch.draw(textureNukeButton, 50, HEIGHT/2 - textureNukeButton.getHeight()/2);
        batch.draw(texturePhoneCover, 380, HEIGHT/2 - texturePhoneCover.getHeight()/2 + 1);
        batch.draw(textureSwitterBanner, 400, 520);
        batch.draw(textureBattery, 25, 25);
        batch.end();
        super.render(delta);
        batch.begin();
        if(phone.isNuked())
        {
            Texture textureButtonPress = assetManager.get("art/button-press.png", Texture.class);
            batch.draw(textureButtonPress, 0, HEIGHT/2 - textureButtonPress.getHeight()/2);

            Texture textureNoise = assetManager.get("art/noise.png", Texture.class);
            batch.draw(textureNoise, 400, HEIGHT/2 - textureNoise.getHeight()/2);
        }
        batch.end();

        if(battery > 0)
        {
            shapeRenderer.begin();
            Color colorLight = null;
            Color colorDark = null;
            if(battery > 0.6f)
            {
                colorLight = new Color(99f/255f, 199f/255f, 77f/255f, 1f);
                colorDark = new Color(62f/255f, 137f/255f, 72f/255f, 1f);
            }else if(battery > 0.25f)
            {
                colorLight = new Color(254f/255f, 231f/255f, 97f/255f, 1f);
                colorDark = new Color(254f/255f, 174f/255f, 52f/255f, 1f);
            }else{
                colorLight = new Color(228f/255f, 59f/255f, 68f/255f, 1f);
                colorDark = new Color(158f/255f, 40f/255f, 53f/255f, 1f);
            }

            shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(30, 30, 39, 102 * battery, colorLight, colorLight, colorLight, colorLight);
            shapeRenderer.rect(69, 30, 15, 102 * battery, colorDark, colorDark, colorDark, colorDark);
            shapeRenderer.end();
        }

        if(warningNuke > 0)
        {
            batch.begin();
            BitmapFont font = Utils.getFont();
            font.setColor(1, 1,1, warningNuke);
            font.draw(batch, "Don't press the nuke launching button!!\nYou must protect it!", Gdx.input.getX() - 100,
                    HEIGHT - Gdx.input.getY() + 40);
            batch.end();
        }

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
