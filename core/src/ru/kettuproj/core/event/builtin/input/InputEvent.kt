package ru.kettuproj.core.event.builtin.input

import ru.kettuproj.core.event.AnvilEvent

/**
 * Input event, listening keyboard, mouse buttons, and controller
 *
 * @param action name of action linked to button
 * @param value value of action ranging from 0f to 1f
 *
 * @see ru.kettuproj.core.event.EventManager
 *
 * @author QwertyMo
 */
class InputEvent(
    val action: String,
    val value: Float
) : AnvilEvent()