package ru.kettuproj.core.window

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

class WindowManager {

    private var fullscreen: Boolean = false
    private var size: Vector2 = Vector2(640f,360f)
    private var ratio: Float = (size.y/size.x)

    fun update(){
        setFullscreen(fullscreen)
        setWindowSize(size)
    }

    fun setFullscreen(fullscreen: Boolean){
        this.fullscreen = fullscreen
        if(fullscreen) Gdx.graphics.setFullscreenMode(Gdx.graphics.displayMode)
        else Gdx.graphics.setWindowedMode(size.x.toInt(),size.y.toInt())
    }

    fun isFullscreen():Boolean{
        return fullscreen
    }

    fun setWindowSize(size: Vector2){
        this.size = size
        if(!fullscreen) Gdx.graphics.setWindowedMode(size.x.toInt(),size.y.toInt())
    }

    fun setRatio(ratio: Float){
        this.ratio = ratio
    }

    fun getRatio():Float{
        return ratio
    }
}