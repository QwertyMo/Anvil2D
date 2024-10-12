package ru.kettuproj.game.scene.game.obj.aim

import ru.kettuproj.core.obj.type.shape.PolygonObject
import ru.kettuproj.core.obj.type.shape.polygon.Polygon
import ru.kettuproj.game.scene.game.obj.entity.player.Player

class AimDot : PolygonObject() {

    var player: Player? = null

    override fun create() {
        polygon = Polygon.SQUARE
        inRenderLogic = true
        size.set(2f, 2f)
    }

    override fun render() {
    }

    override fun logic() {
        translate(scene.getCursor())
    }


}