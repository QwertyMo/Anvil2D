package ru.kettuproj.game.scene.game.obj.common

import ru.kettuproj.core.obj.type.ShapeObject

class Box : ShapeObject() {

    override fun create() {
        size.set(1f, 1f)
        dynamicRotation = true
    }

    override fun render() {

    }

    override fun logic() {

    }

}