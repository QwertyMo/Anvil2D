package ru.kettuproj.core.obj.type.shape.polygon

import ru.kettuproj.core.obj.type.shape.PolygonObject

open class SquareObject : PolygonObject() {

    init{
        polygon = floatArrayOf(
            0.5f, 0.5f,
            0.5f, -0.5f,
            -0.5f, -0.5f,
            -0.5f, 0.5f
        )
    }

    override fun render() {

    }

    override fun logic() {

    }

    override fun create() {

    }
}