package ru.kettuproj.core.obj

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import java.util.logging.Level
import java.util.logging.Logger

//TODO: Improve some functions

/**
 * Animation system for object
 */
class AnvilAnimation {

    /**
     * Animation stop state. If stopped, animation freeze at current frame
     */
    var stop = false

    private val animations: MutableMap<String, com.badlogic.gdx.utils.Array<TextureAtlas.AtlasRegion>> = mutableMapOf()
    private var currentFrame: Int = 0
    private var iterationsBetweenFrames:Int = 0

    /**
     * Speed of animation from 0.0f to 1.0f
     */
    var speed: Float = 1f
        set(value){
            field = if(value>1f) 1f
            else kotlin.math.abs(value)
        }

    /**
     * Current animation state
     */
    var state: String = ""
        set(value){
            iterationsBetweenFrames = 0
            currentFrame = 0
            field = value
        }

    /**
     * Add new animation state
     *
     * @param name animation state
     * @param animation atlas region array from atlas manager
     *
     * @see ru.kettuproj.core.atlas.AtlasManager
     */
    fun addState(name: String, animation: com.badlogic.gdx.utils.Array<TextureAtlas.AtlasRegion>){
        animations[name] = animation
    }

    /**
     * Update animation. Called in AnvilObject
     */
    fun update(): TextureAtlas.AtlasRegion?{
        //TODO: Maybe need to be optimized
        try{
            val sprite = animations[state]!![currentFrame]
            if(stop) return sprite
            iterationsBetweenFrames++
            if(iterationsBetweenFrames==(1/speed).toInt()) {
                iterationsBetweenFrames=0
                currentFrame++
            }
            if(currentFrame==animations[state]!!.size){
                currentFrame = 0
            }
            return sprite
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't get frame $currentFrame at animation $state: ${e.message}")
            return null
        }
    }
}