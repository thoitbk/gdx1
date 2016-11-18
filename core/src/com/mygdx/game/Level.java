package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.objects.AbstractGameObject;
import com.mygdx.game.objects.Clouds;
import com.mygdx.game.objects.Mountains;
import com.mygdx.game.objects.Rock;
import com.mygdx.game.objects.WaterOverlay;

public class Level {

    private static final String TAG = Level.class.getName();

    public enum BlockType {
        EMPTY(0, 0, 0),
        ROCK(0, 255, 0),
        PLAYER_SPAWN(255, 255, 255),
        FEATHER(255, 0, 255),
        GOLD(255, 255, 0);

        private int color;

        private BlockType(int r, int g, int b) {
            color = r << 24 | g << 16 | b << 8 | 0xff;
        }

        public boolean sameColor(int color) {
            return this.color == color;
        }

        public int getColor() {
            return color;
        }
    }

    public Array<Rock> rocks;
    public Clouds clouds;
    public WaterOverlay waterOverlay;
    public Mountains mountains;

    public Level(String fileName) {
        init(fileName);
    }

    private void init(String fileName) {

        rocks = new Array<Rock>();
        Pixmap pixmap = new Pixmap(Gdx.files.internal(fileName));
        int lastPixel = -1;

        for (int pixelY = 0; pixelY < pixmap.getHeight(); pixelY++) {
            for (int pixelX = 0; pixelX < pixmap.getWidth(); pixelX++) {
                AbstractGameObject obj = null;
                float offsetHeight = 0;
                int baseHeight = pixmap.getHeight() - pixelY;
                int currentPixel = pixmap.getPixel(pixelX, pixelY);

                if (BlockType.EMPTY.sameColor(currentPixel)) {

                } else if (BlockType.ROCK.sameColor(currentPixel)) {
                    if (lastPixel != currentPixel) {
                        obj = new Rock();
                        float heightIncreaseFactor = 0.25f;
                        offsetHeight = -2.5f;
                        obj.position.set(pixelX, baseHeight * obj.dimension.y * heightIncreaseFactor + offsetHeight);
                        rocks.add((Rock) obj);
                    } else {
                        rocks.get(rocks.size - 1).increaseLength(1);
                    }
                } else if (BlockType.FEATHER.sameColor(currentPixel)) {

                } else if (BlockType.GOLD.sameColor(currentPixel)) {

                } else if (BlockType.PLAYER_SPAWN.sameColor(currentPixel)) {

                } else {
                    int r = 0xff & (currentPixel >>> 24);
                    int g = 0xff & (currentPixel >>> 16);
                    int b = 0xff & (currentPixel >>> 8);
                    int a = 0xff & currentPixel;

                    Gdx.app.error(TAG, pixelX + ", " + pixelY + ", " + r + ", " + g + ", " + b + ", " + a);
                }

                lastPixel = currentPixel;
            }
        }

        clouds = new Clouds(pixmap.getWidth());
        clouds.position.set(0, 2);
        mountains = new Mountains(pixmap.getWidth());
        mountains.position.set(-1, -1);
        waterOverlay = new WaterOverlay(pixmap.getWidth());
        waterOverlay.position.set(0, -3.75f);

        pixmap.dispose();
    }

    public void render(SpriteBatch spriteBatch) {
        mountains.render(spriteBatch);
        for (Rock rock : rocks) {
            rock.render(spriteBatch);
        }
        waterOverlay.render(spriteBatch);
        clouds.render(spriteBatch);
    }
}
