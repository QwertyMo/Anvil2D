package ru.kettuproj.core.input

import com.badlogic.gdx.InputProcessor
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.event.builtin.input.MouseMoveEvent
import ru.kettuproj.core.event.builtin.input.ScrollEvent

class AnvilInputProcessor : InputProcessor {

    override fun keyDown(key: Int): Boolean {
        val action = Anvil.input.inputs.getAction(InputSignal(false, key))
        if(action != null){
            Anvil.eventManager.call(InputEvent(action,1f))
            return true
        }
        return false
    }

    override fun keyUp(key: Int): Boolean {
        val action = Anvil.input.inputs.getAction(InputSignal(false, key))
        if(action != null){
            Anvil.eventManager.call(InputEvent(action,0f))
            return true
        }
        return false
    }

    override fun keyTyped(p0: Char): Boolean {
        //println("$p0")
        return true
    }

    override fun touchDown(p0: Int, p1: Int, p2: Int, p3: Int): Boolean {
        //println("$p0 $p1 $p2 $p3")
        return true
    }

    override fun touchUp(p0: Int, p1: Int, p2: Int, p3: Int): Boolean {
        //println("$p0 $p1 $p2 $p3")
        return true
    }

    override fun touchDragged(p0: Int, p1: Int, p2: Int): Boolean {
        //println("$p0 $p1 $p2")
        return true
    }

    override fun mouseMoved(x: Int, y: Int): Boolean {
        Anvil.eventManager.call(MouseMoveEvent(x,y, true))
        return true
    }

    override fun scrolled(scrollX: Float, scrollY: Float): Boolean {
        Anvil.eventManager.call(ScrollEvent(scrollY))
        return true
    }
}