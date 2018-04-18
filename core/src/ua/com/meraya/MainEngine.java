package ua.com.meraya;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.I18NBundle;
import com.boontaran.games.StageGame;

import java.util.Locale;

import ua.com.meraya.levels.Level;
import ua.com.meraya.media.Media;
import ua.com.meraya.screens.IntroScreen;
import ua.com.meraya.screens.LevelListScreen;
import ua.com.meraya.screens.MainMenuScreen;
import ua.com.meraya.screens.SettingsScreen;
import ua.com.meraya.screens.StageSelectScreen;
import ua.com.meraya.utils.Data;


public class MainEngine extends Game {

	public static final int SHOW_BANNER = 1;
	public static final int HIDE_BANNER = 2;
	public static final int LOAD_INTERSTITIAL = 3;
	public static final int SHOW_INTERSTITIAL = 4;
	public static final int OPEN_MARKET = 5;
	public static final int SHARE = 6;

	private boolean loadingAssets = false;
	private AssetManager assetManager;

	public static TextureAtlas atlas;
	public  static BitmapFont font40;

	private I18NBundle bundle;
	private String path_to_atlas;

	private GameCallback gameCallBack;

	public static Media media;

	private MainMenuScreen intro;

	public static Data data;

	private LevelListScreen levelList;
	private Level level;
	private IntroScreen intro_logo;
	private StageSelectScreen selectScreen;
	private SettingsScreen settingsScreen;
	private int lastLevelId;

	public MainEngine(GameCallback gameCallBack) {
		this.gameCallBack = gameCallBack;
	}

	private void assetsLoaded(){
		atlas = assetManager.get(path_to_atlas, TextureAtlas.class);
		font40 = assetManager.get("font40.ttf", BitmapFont.class);

		showIntroScreen();
	}

	@Override
	public void create () {
		StageGame.setAppSize(800, 480);

		Gdx.input.setCatchBackKey(true);

		Locale locale = Locale.getDefault();
		bundle = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale);
		path_to_atlas = bundle.get("path");

		loadingAssets = true;
		assetManager = new AssetManager();
		assetManager.load(path_to_atlas, TextureAtlas.class);
		assetManager.load("musics/music1.ogg", Music.class);
		assetManager.load("musics/music2.mp3", Music.class);
		assetManager.load("musics/level_failed.ogg", Music.class);
		assetManager.load("musics/level_win.ogg", Music.class);
		assetManager.load("sounds/engine.ogg", Sound.class);
		assetManager.load("sounds/jump.ogg", Sound.class);
		assetManager.load("sounds/level_completed.ogg", Sound.class);
		assetManager.load("sounds/fail.ogg", Sound.class);
		assetManager.load("sounds/crash.ogg", Sound.class);
		assetManager.load("sounds/click.ogg", Sound.class);

		FileHandleResolver resolver = new InternalFileHandleResolver();
		assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

		FreetypeFontLoader.FreeTypeFontLoaderParameter sizeParams = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
		sizeParams.fontFileName = "fonts/GROBOLD.ttf";
		sizeParams.fontParameters.size = 40;

		assetManager.load("font40.ttf", BitmapFont.class, sizeParams);

