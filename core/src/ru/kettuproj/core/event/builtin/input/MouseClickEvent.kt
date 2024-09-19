package ru.kettuproj.core.event.builtin.input

import ru.kettuproj.core.event.AnvilEvent

/**
 * Input event, listening mouse click
 *
 * @param screenX X-Axis position of click
 * @param screenY Y-Axis position of click
 * @param buttonID mouse button id
 * @param isDown click state of button
 *
 * @see ru.kettuproj.core.event.EventManager
 */
data class MouseClickEvent(
  val screenX: Int,
  val screenY: Int,
  val buttonID: Int,
  val isDown: Boolean
) : AnvilEvent()