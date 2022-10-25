package ru.kettuproj.core.window

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Vector2

/**
 * Windows manager class. Control window settings
 *
 * @author QwertyMo
 */
class WindowManager {

    private var fullscreen: Boolean = false
    private var size: Vector2 = Vector2(640f,360f)
    private var ratio: Float = (size.y/size.x)

    /**
     * Update window state
     *
     * @author QwertyMo
     */
    fun update(){
        setFullscreen(fullscreen)
        setWindowSize(size)
    }

    /**
     * Set fullscreen mode
     *
     * @param fullscreen is window in fullscreen mode
     *
     * @author QwertyMo
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
     *
     * @author QwertyMo
     */
    fun isFullscreen():Boolean{
        return fullscreen
    }

    /**
     * Set window size in pixels
     *
     * @param size size of window
     *
     * @author QwertyMo
     */
    fun setWindowSize(size: Vector2){
        this.size = size
        if(!fullscreen) Gdx.graphics.setWindowedMode(size.x.toInt(),size.y.toInt())
    }

    /**
     * Set screen ratio
     *
     * @param ratio ratio
     *
     * @author QwertyMo
     */
    fun setRatio(ratio: Float){
        this.ratio = ratio
    }


    /**
     * Get screen ration
     *
     * @return ratio
     *
     * @author QwertyMo
     */
    fun getRatio():Float{
        return ratio
    }

    /**
     * Set window title
     *
     * @param title title
     *
     * @author QwertyMo
     */
    fun setTitle(title: String){
        Gdx.graphics.setTitle(title)
    }

    /**
     * Set FPS limit
     *
     * @param fps FPS limit
     *
     * @author QwertyMo
     */
    fun setMaxFPS(fps: Int){
        Gdx.graphics.setForegroundFPS(fps)
    }

    /**
     * Set vsync mode
     *
     * @param enable is vsync enabled
     *
     * @author QwertyMo
     */
    fun setVSync(enable: Boolean){
        Gdx.graphics.setVSync(enable)
    }

}