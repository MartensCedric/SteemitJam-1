package com.martenscedric.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.martenscedric.SteemitJam;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.foregroundFPS = 60;
		config.backgroundFPS = 60;
		config.width = 800;
		config.height = 600;
		config.title = "My bigger button";
		config.addIcon("icons/trump-128.png", Files.FileType.Internal);
		config.addIcon("icons/trump-32.png", Files.FileType.Internal);
		config.addIcon("icons/trump-16.png", Files.FileType.Internal);
		new LwjglApplication(new SteemitJam(), config);
	}
}
