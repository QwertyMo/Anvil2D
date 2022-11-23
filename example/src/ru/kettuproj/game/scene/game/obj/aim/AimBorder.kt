package ru.kettuproj.game.scene.game.obj.aim

import ru.kettuproj.core.Anvil
import ru.kettuproj.core.obj.sprite.AnvilSprite
import ru.kettuproj.core.obj.type.SpriteObject

class AimBorder : SpriteObject() {
    override fun create() {
        sprite = AnvilSprite("aim_border")
    }
}