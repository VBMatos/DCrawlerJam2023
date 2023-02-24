package com.game.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ModelLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.game.DCrawlerJam;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    private final DCrawlerJam game;

    //Fixed resolution
    public static final int V_WIDTH = 1080;
    public static final int V_HEIGHT = 720;

    private PerspectiveCamera camera;
    private CameraInputController cameraController;
    private Environment environment;
    private ModelBatch modelBatch;
    private Model model;
    private AssetManager assets;

    private List<ModelInstance> lsInstances = new ArrayList<>();
    private boolean loading;

    public SpriteBatch batch;

    public GameScreen(DCrawlerJam game) {
        this.game = game;

        modelBatch = new ModelBatch();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(10f, 10f, 10f);
        camera.lookAt(0,0,0);
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        assets = new AssetManager();
        assets.load("models/dcrawler/dcrawler.g3db", Model.class);
        loading = true;

        cameraController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraController);
    }

    @Override
    public void show() {

    }

    private void doneLoading(){
        Model dungeonModel = assets.get("models/dcrawler/dcrawler.g3db", Model.class);
        ModelInstance instance = new ModelInstance(dungeonModel);
        lsInstances.add(instance);
        loading = false;
    }

    @Override
    public void render(float delta) {
        if (loading && assets.update())
            doneLoading();

        camera.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(camera);
        modelBatch.render(lsInstances, environment);
        modelBatch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        modelBatch.dispose();
        model.dispose();
    }
}
