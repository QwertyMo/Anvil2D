package ru.kettuproj.core.obj.light

import box2dLight.ConeLight
import box2dLight.DirectionalLight
import box2dLight.Light
import box2dLight.PointLight
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.physics.box2d.Body
import ru.kettuproj.core.scene.AnvilScene

class AnvilLight(
    private val scene: AnvilScene,
    private val body:  Body
) {
    private var light: Light? = null

    var rays : Int = 100
    var color: Color = Color.WHITE
        set(value) {
            light?.color = value
            field = value
        }
    var distance: Float = 100f
        set(value) {
            light?.distance = value
            field = value
        }

    var angle: Float = 45f
        set(value) {
            if(light is ConeLight) (light as ConeLight).coneDegree = value
            field = value
        }

    fun setPointLight(){
        light?.dispose()
        light = PointLight(scene.rayHandler,rays)
        (light as PointLight).color = color
        (light as PointLight).distance = distance
        (light as PointLight).attachToBody(body)
    }

    fun setConeLight(){
        light?.dispose()
        light = ConeLight(scene.rayHandler,rays,color,distance,0f,0f, 0f, angle)
        (light as ConeLight).attachToBody(body)
    }
}