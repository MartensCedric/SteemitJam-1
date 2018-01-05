package com.martenscedric;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

/**
 * Created by Cedric Martens on 2017-04-26.
 */
public abstract class StageScreen implements Screen
{
    private Stage stage;
    private SceneManager sceneManager;


    public StageScreen(GameManager gameManager) {
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
        this.sceneManager = gameManager.sceneManager;
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(getStage());
    }

    public SceneManager getSceneManager() {
        return sceneManager;
    }
}