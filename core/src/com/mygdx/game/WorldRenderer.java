package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.utils.Constants;

public class WorldRenderer implements Disposable {

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private WorldController worldController;

    public WorldRenderer(WorldController worldController) {
        this.worldController = worldController;
        init();
    }

    private void init() {
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
    }

    public void render() {
        renderWorld(spriteBatch);
    }

    private void renderWorld(SpriteBatch spriteBatch) {
        worldController.cameraHelper.apply(camera);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        worldController.level.render(spriteBatch);
        spriteBatch.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = Constants.VIEWPORT_HEIGHT / height * width;
        camera.update();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}