		media = new Media(assetManager);
		data = new Data();
	}

	@Override
	public void render () {
		if (loadingAssets){
			if (assetManager.update()){
				loadingAssets = false;
				assetsLoaded();
			}
		}
		super.render();
	}



	private void showMainScreen(){
		intro = new MainMenuScreen();
		setScreen(intro);

		intro.setCallback(new StageGame.Callback() {
			@Override
			public void call(int code) {
				if(code == MainMenuScreen.ON_PLAY){
					showStageSelect();
					hideMainScreen();
				} else if(code == MainMenuScreen.ON_BACK){
					exitApp();
				}else if (code == MainMenuScreen.ON_SHARE){
					gameCallBack.sendMessage(SHARE);
				}else if (code == MainMenuScreen.ON_RATE) {
					gameCallBack.sendMessage(OPEN_MARKET);
				}else if (code == MainMenuScreen.ON_SETTINGS) {
				}else if (code == MainMenuScreen.ON_ACHIVE) {
					System.out.println("open achive");
				}else if (code == MainMenuScreen.ON_ABOUT) {
					System.out.println("open about");
				}
			}
		});

		media.playMusic("music1.ogg", true);
	}
	private void hideMainScreen(){
		intro = null;
	}

	private void showStageSelect(){
		selectScreen = new StageSelectScreen();
		setScreen(selectScreen);

		selectScreen.setCallback(new StageGame.Callback(){
			@Override
			public void call(int code) {
				if(code == StageSelectScreen.ON_BACK){
					showMainScreen();
					//hideSelectScreen();
				} else if(code == StageSelectScreen.ON_SELECT_ONE){
					showLevelList();
					//hideLevelSelect
				}else if(code == StageSelectScreen.ON_SELECT_TWO){
					System.out.println("You choice is stage 2 ");
					//showLevelList();
					//hideIntro();
				}else if(code == StageSelectScreen.ON_SELECT_THREE){
					System.out.println("You choice is stage 3 ");
					//showLevelList();
					//hideIntro();
				}else if(code == StageSelectScreen.ON_SELECT_FOUR){
					System.out.println("You choice is stage 4 ");
					//showLevelList();
					//hideIntro();
				}else if(code == StageSelectScreen.ON_OPEN_STORE) {
					System.out.println("open store");
					//showLevelList();
					//hideIntro();
				}
			}
		});
	}
	private void hideStageSelect(){}


	private void showLevelList(){
		levelList = new LevelListScreen();
		setScreen(levelList);

		levelList.setCallback(new StageGame.Callback(){
			@Override
			public void call(int code) {
				if(code == LevelListScreen.ON_BACK){
					showStageSelect();
					hideLevelList();
				} else if(code == LevelListScreen.ON_LEVEL_SELECTED){
					showLevel(levelList.getSelectedLevelId());
					hideLevelList();
				}else if(code == LevelListScreen.ON_OPEN_STORE){
					System.out.println("open store");
				}
			}
		});
		gameCallBack.sendMessage(SHOW_BANNER);
			media.playMusic("music1.ogg", true);
	}
	private void hideLevelList(){
		levelList = null;
		gameCallBack.sendMessage(HIDE_BANNER);
	}

	private void showLevel(int id){
		media.stopMusic("music1.ogg");

		lastLevelId = id;
		switch (id) {
			case 1:
				level = new Level("level1");
				break;
			case 2:
				level = new Level("level2");
				break;
			default:
				level = new Level("level" + id);
				break;
		}

		if (level.getMusicName() == null){
			level.setMusic("music2.mp3");
		}

		setScreen(level);

		level.setCallback(new StageGame.Callback(){
			@Override
			public void call(int code) {
				if (code == Level.ON_RESTART){
					gameCallBack.sendMessage(HIDE_BANNER);
					gameCallBack.sendMessage(SHOW_INTERSTITIAL);
					media.stopMusic("level_failed.ogg");
					media.stopMusic("level_win.ogg");
					hideLevel();
					showLevel(lastLevelId);
				}else if (code == Level.ON_QUIT){
					gameCallBack.sendMessage(SHOW_INTERSTITIAL);
					media.stopMusic("level_failed.ogg");
					media.stopMusic("level_win.ogg");
					hideLevel();
					showLevelList();
				}else if (code == Level.ON_COMPLETED){
					updateProgress();
					gameCallBack.sendMessage(SHOW_INTERSTITIAL);
					gameCallBack.sendMessage(SHOW_BANNER);
					media.stopMusic("level_win.ogg");
					hideLevel();
					showLevelList();
				}else if (code == Level.ON_PAUSED){
					gameCallBack.sendMessage(SHOW_BANNER);
				}else if (code == Level.ON_RESUME){
					gameCallBack.sendMessage(HIDE_BANNER);
				}else if (code == Level.ON_FAILED){
					gameCallBack.sendMessage(SHOW_BANNER);
					media.playMusic("level_failed.ogg", false);
				}
			}
		});
		gameCallBack.sendMessage(LOAD_INTERSTITIAL);
	}
	private void hideLevel(){
		level.dispose();
		level = null;
	}

	protected void updateProgress(){
		int newProgress = lastLevelId + 1;
		if(newProgress > data.getProgress()){
			data.setProgress(newProgress);
		}
	}

	@Override
	public void dispose () {
		assetManager.dispose();
		super.dispose();
	}

	private void showIntroScreen() {
		intro_logo = new IntroScreen();
		setScreen(intro_logo);

		intro_logo.setCallback(new StageGame.Callback() {
			@Override
			public void call(int code) {
				if (code == IntroScreen.START) {
					showMainScreen();
				}
			}
		});
	}

	public void	restart(){
		dispose();
		create();
	}

	private void exitApp(){
		Gdx.app.exit();
	}
}
