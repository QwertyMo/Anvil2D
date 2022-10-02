package ru.kettuproj.core.event.builtin

import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.AnvilEvent

/**
 * System event, listening when game initialized
 *
 * @param game anvil initialized class
 *
 * @see ru.kettuproj.core.event.EventManager
 *
 * @author QwertyMo
 */
class OnGameRunEvent(
    val game: Anvil
) : AnvilEvent()