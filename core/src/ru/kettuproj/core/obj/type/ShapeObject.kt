package ru.kettuproj.core.obj.type

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.obj.AnvilObject


abstract class ShapeObject : AnvilObject() {

    /**
     * Size of object
     */
    val size        : Vector2 = Vector2(0f,0f)

    /**
     * Color of shape. Default it white
     */
    var color: Color = Color.WHITE
        set(value) {
            field = value
        }

    override fun draw(delta: Float){
        scene.batch.end()
        scene.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        scene.shapeRenderer.color = color
        scene.shapeRenderer.rect(position.x, position.y, size.x, size.y)
        scene.shapeRenderer.end();
        scene.batch.begin()
    }
}
