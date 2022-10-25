package ru.kettuproj.core.input

import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.event.builtin.input.MouseMoveEvent

class InputManager() {

    private val buttons: MutableMap<String, Float> = mutableMapOf()

    val inputs = InputCombiner()
    var onScreenCursor = Vector2(0f,0f)

    init{
        listenActions()
    }

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

    fun buttonState(action: String):Float{
        return buttons[action] ?: 0f
    }
}