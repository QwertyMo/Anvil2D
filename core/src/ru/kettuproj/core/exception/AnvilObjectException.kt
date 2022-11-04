package ru.kettuproj.core.exception

import ru.kettuproj.core.obj.AnvilObject

/**
 * Exception, called when object have some error
 *
 * @author QwertyMo
 */
class AnvilObjectException(message: String, obj: AnvilObject) : Exception(message)