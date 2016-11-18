package com.mygdx.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.Asset;

public class Rock extends AbstractGameObject {

    private TextureRegion regEdge;
    private TextureRegion regMiddle;

    private int length;

    public Rock() {
        init();
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

        TextureRegion reg = null;

        float relX = 0;
        float relY = 0;

        reg = regEdge;
        relX -= dimension.x / 4;
        spriteBatch.draw(reg.getTexture(), position.x + relX, position.y + relY,
                origin.x, origin.y, dimension.x / 4, dimension.y, scale.x, scale.y,
                rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                reg.getRegionHeight(), false, false);

        reg = regMiddle;
        relX = 0;
        for (int i = 0; i < length; i++) {
            spriteBatch.draw(reg.getTexture(), position.x + relX, position.y + relY,
                    origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
                    rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                    reg.getRegionHeight(), false, false);
            relX += dimension.x;
        }

        reg = regEdge;
        spriteBatch.draw(reg.getTexture(), position.x + relX, position.y + relY,
                origin.x, origin.y, dimension.x / 4, dimension.y, scale.x, scale.y,
                rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
                reg.getRegionHeight(), true, false);
    }

    private void init() {
        dimension.set(1f, 1.5f);

        regEdge = Asset.instance.rock.rockEdge;
        regMiddle = Asset.instance.rock.rockMiddle;

        setLength(1);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void increaseLength(int amount) {
        setLength(length + amount);
    }
}
