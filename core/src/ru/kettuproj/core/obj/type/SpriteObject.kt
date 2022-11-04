package ru.kettuproj.core.obj.type

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.obj.AnvilAnimation
import ru.kettuproj.core.obj.AnvilObject

abstract class SpriteObject: AnvilObject() {

    /**
     * Object scale
     *
     * @author QwertyMo
     */
    val scale       : Vector2   = Vector2(1f,1f)

    /**
     * Size of object
     *
     * @author QwertyMo
     */
    val size        : Vector2 = Vector2(0f,0f)

    /**
     * Sprite of object.
     * Automatically set size of object
     *
     * @author QwertyMo
     */
    var sprite   : TextureAtlas.AtlasRegion? = null
        set(value) {
            field = value
            setSpriteSettings()
        }

    /**
     * Animation of object
     *
     * @author QwertyMo
     */
    var animation: AnvilAnimation = AnvilAnimation()

    /**
     * Set sprite settings.
     * Get size of sprite, and set it to object
     *
     * @author QwertyMo
     */
    private fun setSpriteSettings(){
        if(sprite!=null) size.set(sprite!!.regionWidth.toFloat(),sprite!!.regionHeight.toFloat())
    }

    override fun draw() {
        if(sprite!=null){
            scene.batch.draw(
                sprite,
                realPos.x - (size.x)/2,
                realPos.y - (size.y)/2,
                size.x /2,
                size.y /2,
                size.x,
                size.y,
                scale.x,
                scale.y,
                rotation
            )
        }
        super.draw()
    }


}