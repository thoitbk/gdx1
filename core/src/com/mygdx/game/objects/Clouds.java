package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Asset;

public class Clouds extends AbstractGameObject {

    private Array<TextureRegion> regClouds;
    private Array<Cloud> clouds;

    private float length;

    public class Cloud extends AbstractGameObject {

        private TextureRegion regCloud;

        public Cloud() {

        }

        public void setRegion(TextureRegion region) {
            this.regCloud = region;
        }

        @Override
        public void render(SpriteBatch spriteBatch) {

            TextureRegion reg = regCloud;
            spriteBatch.draw(reg.getTexture(), position.x, position.y,
                    origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
                    reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
        }
    }

    public Clouds(float length) {
        this.length = length;
        init();
    }

    private void init() {
        dimension.set(3, 1);
        regClouds = new Array<TextureRegion>();

        regClouds.add(Asset.instance.levelDecoration.cloud01);
        regClouds.add(Asset.instance.levelDecoration.cloud02);
        regClouds.add(Asset.instance.levelDecoration.cloud03);

        int disFact = 5;
        int numClouds = (int) (length / disFact);
        clouds = new Array<Cloud>();
        for (int i = 0; i < numClouds; i++) {

            Cloud cloud = spawnCloud();
            cloud.position.x = i * disFact;
            clouds.add(cloud);
        }
    }

    private Cloud spawnCloud() {
        Cloud cloud = new Cloud();
        cloud.dimension.set(dimension);
        cloud.setRegion(regClouds.random());

        Vector2 pos = new Vector2();
        pos.x = length + 10;
        pos.y = 1.75f + MathUtils.random(0, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1);
        cloud.position.set(pos);

        return cloud;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        for (Cloud cloud : clouds) {
            cloud.render(spriteBatch);
        }
    }
}
