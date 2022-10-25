package ru.kettuproj.core.atlas

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import java.util.logging.Level
import java.util.logging.Logger


class AtlasManager {

    private val atlases: MutableMap<String, TextureAtlas> = mutableMapOf()
    private val animations: MutableMap<String, com.badlogic.gdx.utils.Array<AtlasRegion>> = mutableMapOf()
    private val sprites: MutableMap<String, AtlasRegion> = mutableMapOf()

    fun register(path: String, name: String){
        try{
            atlases[name] = TextureAtlas("$path.atlas")
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't initialize texture atlas $path: ${e.message}")
        }
    }
    fun registerSprite(atlas: String, region: String, name: String){
        try{
            sprites[name] = atlases[atlas]!!.findRegion(region)
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't initialize sprite $name: ${e.message}")
        }
    }

    fun registerAnimation(atlas: String, region: String, name: String){
        try{
            animations[name] = atlases[atlas]!!.findRegions(region)
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't initialize animation $name: ${e.message}")
        }
    }

    fun getAnimation(name: String):com.badlogic.gdx.utils.Array<AtlasRegion>?{
        return animations[name]
    }

    fun getSprite(name: String):AtlasRegion?{
        return sprites[name]
    }
}