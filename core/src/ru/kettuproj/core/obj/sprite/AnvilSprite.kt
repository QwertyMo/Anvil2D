package ru.kettuproj.core.obj.sprite

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import ru.kettuproj.core.Anvil

class AnvilSprite(sprite: String? = null, tint: String? = null ) {
        var sprite: Sprite = Sprite(Texture("engine/empty.png"))
            private set
        var tint  : Sprite = Sprite(Texture("engine/empty.png"))
            private set

        var color: Color = Color.WHITE

        init{
            if(sprite!=null) setSprite(sprite)
            if(tint!=null) setTint(tint)
        }

        private fun setSprite(name: String){
            val region = Anvil.assets.atlas.getSprite(name)
            if(region==null) {
                println("can't find")
                return
            }
            sprite = Sprite(region)
        }

        private fun setTint(name: String){
            val region = Anvil.assets.atlas.getSprite(name)
            if(region==null) {
                //TOOD: Log that can't find
                return
            }
            tint = Sprite(region)
        }

}