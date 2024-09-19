package ru.kettuproj.game.scene.game.obj.entity.player

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.sprite.AnvilSprite
import ru.kettuproj.core.obj.type.SpriteObject
import ru.kettuproj.game.input.ActionButtons
import ru.kettuproj.game.scene.game.obj.common.Box
import ru.kettuproj.game.scene.game.obj.common.Colis
import ru.kettuproj.game.scene.game.obj.common.Flashlight

class Player : SpriteObject() {

    private val playerVelocity = Vector2(0f,0f)
    private val colision = Colis()
    val speed = 3f

    override fun create() {
        sprite = AnvilSprite("obj", "obj_tint")
        createObject(colision, "col")
        colision.size.set(size.x, size.y)

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
        //println(renderRotation)
    }
}