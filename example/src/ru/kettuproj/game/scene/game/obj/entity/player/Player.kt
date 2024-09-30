package ru.kettuproj.game.scene.game.obj.entity.player

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.type.shape.polygon.SquareObject
import ru.kettuproj.game.input.ActionButtons
import ru.kettuproj.game.scene.game.obj.common.Colis

class Player : SquareObject() {

    private val playerVelocity = Vector2(0f,0f)
    private val collision = Colis()
    val speed = 3f

    override fun create() {
        size.set(8f,8f)
        color = Color.GREEN
        createObject(collision, "col")
        collision.size.set(size.x, size.y)
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