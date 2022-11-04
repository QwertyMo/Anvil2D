package ru.kettuproj.game.scene.game.obj.aim

import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.type.SpriteObject
import ru.kettuproj.game.scene.game.obj.entity.player.Player
import kotlin.math.pow
import kotlin.math.sqrt

class AimDot : SpriteObject() {

    lateinit var leftBorder : AimBorder
    lateinit var rightBorder: AimBorder

    private var reduction = 8f

    private val maxReduction = 32f
    private val minReduction = 8f

    var player: Player? = null

    override fun create() {
        sprite = Anvil.atlasManager.getSprite("AimDot")

        leftBorder  = createObject(AimBorder(), "left_border" ) as AimBorder
        rightBorder = createObject(AimBorder(), "right_border") as AimBorder
        rightBorder.dynamicRotation = true
        leftBorder.dynamicRotation  = true
    }

    override fun update() {
        calcReduction()
        moveAim()
        super.update()
    }

    private fun calcReduction(){
        val dist = getDist(scene.getCursor(), position)
        if(reduction>minReduction) reduction--
        reduction+=dist
        if(reduction>maxReduction) reduction = maxReduction
    }

    private fun moveAim(){
        translate(scene.getCursor())
        leftBorder.translate(-reduction, 0f)
        rightBorder.translate(-reduction, 0f)
        leftBorder.rotation =  (player?.rotation ?: 0f) - 90f
        rightBorder.rotation = (player?.rotation ?: 0f) + 90f
    }

    private fun getDist(pos1: Vector2, pos2: Vector2): Float{
        return sqrt((pos2.x - pos1.x).pow(2) + (pos2.y - pos1.y).pow(2))
    }

    private fun calcAngle(deg: Float): Float{
        return ((deg - 90f) * Math.PI / 180F).toFloat()
    }
}