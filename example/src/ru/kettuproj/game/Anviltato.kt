package ru.kettuproj.game

import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.OnGameRunEvent
import ru.kettuproj.game.atlas.Atlas
import ru.kettuproj.game.input.KeyBind
import ru.kettuproj.game.scene.game.GameScene

class Anviltato: Anvil() {
    init {
        eventManager.listen(object : EventListener<OnGameRunEvent> {
            override fun handle(event: OnGameRunEvent) {
                run()
            }
        })
    }

    fun run(){
        window.setMaxFPS(60)
        window.setVSync(true)
        window.setFullscreen(false)

        KeyBind()
        Atlas()

        screen = GameScene()
    }
}