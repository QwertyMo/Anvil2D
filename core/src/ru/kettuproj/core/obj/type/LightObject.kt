package ru.kettuproj.core.obj.type

import ru.kettuproj.core.obj.AnvilObject
import ru.kettuproj.core.obj.light.AnvilLight
import ru.kettuproj.core.scene.AnvilScene

abstract class LightObject : AnvilObject() {

    /**
     * Light system of object
     *
     * @author QwertyMo
     */
    var light: AnvilLight? = null

    override fun setScene(scene: AnvilScene) {
        light = AnvilLight(scene)
        super.setScene(scene)
    }

    override fun update() {
        super.update()
        light?.translate(realPos.x, realPos.y)
        light?.setAngle(rotation)

    }

}