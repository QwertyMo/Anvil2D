package ru.kettuproj.core.input

/**
 * Class, contains key, and information, is it pressed on controller.
 * Used in all builtin input events, and in input combiner
 *
 * @param isController flag, if button pressed in controller
 * @param key key code.
 *
 * @see ru.kettuproj.core.input.InputCombiner
 */
data class InputSignal(
    val isController: Boolean,
    val isMouse: Boolean,
    val key: Int
)