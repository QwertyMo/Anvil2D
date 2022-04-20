package ru.kettuproj.core.obj

import com.badlogic.gdx.math.Vector2

abstract class AnvilObject : IAnvilObject{
    var rotation: Int                      = 0
    var scale   : Float                    = 1.0f
    var position: Vector2                  = Vector2(0f,0f)
    val objects : MutableList<AnvilObject> = mutableListOf()

    override fun render() {

    }

    override fun update() {

    }
}