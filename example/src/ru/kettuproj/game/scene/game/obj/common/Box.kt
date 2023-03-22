package ru.kettuproj.game.scene.game.obj.common

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.sprite.AnvilSprite
import ru.kettuproj.core.obj.type.SpriteObject
import ru.kettuproj.game.input.ActionButtons

class Box : SpriteObject() {

    override fun create() {
        sprite = AnvilSprite("obj", "obj_tint")
        size.set(4f, 4f)
        dynamicRotation = true
    }

    override fun logic() {

    }

    override fun render() {
        println(debugPosition())
    }
}