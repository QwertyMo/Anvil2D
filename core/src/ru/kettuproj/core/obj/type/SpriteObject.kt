package ru.kettuproj.core.obj.type

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.obj.AnvilAnimation
import ru.kettuproj.core.obj.AnvilObject
import ru.kettuproj.core.obj.sprite.AnvilSprite

abstract class SpriteObject: AnvilObject() {

    /**
     * Object scale
     */
    val scale       : Vector2   = Vector2(1f,1f)

    /**
     * Size of object
     */
    val size        : Vector2 = Vector2(0f,0f)

    /**
     * Sprite of object.
     * Automatically set size of object
     */
    var sprite   : AnvilSprite = AnvilSprite()
        set(value) {
            field = value
            setSpriteSettings()
        }

    /**
     * Animation of object
     */
    var animation: AnvilAnimation = AnvilAnimation()

    /**
     * Set sprite settings.
     * Get size of sprite, and set it to object
     */
    private fun setSpriteSettings(){
        size.set(sprite.sprite.width,sprite.sprite.height)
    }

    override fun draw(delta: Float) {
        if(visible){
            val c = scene.batch.color
            scene.batch.color = sprite.color
            scene.batch.draw(
                sprite.tint,
                renderPos.x - (size.x)/2,
                renderPos.y - (size.y)/2,
                size.x /2,
                size.y /2,
                size.x,
                size.y,
                scale.x,
                scale.y,
                renderRotation
            )
            scene.batch.color = c
            scene.batch.draw(
                sprite.sprite,
                renderPos.x - (size.x)/2,
                renderPos.y - (size.y)/2,
                size.x /2,
                size.y /2,
                size.x,
                size.y,
                scale.x,
                scale.y,
                renderRotation
            )
        }
        super.draw(delta)
    }
}