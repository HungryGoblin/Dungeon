package ru.geekbrains.dungeon;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Hero {

    public static BitmapFont font = new BitmapFont();


    final float SPEED = 100;
    final float ANGLE_STEP = 1;

    ProjectileController projectileController;
    Vector2 position;
    Vector2 velocity;
    private float angle = 0;
    private Vector2 step = null;
    private TextureRegion texture;
    private CharacterController characterController = new CharacterController(this);

    public Hero(TextureAtlas atlas, ProjectileController projectileController) {
        this.position = new Vector2(100, 100);
        this.velocity = new Vector2(0, 0);
        this.texture = atlas.findRegion("tank");
        this.projectileController = projectileController;
    }

    public void updateAngle(float angle) {
        if (Math.abs(this.angle) > 360)
            this.angle = 0;
        this.angle += angle;
    }

    public float getAngle() {
        return angle;
    }

    public void updateStep(Vector2 step) {
        this.step = step;
    }

    public void update(float dt) {
        characterController.checkKeyPressed(dt);
        if (step != null) {
            step = new Vector2(position.x, position.y).mulAdd(step, SPEED * dt);
            if (GameMap.isOnMap(step))
                position = step;
            step = null;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, texture.getRegionWidth() / 2, texture.getRegionHeight() / 2,
                texture.getRegionWidth(), texture.getRegionHeight(), 1, 1, angle);
        font.draw(batch, String.format("angle: %f", angle),100, 100);
    }
}
