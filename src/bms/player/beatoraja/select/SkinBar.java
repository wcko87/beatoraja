package bms.player.beatoraja.select;

import bms.player.beatoraja.MainState;
import bms.player.beatoraja.skin.*;
import bms.player.beatoraja.skin.Skin.SkinObjectRenderer;

import com.badlogic.gdx.graphics.g2d.*;

/**
 * 楽曲バー描画用スキンオブジェクト
 */
public class SkinBar extends SkinObject {

    /**
     * 選択時のBarのSkinImage
     */
    private SkinImage[] barimageon = new SkinImage[BAR_COUNT];
    /**
     * 非選択時のBarのSkinImage
     */
    private SkinImage[] barimageoff = new SkinImage[BAR_COUNT];
    
    public static final int BAR_COUNT = 60;

    /**
     * トロフィーのSkinImage。描画位置はBarの相対座標
     */
    private SkinImage[] trophy = new SkinImage[BARTROPHY_COUNT];
    
    public static final int BARTROPHY_COUNT = 3;

    /**
     * BarのSkinText。描画位置はBarの相対座標。
     * Indexは0:通常 1:新規 2:SongBar(通常) 3:SongBar(新規) 4:FolderBar(通常) 5:FolderBar(新規) 6:TableBar or HashBar
     * 7:GradeBar(曲所持) 8:(SongBar or GradeBar)(曲未所持) 9:CommandBar or ContainerBar 10:SearchWordBar
     * 3以降で定義されてなければ0か1を用いる
     */
    private SkinText[] text = new SkinText[BARTEXT_COUNT];
    
    public static final int BARTEXT_COUNT = 11;
    /**
     * レベルのSkinNumber。描画位置はBarの相対座標
     */
    private SkinNumber[] barlevel = new SkinNumber[BARLEVEL_COUNT];
    
    public static final int BARLEVEL_COUNT = 7;

    /**
     * 譜面ラベルのSkinImage。描画位置はBarの相対座標
     */
    private SkinImage[] label = new SkinImage[BARLABEL_COUNT];
    
    public static final int BARLABEL_COUNT = 5;

    private SkinDistributionGraph graph;

    private int position = 0;

    private TextureRegion[][] images;
    private int cycle;

    /**
     * ランプ画像
     */
    private SkinImage[] lamp = new SkinImage[BARLAMP_COUNT];
    /**
     * ライバルランプ表示時のプレイヤーランプ画像
     */
    private SkinImage[] mylamp = new SkinImage[BARLAMP_COUNT];
    /**
     * ライバルランプ表示時のライバルランプ画像
     */
    private SkinImage[] rivallamp = new SkinImage[BARLAMP_COUNT];

    public static final int BARLAMP_COUNT = 11;

    public SkinBar(int position) {
        this.position = position;
        this.setDestination(0, 0, 0, 0, 0, 0, 0, 255, 255, 255, 0, 0, 0, 0, 0, 0, new int[0]);
    }

    public SkinBar(int position, TextureRegion[][] images, int cycle) {
        setBarImages(images, cycle);
    }

    public void setBarImages(TextureRegion[][] images, int cycle) {
        this.images = images;
        this.cycle = cycle;
    }
    
    public void setBarImage(SkinImage[] onimage, SkinImage[] offimage) {
    	barimageon = onimage;
    	barimageoff = offimage;
    }

    public SkinImage makeBarImages(boolean on, int index) {
        if ((on ? barimageon[index] : barimageoff[index]) == null) {
            if (on) {
                barimageon[index] = new SkinImage(images, 0, cycle);
            } else {
                barimageoff[index] = new SkinImage(images, 0, cycle);
            }
        }
        return on ? barimageon[index] : barimageoff[index];
    }

    public SkinImage getBarImages(boolean on, int index) {
    	if(index >= 0 && index < barimageoff.length) {
            return on ? barimageon[index] : barimageoff[index];    		
    	}
    	return null;
    }

    public SkinImage getLamp(int id) {
        if(id >= 0 && id < this.lamp.length) {
            return this.lamp[id];
        }
        return null;
    }

    public SkinImage getPlayerLamp(int id) {
        if(id >= 0 && id < this.mylamp.length) {
            return this.mylamp[id];
        }
        return null;
    }

    public SkinImage getRivalLamp(int id) {
        if(id >= 0 && id < this.rivallamp.length) {
            return this.rivallamp[id];
        }
        return null;
    }

    public SkinImage getTrophy(int id) {
        if(id >= 0 && id < this.trophy.length) {
            return this.trophy[id];
        }
        return null;
    }

    public SkinText getText(int id) {
        if(id >= 0 && id < this.text.length) {
            return this.text[id];
        }
        return null;
    }

    public void setTrophy(int id, SkinImage trophy) {
        if(id >= 0 && id < this.trophy.length) {
            this.trophy[id] = trophy;
        }
    }

    public void setLamp(int id, SkinImage lamp) {
        if(id >= 0 && id < this.lamp.length) {
            this.lamp[id] = lamp;
        }
    }

    public void setPlayerLamp(int id, SkinImage mylamp) {
        if(id >= 0 && id < this.mylamp.length) {
            this.mylamp[id] = mylamp;
        }
    }

    public void setText(int id, SkinText text) {
        if(id >= 0 && id < this.text.length) {
            this.text[id] = text;
        }
    }

    public void setRivalLamp(int id, SkinImage rivallamp) {
        if(id >= 0 && id < this.rivallamp.length) {
            this.rivallamp[id] = rivallamp;
        }
    }

    @Override
    public void draw(SkinObjectRenderer sprite, long time, MainState state) {
        ((MusicSelector)state).getBarRender().render(sprite, (MusicSelectSkin) state.getSkin(), this, (int)time);
    }

    @Override
    public void dispose() {
    	disposeAll(barimageon);
    	disposeAll(barimageoff);
    	disposeAll(trophy);
    	disposeAll(text);
    	disposeAll(barlevel);
    	disposeAll(label);
        disposeAll(lamp);
        disposeAll(mylamp);
        disposeAll(rivallamp);
    }

    public SkinNumber getBarlevel(int id) {
        if(id >= 0 && id < this.barlevel.length) {
            return this.barlevel[id];
        }
        return null;
    }

    public void setBarlevel(int id, SkinNumber barlevel) {
        if(id >= 0 && id < this.barlevel.length) {
            this.barlevel[id] = barlevel;
        }
    }

    public int getPosition() {
        return position;
    }
    
    @Override
	protected boolean mousePressed(MainState state, int button, int x, int y) {
        return ((MusicSelector) state).getBarRender().mousePressed(this, button, x, y);
	}

    public SkinImage getLabel(int id) {
        if(id >= 0 && id < this.label.length) {
            return this.label[id];
        }
        return null;
    }

    public void setLabel(int id, SkinImage label) {
        if(id >= 0 && id < this.label.length) {
            this.label[id] = label;
        }
    }

	public SkinDistributionGraph getGraph() {
		return graph;
	}

	public void setGraph(SkinDistributionGraph graph) {
		this.graph = graph;
	}
}