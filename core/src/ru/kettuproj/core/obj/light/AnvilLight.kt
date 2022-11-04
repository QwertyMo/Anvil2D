package ru.kettuproj.core.obj.light

import box2dLight.ConeLight
import box2dLight.Light
import box2dLight.PointLight
import com.badlogic.gdx.graphics.Color
import ru.kettuproj.core.scene.AnvilScene

//TODO: Move it to LightObject

/**
 * Light system for object in scene. Initialized in AnvilObject
 *
 * @param scene scene where light object need to be placed
 *
 * @author QwertyMo
 */
class AnvilLight(
    private val scene: AnvilScene
) {
    private var light: Light? = null

    var rays : Int = 500

    /**
     * Color of lighting. Default it white
     *
     * @author QwertyMo
     */
    var color: Color = Color.WHITE
        set(value) {
            light?.color = value
            field = value
        }

    //TODO: Cast distance to scene distances
    /**
     * Distance of lighting
     *
     * @author QwertyMo
     */
    var distance: Float = 100f
        set(value) {
            light?.distance = value
            field = value
        }

    /**
     * Degree of cone light
     *
     * @author QwertyMo
     */
    var degree: Float = 45f
        set(value) {
            if(light is ConeLight) (light as ConeLight).coneDegree = value
            field = value
        }

    var rotation: Float = 0f

    /**
     * Sets point light
     *
     * @author QwertyMo
     */
    fun setPointLight(){
        light?.dispose()
        light = PointLight(scene.rayHandler,rays)
        light?.color = color
        light?.distance = distance
    }

    /**
     * Set cone light
     *
     * @author QwertyMo
     */
    fun setConeLight(){
        light?.dispose()
        light = ConeLight(scene.rayHandler,rays,color,distance,0f,0f, 0f, degree)
    }


    fun translate(x: Float, y: Float){
        if(light!=null)
            light!!.setPosition(x, y)
    }

    fun move(x: Float, y: Float){
        if(light!=null)
            light!!.setPosition(
                light!!.position.x + x,
                light!!.position.y + y
            )
    }

    fun rotate(angle: Float){
        rotation+= angle
        if(light is ConeLight)
            light!!.direction = rotation
    }

    fun setAngle(angle: Float){
        rotation = angle
        if(light is ConeLight)
            light!!.direction = rotation
    }
}