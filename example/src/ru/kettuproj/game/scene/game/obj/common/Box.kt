package ru.kettuproj.game.scene.game.obj.common

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.sprite.AnvilSprite
import ru.kettuproj.core.obj.type.ShapeObject
import ru.kettuproj.core.obj.type.SpriteObject
import ru.kettuproj.game.input.ActionButtons

class Box : ShapeObject() {

    override fun create() {
        size.set(128f, 128f)
        dynamicRotation = true
    }

    override fun render() {

    }

    override fun logic() {

    }

}