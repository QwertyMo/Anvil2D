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
 *
 * @author QwertyMo
 */
class MouseMoveEvent(
    x: Int,
    y: Int,
    convert: Boolean = false
) : AnvilEvent(){

    val x: Int
    val y: Int

    init{
        this.x = x
        //Swap Y position from top to bottom, if necessary
        this.y =
            if(convert) Gdx.graphics.height - y
            else y
    }
}