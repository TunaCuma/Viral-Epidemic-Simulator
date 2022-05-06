package com.mygdx.viralepidemicsim;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.Helpers.GameInfo;
import com.mygdx.viralepidemicsim.SimulationV4UsedLibgdx.MyLibgdxTester.GameMain;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		//new Lwjgl3Application(new ViralEpidemicSim(), config);
		config.setTitle("VIRAL EPIDEMIC SIMULATOR");

		config.setWindowedMode(GameInfo.WIDTH, GameInfo.HEIGHT);

		config.setMaximized(true);
		
		
		new Lwjgl3Application(new GameMain(), config);
	}
}
