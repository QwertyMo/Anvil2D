package ru.kettuproj.game.scene.game.obj.aim

import ru.kettuproj.core.obj.type.shape.polygon.SquareObject
import ru.kettuproj.game.scene.game.obj.entity.player.Player

class AimDot : SquareObject() {

    var player: Player? = null

    override fun create() {
        inRenderLogic = true
        size.set(2f, 2f)
    }

    override fun render() {
    }

    override fun logic() {
        translate(scene.getCursor())
    }


}