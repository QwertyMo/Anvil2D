package ru.kettuproj.core.obj

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import java.util.logging.Level
import java.util.logging.Logger

class AnvilAnimation {
    var stop = false
    private val animations: MutableMap<String, com.badlogic.gdx.utils.Array<TextureAtlas.AtlasRegion>> = mutableMapOf()
    private var currentFrame: Int = 0
    private var iterationsBetweenFrames:Int = 0

    var speed: Float = 1f
        set(value){
            field = if(value>1f) 1f
            else kotlin.math.abs(value)
        }
    var state: String = ""
        set(value){
            iterationsBetweenFrames = 0
            currentFrame = 0
            field = value
        }

    fun addState(name: String, animation: com.badlogic.gdx.utils.Array<TextureAtlas.AtlasRegion>){
        animations[name] = animation
    }

    fun update(): TextureAtlas.AtlasRegion?{
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