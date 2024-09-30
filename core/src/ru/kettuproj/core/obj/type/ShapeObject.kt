package ru.kettuproj.core.obj.type

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.obj.AnvilObject

abstract class ShapeObject : AnvilObject() {

    /**
     * Size of object
     */
    open var size        : Vector2 = Vector2(0f,0f)

    /**
     * Color of shape. Default it white
     */
    var color: Color = Color.WHITE
        set(value) {
            field = value
        }

    override fun create() {

    }

}
