package ru.geekbrains.dungeon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import ru.geekbrains.dungeon.helpers.Assets;
import ru.geekbrains.dungeon.game.GameController;

public class Hero extends Unit {
    private String name;

    private int coins = 50;

    public Hero(GameController gc) {
        super(gc, 1, 1, 10);
        this.name = "Sir Mullih";
        this.hpMax = 100;
        this.hp = this.hpMax;
        this.texture = Assets.getInstance().getAtlas().findRegion("knight");
        this.textureHp = Assets.getInstance().getAtlas().findRegion("hp");
    }

    public void update(float dt) {
        super.update(dt);
        if (Gdx.input.justTouched() && canIMakeAction()) {
            Monster m = gc.getUnitController().getMonsterController().getMonsterInCell(gc.getCursorX(), gc.getCursorY());
            if (m != null && canIAttackThisTarget(m)) {
                attack(m);
            } else {
                goTo(gc.getCursorX(), gc.getCursorY());
            }
        }
    }

    @Override
    public void attack(Unit target) {
        if (target.takeDamage(BattleCalc.attack(this, target)))
            coins += MathUtils.random(1, 3);
        this.takeDamage(BattleCalc.checkCounterAttack(this, target));
        turns--;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

}
