package com.martenscedric;

public class StubScreen extends StageScreen {
    private GameManager gameManager;
    public StubScreen(GameManager gameManager) {
        super(gameManager);
        this.gameManager = gameManager;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        gameManager.sceneManager.pushScreen(new PlayScreen(gameManager));
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
