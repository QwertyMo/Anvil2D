package ru.kettuproj.game.scene.game.obj.ui

import ru.kettuproj.core.obj.type.TextObject
import kotlin.math.roundToInt

class TPSText : TextObject() {
    init {
        scale = 0.5f
    }

    override fun render() {
        text = "${scene.getCurrentTPS()} TPS\n${scene.getCurrentFPS()} FPS"
    }

    override fun logic() {

    }


}