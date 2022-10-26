package ru.kettuproj.core.obj.light

import box2dLight.ConeLight
import box2dLight.Light
import box2dLight.PointLight
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Body
import ru.kettuproj.core.scene.AnvilScene

//TODO: Maybe need to unbound it from body

/**
 * Light system for object in scene. Initialized in AnvilObject
 *
 * @param scene scene where light object need to be placed
 * @param body linked body
 *
 * @author QwertyMo
 */
class AnvilLight(
    private val scene: AnvilScene,
    private val body:  Body
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

    /**
     * Sets point light
     *
     * @author QwertyMo
     */
    fun setPointLight(){
        light?.dispose()
        light = PointLight(scene.rayHandler,rays)
        (light as PointLight).color = color
        (light as PointLight).distance = distance
        (light as PointLight).attachToBody(body)
    }

    /**
     * Set cone light
     */
    fun setConeLight(){
        light?.dispose()
        light = ConeLight(scene.rayHandler,rays,color,distance,0f,0f, 0f, degree)
        (light as ConeLight).attachToBody(body)
    }
}