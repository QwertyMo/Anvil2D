package ru.kettuproj.core;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {

	public static void main(String[] args) {}

	public void launch(String[] args, Anvil game){
		new Lwjgl3Application(game, new Lwjgl3ApplicationConfiguration());
	}
}
