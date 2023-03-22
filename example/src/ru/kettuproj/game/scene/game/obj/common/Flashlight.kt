package ru.kettuproj.game.scene.game.obj.common

import com.badlogic.gdx.graphics.Color
import ru.kettuproj.core.obj.type.LightObject

class Flashlight : LightObject() {
    override fun create() {
        light?.setConeLight()
        light?.distance = 300f
        light?.degree = 15f
        light?.color = Color.WHITE
        dynamicRotation = true
    }

    override fun render() {

    }

    override fun logic() {

    }
}