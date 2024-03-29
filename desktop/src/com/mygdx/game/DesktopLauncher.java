package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Piazza Panic");
        config.setWindowedMode(840, 820);
        config.useVsync(true);
        config.setForegroundFPS(60);
        config.setWindowIcon(Files.FileType.Internal, "windowIcon.png");
        new Lwjgl3Application(new PiazzaPanic(), config);
    }
}
