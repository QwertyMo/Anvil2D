package ru.kettuproj.core.atlas

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import java.util.logging.Level
import java.util.logging.Logger

class AtlasManager {

    val atlas: MutableMap<String, TextureAtlas> = mutableMapOf()

    fun register(path: String, name: String){
        try{
            val atl = TextureAtlas(path)
            atlas[name] = atl
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't initialize texture atlas $path: ${e.message}")
        }
    }

}