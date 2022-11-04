package ru.kettuproj.core.atlas

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import ru.kettuproj.core.exception.AtlasException
import java.util.logging.Level
import java.util.logging.Logger


//TODO: Maybe need to make auto registering?
/**
 * Atlas manager class for manage sprites loading
 *
 * @author QwertyMo
 */
class AtlasManager {

    private val atlases: MutableMap<String, TextureAtlas> = mutableMapOf()
    private val animations: MutableMap<String, com.badlogic.gdx.utils.Array<AtlasRegion>> = mutableMapOf()
    private val sprites: MutableMap<String, AtlasRegion> = mutableMapOf()

    /**
     * Register texture atlas from recourses
     *
     * @param path path to atlas
     * @param name name to link to atlas
     *
     * @author QwertyMo
     */
    fun register(path: String, name: String){
        try{
            atlases[name] = TextureAtlas("$path.atlas")

        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't initialize texture atlas $path: ${e.message}")
            throw AtlasException("Can't initialize texture atlas $path: ${e.message}")
        }
    }

    /**
     * Register sprite from atlas
     *
     * @param atlas atlas name, registered earlier
     * @param region region of atlas
     * @param name name to link it to sprite
     *
     * @author QwertyMo
     */
    fun registerSprite(atlas: String, region: String, name: String){
        try{
            sprites[name] = atlases[atlas]!!.findRegion(region)
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't initialize sprite $name: ${e.message}")
            throw AtlasException("Can't initialize sprite $name: ${e.message}")
        }
    }

    /**
     * Register animation from atlas.
     * Regions of atlas, which has format ${NAME}_${INDEX} can combined
     * in animation. Index starts from 0
     *
     * @param atlas atlas name, registered earlier
     * @param region region of atlas
     * @param name name to link it to animation
     *
     * @author QwertyMo
     */
    fun registerAnimation(atlas: String, region: String, name: String){
        try{
            animations[name] = atlases[atlas]!!.findRegions(region)
        }catch (e: Exception){
            Logger.getLogger(this.javaClass.name).log(Level.WARNING,"Can't initialize animation $name: ${e.message}")
            throw AtlasException("Can't initialize sprite $name: ${e.message}")
        }
    }

    /**
     * Get animation from registered animations
     *
     * @param name animation name
     *
     * @return array of atlas regions, if it registered
     *
     * @author QwertyMo
     */
    fun getAnimation(name: String):com.badlogic.gdx.utils.Array<AtlasRegion>?{
        return animations[name]
    }

    /**
     * Get sprite from registered sprites
     *
     * @param name sprite name
     *
     * @return atlas region, if it registered
     *
     * @author QwertyMo
     */
    fun getSprite(name: String):AtlasRegion?{
        return sprites[name]
    }
}