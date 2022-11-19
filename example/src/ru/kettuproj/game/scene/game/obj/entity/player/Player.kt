package ru.kettuproj.game.scene.game.obj.entity.player

import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.type.CollisionObject
import ru.kettuproj.game.input.ActionButtons
import ru.kettuproj.game.scene.game.obj.common.Flashlight

class Player : CollisionObject() {

    private val playerVelocity = Vector2(0f,0f)
    private val flashlight = Flashlight()

    override fun create() {
        sprite = Anvil.assets.atlas.getSprite("Player")
        setSpriteCollision()
        createObject(flashlight, "flashlight")

        flashlight.translate(32f,0f)

    }

    override fun update() {

        playerVelocity.add(0f, Anvil.input.buttonState(ActionButtons.MOVE_UP.name))
        playerVelocity.add(0f, -Anvil.input.buttonState(ActionButtons.MOVE_DOWN.name))
        playerVelocity.add(Anvil.input.buttonState(ActionButtons.MOVE_RIGHT.name), 0f)
        playerVelocity.add(-Anvil.input.buttonState(ActionButtons.MOVE_LEFT.name), 0f)
        move(playerVelocity)

        lookAt(scene.getCursor())

        playerVelocity.set(0f,0f)

        flashlight.rotation = rotation

        super.update()
    }
}