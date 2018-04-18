package ua.com.meraya.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ua.com.meraya.GameCallback;
import ua.com.meraya.MainEngine;

public class DesktopLauncher {
	public DesktopLauncher() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		new LwjglApplication(new MainEngine(callback), config);
	}

	private GameCallback callback = new GameCallback() {
		@Override
		public void sendMessage(int message) {
			System.out.println("DesktopLauncher sendMessage: " + message);
		}
	};

	public static void main(String[] args) {
		new DesktopLauncher();
	}
}
