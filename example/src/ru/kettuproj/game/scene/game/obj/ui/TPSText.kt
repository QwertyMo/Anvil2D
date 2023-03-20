package ru.kettuproj.game.scene.game.obj.ui

import ru.kettuproj.core.obj.type.TextObject
import kotlin.math.roundToInt

class TPSText : TextObject() {
    init {
        scale = 1f
    }

    override fun update() {
        super.update()
        text = "${scene.getCurrentTPS()} TPS\n${scene.getCurrentFPS()} FPS"
    }

}