package ru.kettuproj.game.atlas

import ru.kettuproj.core.Anvil

class Atlas {
    init{
        Anvil.atlasManager.register("cursor/cursor", "cursor")
        Anvil.atlasManager.register("player/player", "player")

        Anvil.atlasManager.registerSprite("cursor", "aim_dot", "AimDot")
        Anvil.atlasManager.registerSprite("cursor", "aim_border", "AimBorder")

        Anvil.atlasManager.registerSprite("player", "player", "Player")

    }
}