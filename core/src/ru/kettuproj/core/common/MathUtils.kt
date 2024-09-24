package ru.kettuproj.core.common

import com.badlogic.gdx.math.Vector2
import kotlin.math.pow
import kotlin.math.sqrt

private fun getDist(pos1: Vector2, pos2: Vector2): Float{
    return sqrt((pos2.x - pos1.x).pow(2) + (pos2.y - pos1.y).pow(2))
}

private fun calcAngle(deg: Float): Float{
    return ((deg - 90f) * Math.PI / 180F).toFloat()
}