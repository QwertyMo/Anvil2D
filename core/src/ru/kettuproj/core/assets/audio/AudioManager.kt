package ru.kettuproj.core.assets.audio

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import org.apache.log4j.Level
import org.apache.log4j.Logger
import java.io.File

/**
 * Audio manager class for manage sounds loading
 */
class AudioManager(private val assets: List<String>) {
    private val sounds: MutableMap<String, Sound> = mutableMapOf()

    /**
     * Register sound from assets
     *
     * @param path path to sound
     * @param name name
     */
    fun register(path: String, name: String){
        sounds[name] = Gdx.audio.newSound(Gdx.files.internal(path))
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Register sound [$name]")
    }

    /**
     * Auto registering of sounds. For names use sounds names.
     * Sound names must not be duplicated, otherwise they will overwrite each other
     */
    fun autoRegister(){
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "Start auto registering sounds from internal storage")
        assets.filter {
            it.lowercase().endsWith(".mp3") ||
            it.lowercase().endsWith(".ogg")
        }.forEach {
            register(it, File(it).nameWithoutExtension)
        }
    }

    /**
     * Dispose all sounds
     */
    fun disposeAll(){
        sounds.forEach{
            it.value.dispose()
        }
        sounds.clear()
        Logger.getLogger(this.javaClass.name).log(Level.INFO, "All sounds has been disposed")

    }

    /**
     * Dispose sound
     *
     * @param name name
     */
    fun disposeSound(name: String){
        sounds[name]?.dispose()
        sounds.remove(name)
    }

    /**
     * Get sound by name
     *
     * @param name name
     */
    fun getSound(name:String):Sound?{
        return sounds[name]
    }
}