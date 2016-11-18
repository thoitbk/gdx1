package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.objects.AbstractGameObject;

public class CameraHelper {

    private static final String TAG = CameraHelper.class.getName();

    private static final float MAX_ZOOM_IN = 0.25f;
    private static final float MAX_ZOOM_OUT = 10f;

    private Vector2 position;
    private float zoom;
    private AbstractGameObject target;

    public CameraHelper() {
        position = new Vector2();
        zoom = 1.0f;
    }

    public void setPosition(float x, float y) {
        position.set(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setZoom(float zoom) {
        this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
    }

    public float getZoom() {
        return zoom;
    }

    public void addZoom(float z) {
        setZoom(zoom + z);
    }

    public AbstractGameObject getTarget() {
        return target;
    }

    public void setTarget(AbstractGameObject target) {
        this.target = target;
    }

    public boolean hasTarget() {
        return target != null;
    }

    public boolean hasTarget(AbstractGameObject t) {
        return hasTarget() && target.equals(t);
    }

    public void update(float deltaTime) {
        if (!hasTarget()) return;
        position.x = target.position.x + target.origin.x;
        position.y = target.position.y + target.origin.y;
    }

    public void apply(OrthographicCamera camera) {
        camera.position.x = position.x;
        camera.position.y = position.y;
        camera.zoom = zoom;
        camera.update();
    }
}
