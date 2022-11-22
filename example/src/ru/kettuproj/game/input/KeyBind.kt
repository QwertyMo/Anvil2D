package ru.kettuproj.game.input

import com.badlogic.gdx.Input
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.input.InputSignal
import ru.kettuproj.core.input.MouseButton

enum class ActionButtons{
    ESC,
    MOVE_UP,
    MOVE_DOWN,
    MOVE_LEFT,
    MOVE_RIGHT,
    SHOOT,
    FULL_SCREEN
}

class KeyBind{
    init{
        Anvil.input.inputs.registerAction(ActionButtons.ESC.name)
        Anvil.input.inputs.registerAction(ActionButtons.MOVE_UP.name)
        Anvil.input.inputs.registerAction(ActionButtons.MOVE_DOWN.name)
        Anvil.input.inputs.registerAction(ActionButtons.MOVE_LEFT.name)
        Anvil.input.inputs.registerAction(ActionButtons.MOVE_RIGHT.name)
        Anvil.input.inputs.registerAction(ActionButtons.SHOOT.name)
        Anvil.input.inputs.registerAction(ActionButtons.FULL_SCREEN.name)

        Anvil.input.inputs.addButtonToAction(ActionButtons.FULL_SCREEN.name, InputSignal(
            isController = false,
            isMouse = false,
            key = Input.Keys.L
        ))
        Anvil.input.inputs.addButtonToAction(ActionButtons.MOVE_UP.name, InputSignal(
            isController = false,
            isMouse = false,
            key = Input.Keys.W
        ))
        Anvil.input.inputs.addButtonToAction(ActionButtons.MOVE_DOWN.name, InputSignal(
            isController = false,
            isMouse = false,
            key = Input.Keys.S
        ))
        Anvil.input.inputs.addButtonToAction(ActionButtons.MOVE_LEFT.name, InputSignal(
            isController = false,
            isMouse = false,
            key = Input.Keys.A
        ))
        Anvil.input.inputs.addButtonToAction(ActionButtons.MOVE_RIGHT.name, InputSignal(
            isController = false,
            isMouse = false,
            key = Input.Keys.D
        ))
        Anvil.input.inputs.addButtonToAction(ActionButtons.ESC.name, InputSignal(
            isController = false,
            isMouse = false,
            key = Input.Keys.ESCAPE
        ))
        Anvil.input.inputs.addButtonToAction(ActionButtons.SHOOT.name, InputSignal(
            isController = false,
            isMouse = true,
            key = MouseButton.MOUSE_LEFT
        ))

    }
}