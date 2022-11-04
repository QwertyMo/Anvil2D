package ru.kettuproj.game.scene.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Cursor
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.scene.AnvilScene
import ru.kettuproj.game.input.ActionButtons
import ru.kettuproj.game.scene.game.obj.aim.AimDot
import ru.kettuproj.game.scene.game.obj.entity.player.Player

class GameScene : AnvilScene() {

    init{

        width = 512f

        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.None);

        rayHandler.setAmbientLight(.5f)
        setTickRate(60)
        Anvil.eventManager.listen(object : EventListener<InputEvent> {
            override fun handle(event: InputEvent) {
                val action = ActionButtons.valueOf(event.action)
                if(action == ActionButtons.ESC) Anvil.exit()
            }
        })

        val aim = createObject(AimDot(), "aim") as AimDot
        val player = createObject(Player(), "player") as Player
        aim.player = player
    }
    override fun update(delta: Float) {

    }
}