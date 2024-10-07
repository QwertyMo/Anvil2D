package ru.kettuproj.game.scene.game.obj.entity.player

import com.badlogic.gdx.graphics.Color
import ru.kettuproj.core.obj.type.SpriteObject
import ru.kettuproj.core.obj.type.shape.polygon.SquareObject

class PlayerSprite : SquareObject() {

    override fun render() {

    }

    override fun logic() {

    }

    override fun create() {
        color = Color.GREEN
        size.set(8f,8f)
    }
}