package ru.kettuproj.core

import box2dLight.PointLight
import box2dLight.RayHandler
import com.badlogic.gdx.graphics.Color
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.event.builtin.input.ScrollEvent
import ru.kettuproj.core.scene.AnvilScene

class TestScene(ratio: Float) : AnvilScene(ratio) {

    init{
        setTickRate(240)
        rayHandler.setAmbientLight(1f)
        objects["test_1"] = TestObject(this)
        objects["test_1"]?.animation?.speed = 0.1f
        objects["test_1"]?.translate(0f,0f)
        objects["test_2"] = TestObject2(this)
        width = 1024f

        objects["test_2"]?.translate(100f,100f)

        Anvil.eventManager.listen(object : EventListener<ScrollEvent> {
            override fun handle(event: ScrollEvent) {
                zoom(event.value * 0.1f)
            }
        })

    }

    var t = false

    override fun update(delta: Float){

        val up    =   Anvil.input.buttonState("UP")
        val down  = - Anvil.input.buttonState("DOWN")
        val right =   Anvil.input.buttonState("RIGHT")
        val left  = - Anvil.input.buttonState("LEFT")

        objects["test_1"]?.move((right + left) * 1f, (up + down) * 1f)

    }

}