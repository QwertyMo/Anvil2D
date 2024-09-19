package ru.kettuproj.core.input

import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.event.builtin.input.MouseMoveEvent

/**
 * Input manager. Contains functions to get all inputs in game
 *
 * @see ru.kettuproj.core.Anvil.input
 */
class InputManager{

    //Action states
    private val buttons: MutableMap<String, Float> = mutableMapOf()

    val inputs = InputCombiner()
    var onScreenCursor = Vector2(0f,0f)

    init{
        listenActions()
    }

    /**
     * Listen registered actions and mouse
     */
    private fun listenActions(){
        //Listen input actions
        Anvil.eventManager.listen(object : EventListener<InputEvent> {
            override fun handle(event: InputEvent) {
                buttons[event.action] = event.value
            }
        })

        Anvil.eventManager.listen(object : EventListener<MouseMoveEvent> {
            override fun handle(event: MouseMoveEvent) {
                onScreenCursor = Vector2(event.x.toFloat(),event.y.toFloat())
            }
        })

    }

    /**
     * Get action state
     *
     * @param action registered action
     *
     * @return state of button from 0.0f to 1.0f, if it is not registered, return 0.0f
     */
    fun buttonState(action: String):Float{
        return buttons[action] ?: 0f
    }
}