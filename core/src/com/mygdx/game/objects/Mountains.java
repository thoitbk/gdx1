package com.mygdx.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Asset;

public class Mountains extends AbstractGameObject {

    private TextureRegion regMountainLeft;
    private TextureRegion regMountainRight;

    private int length;

    public Mountains(int length) {
        this.length = length;
        init();
    }

    private void init() {
        dimension.set(10, 2);
        regMountainLeft = Asset.instance.levelDecoration.mountainLeft;
        regMountainRight = Asset.instance.levelDecoration.mountainRight;

        origin.x = -dimension.x * 2;
        length += dimension.x * 2;


    }

    private void drawMountain(SpriteBatch spriteBatch, float offsetX, float offsetY, float tintColor) {
        TextureRegion reg = null;
        spriteBatch.setColor(tintColor, tintColor, tintColor, 1);

        float relX = dimension.x * offsetX;
        float relY = dimension.y * offsetY;

        int mountainLength = 0;
        mountainLength += MathUtils.ceil(length / (2 * dimension.x));
        mountainLength += MathUtils.ceil(0.5f + offsetX);

        for (int i = 0; i < mountainLength; i++) {
            reg = regMountainLeft;
            spriteBatch.draw(reg.getTexture(), origin.x + relX, origin.y + position.y + relY,
                    origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
                    reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
            relX += dimension.x;

            reg = regMountainRight;
            spriteBatch.draw(reg.getTexture(), origin.x + relX, origin.y + position.y + relY,
                    origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation,
                    reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
            relX += dimension.x;
        }

        spriteBatch.setColor(1, 1, 1, 1);
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        drawMountain(spriteBatch, 0.5f, 0.5f, 0.5f);
        drawMountain(spriteBatch, 0.25f, 0.25f, 0.7f);
        drawMountain(spriteBatch, 0.0f, 0.0f, 0.9f);
    }
}
