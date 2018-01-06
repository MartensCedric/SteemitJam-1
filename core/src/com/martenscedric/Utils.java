package com.martenscedric;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Utils
{
    private static Skin defaultSkin;
    private static BitmapFont font;

    public static Skin getDefaultSkin()
    {
        if(defaultSkin == null)
        {
            defaultSkin = new Skin(Gdx.files.internal("skins/default/skin/uiskin.json"));
        }
        return defaultSkin;
    }

    public static BitmapFont getFont()
    {
        if(font == null)
            font = new BitmapFont();

        return font;
    }
}
