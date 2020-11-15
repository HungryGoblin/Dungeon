package ru.geekbrains.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CharacterController {

    private static final int KEY_MOVE_UP = Input.Keys.UP;
    private static final int KEY_MOVE_DOWN = Input.Keys.DOWN;
    private static final int TURN_LEFT = Input.Keys.LEFT;
    private static final int TURN_RIGHT = Input.Keys.RIGHT;
    private static final int FIRE_MODE = Input.Keys.Q;

    private Hero character = null;
    private int keyMoveUp = KEY_MOVE_UP;
    private int keyMoveDown = KEY_MOVE_DOWN;
    private int keyTurnLeft = TURN_LEFT;
    private int keyTurnRight = TURN_RIGHT;
    private int keyFireMode = FIRE_MODE;

    public void checkKeyPressed(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            character.projectileController.activate(
                    character.position.x + 20,
                    character.position.y + 20,
                    MathUtils.cosDeg(character.getAngle()) * 200,
                    MathUtils.sinDeg(character.getAngle()) * 200);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            character.updateStep(new Vector2(
                    MathUtils.cosDeg(character.getAngle()),
                    MathUtils.sinDeg(character.getAngle())));
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            character.updateStep(new Vector2(
                    MathUtils.cosDeg(character.getAngle()) * -1,
                    MathUtils.sinDeg(character.getAngle()) * -1));
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            character.updateAngle(character.ANGLE_STEP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            character.updateAngle(character.ANGLE_STEP * -1);
        } else if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            character.projectileController.switchAutoFireMode();
        }

    }

    public void setControlButtons(int keyMoveUp, int keyMoveDown, int keyTurnLeft, int keyTurnRight,
                                  int keyFireMode) {
        this.keyMoveUp = keyMoveUp;
        this.keyMoveDown = keyMoveDown;
        this.keyTurnLeft = keyTurnLeft;
        this.keyTurnRight = keyTurnRight;
        this.keyFireMode = keyFireMode;
    }

    public void keyPressed(int key) {

    }

    public CharacterController(int keyMoveUp, int keyMoveDown, int keyTurnLeft, int keyTurnRight, int fireMode) {
        setControlButtons(keyMoveUp, keyMoveDown, keyTurnLeft, keyTurnRight, fireMode);
    }

    public CharacterController(Hero character) {
        this.character = character;
    }

}
