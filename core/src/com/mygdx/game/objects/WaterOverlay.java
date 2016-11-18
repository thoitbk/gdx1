package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Asset;

public class WaterOverlay extends AbstractGameObject {

    private TextureRegion regWaterOverlay;
    private float length;

    public WaterOverlay(float length) {
        this.length = length;
        init();
    }

    private void init() {
        dimension.set(length * 2, 3);

        regWaterOverlay = Asset.instance.levelDecoration.waterOverlay;

        origin.x = -dimension.x / 2;
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        TextureRegion reg = null;
        reg = regWaterOverlay;
        spriteBatch.draw(reg.getTexture(), origin.x + position.x, origin.y + position.y, origin.x,
                origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),
                reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
    }
}
