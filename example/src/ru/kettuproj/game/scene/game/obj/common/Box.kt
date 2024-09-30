package ru.kettuproj.game.scene.game.obj.common

import com.badlogic.gdx.graphics.Color
import ru.kettuproj.core.obj.type.shape.polygon.SquareObject

class Box : SquareObject() {

    override fun create() {
        size.set(20f, 20f)
        color = Color.BROWN
    }

    override fun render() {

    }

    override fun logic() {
        rotation+=1
    }

}