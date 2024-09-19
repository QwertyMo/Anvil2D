package ru.kettuproj.core.input

import com.badlogic.gdx.InputProcessor
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.event.builtin.input.MouseClickEvent
import ru.kettuproj.core.event.builtin.input.MouseMoveEvent
import ru.kettuproj.core.event.builtin.input.ScrollEvent

/**
 * Default input processor. Call builtin events for keyboard and mouse
 */
class AnvilInputProcessor : InputProcessor {

    override fun keyDown(key: Int): Boolean {
        //Get action key from registered signals, and call input event, that key pressed
        val action = Anvil.input.inputs.getAction(InputSignal(isController = false, isMouse = false, key = key))
        if(action != null){
            Anvil.eventManager.call(InputEvent(action,1f))
            return true
        }
        return false
    }

    override fun keyUp(key: Int): Boolean {
        //Get action key from registered signals, and call input event, that key realized
        val action = Anvil.input.inputs.getAction(InputSignal(isController = false, isMouse = false, key = key))
        if(action != null){
            Anvil.eventManager.call(InputEvent(action,0f))
            return true
        }
        return false
    }


    //TODO: Fix multilang, and make event for it
    override fun keyTyped(p0: Char): Boolean {
        return false
    }

    override fun touchDown(posX: Int, posY: Int, point: Int, button: Int): Boolean {
        //Get action key from registered signals, and call input event, that key pressed
        val action = Anvil.input.inputs.getAction(InputSignal(isController = false, isMouse = true, key = button))
        if(action != null){
            Anvil.eventManager.call(InputEvent(action,1f))
            return true
        }
        //Get mouse click, and call event
        Anvil.eventManager.call(MouseClickEvent(posX,posY,button,true))
        return true
    }

    override fun touchUp(posX: Int, posY: Int, point: Int, button: Int): Boolean {
        //Get action key from registered signals, and call input event, that key realized
        val action = Anvil.input.inputs.getAction(InputSignal(isController = false, isMouse = true, key = button))
        if(action != null){
            Anvil.eventManager.call(InputEvent(action,0f))
            return true
        }
        //Get mouse click, and call event
        Anvil.eventManager.call(MouseClickEvent(posX,posY,button,false))
        return true
    }

    override fun touchDragged(x: Int, y: Int, button: Int): Boolean {
        //Call event that moused move
        Anvil.eventManager.call(MouseMoveEvent(x,y, true))
        return false
    }

    override fun mouseMoved(x: Int, y: Int): Boolean {
        //Call event that moused move
        Anvil.eventManager.call(MouseMoveEvent(x,y, true))
        return true
    }

    override fun scrolled(scrollX: Float, scrollY: Float): Boolean {
        //Call event that mouse wheel scrolled
        Anvil.eventManager.call(ScrollEvent(scrollY))
        return true
    }
}