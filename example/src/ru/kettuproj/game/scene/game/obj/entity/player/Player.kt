package ru.kettuproj.game.scene.game.obj.entity.player

import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.type.CollisionObject
import ru.kettuproj.game.input.ActionButtons

class Player : CollisionObject() {

    private val playerVelocity = Vector2(0f,0f)
    private val sprite = PlayerSprite()
    val speed = 3f

    override fun create() {
        setSize(8f,8f)
        createObject(sprite, "player_collision")
        sprite.size.set(getSize().x, getSize().y)
    }

    override fun logic() {
        playerVelocity.add(0f, Anvil.input.buttonState(ActionButtons.MOVE_UP.name) * speed)
        playerVelocity.add(0f, -Anvil.input.buttonState(ActionButtons.MOVE_DOWN.name)* speed)
        playerVelocity.add(Anvil.input.buttonState(ActionButtons.MOVE_RIGHT.name)* speed, 0f)
        playerVelocity.add(-Anvil.input.buttonState(ActionButtons.MOVE_LEFT.name)* speed, 0f)
        move(playerVelocity)

        lookAt(scene.getCursor())

        playerVelocity.set(0f,0f)
    }

    override fun render() {

    }
}