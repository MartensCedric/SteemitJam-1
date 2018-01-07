package com.martenscedric;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class GameManager
{
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    public AssetManager assetManager;
    public SceneManager sceneManager;
    public HashMap<String, Sound> soundMap = new HashMap<>();
}
