package ru.kettuproj.core.scene

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold
import ru.kettuproj.core.obj.AnvilObject

class AnvilContactListener : ContactListener {
    override fun beginContact(contact: Contact?) {
        val objectA = contact?.fixtureA?.body?.userData as AnvilObject?
        val objectB = contact?.fixtureB?.body?.userData as AnvilObject?
        if(objectA == null || objectB == null) return
        //objectA.addContact(objectB)
        //objectB.addContact(objectA)
    }

    override fun endContact(contact: Contact?) {
        val objectA = contact?.fixtureA?.body?.userData as AnvilObject?
        val objectB = contact?.fixtureB?.body?.userData as AnvilObject?
        if(objectA == null || objectB == null) return
        //objectA.removeContact(objectB)
        //objectB.removeContact(objectA)
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
        return
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
        return
    }
}