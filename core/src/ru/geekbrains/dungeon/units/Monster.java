package ru.geekbrains.dungeon.units;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import ru.geekbrains.dungeon.GameController;

public class Monster extends Unit {

    int retDamage = 0;

    public boolean isActive() {
        return hp > 0;
    }

    public Monster(TextureAtlas atlas, GameController gc) {
        super(gc, 5, 2, 10);
        this.texture = atlas.findRegion("monster");
        this.textureHp = atlas.findRegion("hp");
        this.hp = -1;
    }

    public void activate(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.hpMax = 10;
        this.hp = hpMax;
    }

    public int getRetDamage() {
        return retDamage;
    }

    public void setRetDamage(int retDamage) {
        this.retDamage = retDamage;
    }

    @Override
    public boolean takeDamage(int amount) {
        super.takeDamage(amount);
        if (hp > 0 && Math.random() <= 0.25)
            retDamage++;
        return hp <= 0;
    }

    public void update(float dt) {
    }
}
