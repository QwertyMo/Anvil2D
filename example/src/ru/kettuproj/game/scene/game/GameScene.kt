package ru.kettuproj.game.scene.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Cursor
import com.badlogic.gdx.math.Vector2
import ru.kettuproj.core.Anvil
import ru.kettuproj.core.event.EventListener
import ru.kettuproj.core.event.builtin.input.InputEvent
import ru.kettuproj.core.scene.AnvilScene
import ru.kettuproj.game.input.ActionButtons
import ru.kettuproj.game.scene.game.obj.aim.AimDot
import ru.kettuproj.game.scene.game.obj.entity.player.Player
import kotlin.math.*

class GameScene : AnvilScene() {

    val sound: Sound = Gdx.audio.newSound(Gdx.files.internal("audio/phonk.mp3"))
    val id = sound.play(0.0f)
    val pos = Vector2(0f,0f)

    var mute = true

    val aim = createObject(AimDot(), "aim") as AimDot
    val player = createObject(Player(), "player") as Player
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

        aim.player = player


    }
    private fun getDist(pos1: Vector2, pos2: Vector2): Float{
        return sqrt((pos2.x - pos1.x).pow(2) + (pos2.y - pos1.y).pow(2))
    }

    private fun getAngle(pos1: Vector2, pos2: Vector2): Float {
        return atan2(pos1.y - pos2.y,pos1.x - pos2.x) * 180 / Math.PI.toFloat() + 180
    }

    override fun update(delta: Float) {
        var range = 50f
        val dist = getDist(player.position, pos)
        val ang = getAngle(player.position, pos) - 180
        sound.setPan(id, ang/90 - 1, if(!mute) 1/(dist/range) else 0.0f)
    }
}