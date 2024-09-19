package ru.kettuproj.core.event.builtin.input

import ru.kettuproj.core.event.AnvilEvent

/**
 * Input event, listening mouse scroll
 *
 * @param value scroll value ranging from -1f to 1f
 *
 * @see ru.kettuproj.core.event.EventManager
 */
class ScrollEvent(
    val value: Float
) : AnvilEvent()