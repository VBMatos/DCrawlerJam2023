package com.game.screens.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.DCrawlerJam;
import com.game.enums.EnumGameState;
import com.game.util.UIFactory;

public class MainMenuScreen implements Screen {
    private final DCrawlerJam game;

    //Fixed resolution
    public static final int V_WIDTH = 1280;
    public static final int V_HEIGHT = 720;

    private Stage stage;

    private OrthographicCamera camera;
    private Viewport viewport;

    private Texture bgTexture;

    private ImageButton btnPlay;
    private ImageButton btnExit;

    public MainMenuScreen(DCrawlerJam game) {
        this.game = game;

        float screenWitdh = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(V_WIDTH, V_HEIGHT);
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, camera);

        bgTexture = new Texture(Gdx.files.internal("bg/main_menu.png"));

        btnPlay = UIFactory.createImageButton(new Texture(Gdx.files.internal("ui/btn_play.png")));
        btnPlay.setPosition(screenWitdh / 2 - btnPlay.getWidth() / 2 , screenHeight / 2 - btnPlay.getHeight() / 2);
        btnPlay.addListener(UIFactory.createChangeScreenListener(EnumGameState.GAME, game));

        btnExit = UIFactory.createImageButton(new Texture(Gdx.files.internal("ui/btn_exit.png")));
        btnExit.setPosition(btnPlay.getX(), btnPlay.getY() - 90);
        btnExit.addListener(UIFactory.createExitListener());

        stage = new Stage(new ScreenViewport()); //Set up a stage for the ui
        stage.addActor(btnPlay); //Add buttons to the stage to perform rendering and take input.
        stage.addActor(btnExit);

        Gdx.input.setInputProcessor(stage); //Start taking input from the ui
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(bgTexture, 0, 0); //Render the bg image
        game.batch.end();

        stage.act(Gdx.graphics.getDeltaTime()); //Perform ui logic
        stage.draw(); //Draw the ui
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bgTexture.dispose();

        game.batch.dispose();
    }
}
