package ru.kettuproj.core.event.builtin.input

import ru.kettuproj.core.event.AnvilEvent

class InputEvent(
    val action: String,
    val value: Float
) : AnvilEvent()