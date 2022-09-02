package ru.kettuproj.core

import com.badlogic.gdx.Input
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.input.InputSignal

class TestGame : Anvil() {

    init{
        input.inputs.registerAction("UP")
        input.inputs.registerAction("DOWN")
        input.inputs.registerAction("LEFT")
        input.inputs.registerAction("RIGHT")
        input.inputs.registerAction("SCREEN")

        input.inputs.addButtonToAction("UP", InputSignal(false, Input.Keys.UP))
        input.inputs.addButtonToAction("UP", InputSignal(false, Input.Keys.W))

        input.inputs.addButtonToAction("DOWN", InputSignal(false, Input.Keys.DOWN))
        input.inputs.addButtonToAction("DOWN", InputSignal(false, Input.Keys.S))

        input.inputs.addButtonToAction("LEFT", InputSignal(false, Input.Keys.LEFT))
        input.inputs.addButtonToAction("LEFT", InputSignal(false, Input.Keys.A))

        input.inputs.addButtonToAction("RIGHT", InputSignal(false, Input.Keys.RIGHT))
        input.inputs.addButtonToAction("RIGHT", InputSignal(false, Input.Keys.D))

        input.inputs.addButtonToAction("SCREEN", InputSignal(false, Input.Keys.L))

        eventManager.listen(object : EventListener<InputEvent> {
            override fun handle(event: InputEvent) {
                if(event.action == "SCREEN" && event.value == 1f){
                    window.setFullscreen(!window.isFullscreen())
                }
            }
        })
    }

}