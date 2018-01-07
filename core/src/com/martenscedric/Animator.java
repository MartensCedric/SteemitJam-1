package com.martenscedric;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Map;

public class Animator
{
    private static final int FPS = 60;
    private static Animator animator;

    private Map<String, Animation<TextureRegion>> animationMap;
    public static Animator getAnimator()
    {
        if(animator == null)
        {
            animator = new Animator();
        }
        return animator;
    }

    public void initializeAnimator(AssetManager am)
    {
        animationMap = new HashMap<>();

        TextureRegion[][] ssElectricBackground = TextureRegion.split(am.get("art/button-background_animation.png", Texture.class), 300, 300);

        Animation<TextureRegion> electricBackground = new Animation<>(frameDuration(2, 500), to1DArray(ssElectricBackground));
        electricBackground.setPlayMode(Animation.PlayMode.LOOP);

        animationMap.put("electric_bg", electricBackground);
    }

    private static float frameDuration(int frameAmount, float duration)
    {
        return (duration / (float)frameAmount)/1000f;
    }

    private TextureRegion[] to1DArray(TextureRegion[][] array)
    {
        TextureRegion[] oneD = new TextureRegion[array.length * array[0].length];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                oneD[index++] = array[i][j];
            }
        }
        return oneD;
    }

    public Animation<TextureRegion> get(String animationName)
    {
        return animationMap.get(animationName);
    }
}
