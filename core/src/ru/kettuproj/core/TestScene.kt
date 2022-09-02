package ru.kettuproj.core

import ru.kettuproj.core.scene.AnvilScene

class TestScene : AnvilScene() {

    val obj = TestObject(this)

    init{
        objects["test"] = obj
    }

    var t = false

    override fun update(delta: Float){

        val up    =   Anvil.input.buttonState("UP")
        val down  = - Anvil.input.buttonState("DOWN")
        val right =   Anvil.input.buttonState("RIGHT")
        val left  = - Anvil.input.buttonState("LEFT")

        obj.move((right + left), (up + down))
        obj.rotate(1f)

        if(!t) obj.scale(0.1f,0.1f)
        else obj.scale(-0.1f,-0.1f)

        if(obj.scale.x < 1f) t = false
        if(obj.scale.x > 2f) t = true
    }

}