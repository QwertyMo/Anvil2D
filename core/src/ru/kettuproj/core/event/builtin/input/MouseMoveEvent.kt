package ru.kettuproj.core.event.builtin.input

import com.badlogic.gdx.Gdx
import ru.kettuproj.core.event.AnvilEvent

/**
 * Input event, listening mouse movement on game screen
 *
 * @param x X-axis of mouse position
 * @param y Y-axis of mouse position
 * @param convert use to change Y-axis zero point from top to bottom
 *
 * @see ru.kettuproj.core.event.EventManager
 */
class MouseMoveEvent(
    val x: Int,
    y: Int,
    convert: Boolean = false
) : AnvilEvent(){

    //Swap Y position from top to bottom, if necessary
    val y: Int = if(convert) Gdx.graphics.height - y
    else y
}