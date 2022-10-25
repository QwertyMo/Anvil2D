package ru.kettuproj.core

import box2dLight.PointLight
import box2dLight.RayHandler
import com.badlogic.gdx.graphics.Color
import ru.kettuproj.core.scene.AnvilScene

class TestScene() : AnvilScene() {

    var light: PointLight = PointLight(rayHandler, 100, Color.PINK,1000F,0f,0f)

    init{
        rayHandler.setAmbientLight(.5f)
            objects["test_1"] = TestObject(this)
            objects["test_1"]?.animation?.speed = 0.1f
            objects["test_1"]?.translate(0f,0f)

    }

    var t = false

    override fun update(delta: Float){


        val up    =   Anvil.input.buttonState("UP")
        val down  = - Anvil.input.buttonState("DOWN")
        val right =   Anvil.input.buttonState("RIGHT")
        val left  = - Anvil.input.buttonState("LEFT")

        objects["test_1"]?.move((right + left), (up + down))


    }

}