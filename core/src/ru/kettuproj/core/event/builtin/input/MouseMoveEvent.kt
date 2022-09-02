package ru.kettuproj.core.event.builtin.input

import com.badlogic.gdx.Gdx
import ru.kettuproj.core.event.AnvilEvent

class MouseMoveEvent(
    x: Int,
    y: Int,
    convert: Boolean = false
) : AnvilEvent(){

    val x: Int
    val y: Int

    init{
        this.x = x
        this.y =
            if(convert) Gdx.graphics.height - y
            else y
    }
}