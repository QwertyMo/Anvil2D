package ru.kettuproj.core.obj.type

import ru.kettuproj.core.obj.AnvilObject

abstract class SoundObject: AnvilObject() {

    private var soundId: Long = -1

    var pause: Boolean = true
        set(value){
            field = value
        }

       
}