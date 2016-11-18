package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.utils.Constants;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Asset implements Disposable, AssetErrorListener {

    private static final String TAG = Asset.class.getName();

    public static final Asset instance = new Asset();

    public AssetBunny bunny;
    public AssetRock rock;
    public AssetCoin coin;
    public AssetFeather feather;
    public AssetLevelDecoration levelDecoration;

    private AssetManager assetManager;

    private Asset() {

    }

    public void init(AssetManager assetManager) {
        this.assetManager = assetManager;

        assetManager.setErrorListener(this);
        assetManager.load(Constants.TEXTURE_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();

        Gdx.app.log(TAG, "number of textures: " + assetManager.getAssetNames().size);
        for (String assetName : assetManager.getAssetNames()) {
            Gdx.app.log(TAG, "asset: " + assetName);
        }

        TextureAtlas textureAtlas = assetManager.get(Constants.TEXTURE_ATLAS);

        for (Texture t : textureAtlas.getTextures()) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }

        bunny = new AssetBunny(textureAtlas);
        rock = new AssetRock(textureAtlas);
        coin = new AssetCoin(textureAtlas);
        feather = new AssetFeather(textureAtlas);
        levelDecoration = new AssetLevelDecoration(textureAtlas);
    }

    @Override
    public void error(AssetDescriptor asset, Throwable throwable) {
        Gdx.app.error(TAG, asset.fileName + " " + throwable.toString());
    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }

    public class AssetBunny {
        public final AtlasRegion head;

        public AssetBunny(TextureAtlas textureAtlas) {
            head = textureAtlas.findRegion("bunny_head");
        }
    }

    public class AssetRock {
        public final AtlasRegion rockEdge;
        public final AtlasRegion rockMiddle;

        public AssetRock(TextureAtlas textureAtlas) {
            rockEdge = textureAtlas.findRegion("rock_edge");
            rockMiddle = textureAtlas.findRegion("rock_middle");
        }
    }

    public class AssetCoin {
        public final AtlasRegion coin;

        public AssetCoin(TextureAtlas textureAtlas) {
            coin = textureAtlas.findRegion("item_gold_coin");
        }
    }

    public class AssetFeather {
        public final AtlasRegion feather;

        public AssetFeather(TextureAtlas textureAtlas) {
            feather = textureAtlas.findRegion("item_feather");
        }
    }

    public class AssetLevelDecoration {
        public final AtlasRegion cloud01;
        public final AtlasRegion cloud02;
        public final AtlasRegion cloud03;
        public final AtlasRegion mountainLeft;
        public final AtlasRegion mountainRight;
        public final AtlasRegion waterOverlay;

        public AssetLevelDecoration(TextureAtlas textureAtlas) {
            cloud01 = textureAtlas.findRegion("cloud01");
            cloud02 = textureAtlas.findRegion("cloud02");
            cloud03 = textureAtlas.findRegion("cloud03");
            mountainLeft = textureAtlas.findRegion("mountain_left");
            mountainRight = textureAtlas.findRegion("mountain_right");
            waterOverlay = textureAtlas.findRegion("water_overlay");
        }
    }
}
