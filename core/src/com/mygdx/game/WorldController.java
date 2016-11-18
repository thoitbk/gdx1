package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.utils.CameraHelper;
import com.mygdx.game.utils.Constants;

public class WorldController extends InputAdapter {

    private static final String TAG = WorldController.class.getName();

    public CameraHelper cameraHelper;

    public Level level;
    public int lives;
    public int score;

    public WorldController() {
        init();
    }

    private void init() {
        Gdx.input.setInputProcessor(this);
        cameraHelper = new CameraHelper();
        lives = Constants.LIVES_START;
        initLevel();
    }

    private void initLevel() {
        score = 0;
        level = new Level(Constants.LEVEL_01);
    }

    public void update(float deltaTime) {

        handleInput(deltaTime);
        cameraHelper.update(deltaTime);
    }

    private void handleInput(float deltaTime) {
        float cameraMoveSpeed = 5 * deltaTime;
        float cameraMoveAccelerator = 5f;

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) cameraMoveSpeed *= cameraMoveAccelerator;
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) moveCamera(0, cameraMoveSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) moveCamera(0, -cameraMoveSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveCamera(-cameraMoveSpeed, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveCamera(cameraMoveSpeed, 0);

        float cameraZoomSpeed = 1 * deltaTime;
        float cameraZoomAccelerator = 5f;

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) cameraZoomSpeed *= cameraZoomAccelerator;
        if (Gdx.input.isKeyPressed(Input.Keys.COMMA)) zoomCamera(cameraZoomSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.PERIOD)) zoomCamera(-cameraZoomSpeed);
        if (Gdx.input.isKeyPressed(Input.Keys.SLASH)) cameraHelper.setZoom(1);
    }

    private void moveCamera(float x, float y) {
        x += cameraHelper.getPosition().x;
        y += cameraHelper.getPosition().y;

        cameraHelper.setPosition(x, y);
    }

    private void zoomCamera(float zoom) {
        cameraHelper.addZoom(zoom);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.R) {
            init();
        }

        return false;
    }
}
