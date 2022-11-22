package ru.kettuproj.game.atlas

import ru.kettuproj.core.Anvil

class Atlas {
    init{
        Anvil.assets.atlas.register("cursor/cursor.atlas", "cursor")
        Anvil.assets.atlas.register("player/player.atlas", "player")

        Anvil.assets.atlas.registerSprite("cursor", "aim_dot", "AimDot")
        Anvil.assets.atlas.registerSprite("cursor", "aim_border", "AimBorder")

        Anvil.assets.atlas.registerSprite("player", "player", "Player")

    }
}