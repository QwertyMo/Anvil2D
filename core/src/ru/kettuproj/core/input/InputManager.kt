package ru.kettuproj.core.input

import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent

class InputManager() {

    private val buttons: MutableMap<String, Float> = mutableMapOf()

    val inputs = InputCombiner()

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
    }

    fun buttonState(action: String):Float{
        return buttons[action] ?: 0f
    }
}