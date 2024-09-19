package ru.kettuproj.core.window

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

/**
 * Windows manager class. Control window settings
 */
class WindowManager {

    private var fullscreen: Boolean = false
    private var size: Vector2 = Vector2(640f,360f)
    private var ratio: Float = (size.y/size.x)

    /**
     * Update window state
     */
    fun update(){
        setFullscreen(fullscreen)
        setWindowSize(size)
    }

    /**
     * Set fullscreen mode
     *
     * @param fullscreen is window in fullscreen mode
     */
    fun setFullscreen(fullscreen: Boolean){
        this.fullscreen = fullscreen
        if(fullscreen) Gdx.graphics.setFullscreenMode(Gdx.graphics.displayMode)
        else Gdx.graphics.setWindowedMode(size.x.toInt(),size.y.toInt())
    }

    /**
     * Checks fullscreen mode
     *
     * @return fullscreen mode
     */
    fun isFullscreen():Boolean{
        return fullscreen
    }

    /**
     * Set window size in pixels
     *
     * @param size size of window
     */
    fun setWindowSize(size: Vector2){
        this.size = size
        if(!fullscreen) Gdx.graphics.setWindowedMode(size.x.toInt(),size.y.toInt())
    }

    /**
     * Set screen ratio
     *
     * @param ratio ratio
     */
    fun setRatio(ratio: Float){
        this.ratio = ratio
    }


    /**
     * Get screen ration
     *
     * @return ratio
     */
    fun getRatio():Float{
        return ratio
    }

    /**
     * Set window title
     *
     * @param title title
     */
    fun setTitle(title: String){
        Gdx.graphics.setTitle(title)
    }

    /**
     * Set FPS limit
     *
     * @param fps FPS limit
     */
    fun setMaxFPS(fps: Int){
        Gdx.graphics.setForegroundFPS(fps)
    }

    /**
     * Set vsync mode
     *
     * @param enable is vsync enabled
     */
    fun setVSync(enable: Boolean){
        Gdx.graphics.setVSync(enable)
    }

}