package ua.com.meraya.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class Packer {

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.filterMin = Texture.TextureFilter.MipMapLinearNearest;
        settings.filterMag = Texture.TextureFilter.Linear;

        settings.paddingX = 2;
        settings.paddingY = 2;
        settings.maxHeight = 4096;
        settings.maxWidth = 4096;

        TexturePacker.process(settings, "raw_images_ru", "android/assets/images_ru", "pack");
        TexturePacker.process(settings, "raw_images_en", "android/assets/images_en", "pack");
    }
}
