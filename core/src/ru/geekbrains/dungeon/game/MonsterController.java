package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import ru.geekbrains.dungeon.helpers.ObjectPool;

public class MonsterController extends ObjectPool<Monster> {
    private GameController gc;

    public MonsterController(GameController gc) {
        this.gc = gc;
    }

    @Override
    protected Monster newObject() {
        return new Monster(gc);
    }

    public void activate(int cellX, int cellY) {
        getActiveElement().activate(cellX, cellY);
    }

    public Monster getMonsterInCell(int cellX, int cellY) {
        for (Monster m : getActiveList()) {
            if (m.getCellX() == cellX && m.getCellY() == cellY) {
                return m;
            }
        }
        return null;
    }

    public void spawn (int num) {
        for (int i = 0; i < num; i++) {
            int cellX = -1;
            int cellY = -1;
            while (!gc.getGameMap().isCellPassable(cellX, cellY)) {
                cellX = MathUtils.random(0, gc.getGameMap().getCellsX() - 1);
                cellY = MathUtils.random(0, gc.getGameMap().getCellsY() - 1);
            }
            activate(cellX, cellY);
        }
    }

    public void update(float dt) {
        for (Monster m : getActiveList()) {
            m.update(dt);
        }
        checkPool();
    }

    public void render(SpriteBatch batch, BitmapFont font18) {
        for (Monster m : getActiveList()) {
            m.render(batch, font18);
        }
    }
}
