package ru.kettuproj.core.scene

import ru.kettuproj.core.Anvil

class SceneManager{
    private var game: Anvil? = null

    fun initialize(game: Anvil){
        this.game = game
    }

    fun setScene(scene: AnvilScene){
        game?.screen = scene
    }
}