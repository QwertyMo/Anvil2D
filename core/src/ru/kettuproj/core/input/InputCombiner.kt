package ru.kettuproj.core.input

/**
 * Combiner inputs from keyboard, mouse, and controller
 */
class InputCombiner {
    private val keys: MutableMap<String, MutableList<InputSignal>> = mutableMapOf()

    /**
     * Register new action, to bind button to this action.
     * You need to register action before binding.
     *
     * @param name Action name
     */
    fun registerAction(name: String){
        keys[name] = mutableListOf()
    }

    /**
     * Check button, if it already binds to action
     *
     * @param signal Signal for checking
     *
     * @return action name or null, if not bind
     *
     * @see ru.kettuproj.core.input.InputSignal
     */
    fun checkIsButtonBusy(signal: InputSignal): String?{
        for(item in keys)
            if(item.value.contains(signal)) return item.key
        return null
    }

    /**
     * Add button to action. You can't bind one button to two or more
     * actions
     *
     * @param name Action name
     * @param signal Button for checking
     * @param rewrite if button already in use, this function rewrite it or not
     *
     * @see ru.kettuproj.core.input.InputSignal
     */
    fun addButtonToAction(name: String, signal: InputSignal, rewrite: Boolean = true){
        val inputs = keys[name] ?: return
        val action = checkIsButtonBusy(signal)
        if(action!=null){
            if(rewrite) removeButtonFromAction(action, signal)
            else return
        }
        inputs.add(signal)
        keys[name] = inputs
    }

    /**
     * Remove button from action
     *
     * @param name Action name
     * @param signal Button to unbind
     *
     * @see ru.kettuproj.core.input.InputSignal
     */
    fun removeButtonFromAction(name: String, signal: InputSignal){
        val inputs = keys[name] ?: return
        inputs.remove(signal)
        keys[name] = inputs
    }

    /**
     * Get all actions with their buttons
     *
     * @return map of all actions with list of buttons
     *
     * @see ru.kettuproj.core.input.InputSignal
     */
    fun getKeys():Map<String, List<InputSignal>>{
        return keys
    }

    /**
     * Get action from button
     *
     * @param signal searching button
     *
     * @return action name or null, if not find
     *
     * @see ru.kettuproj.core.input.InputSignal
     */
    fun getAction(signal: InputSignal):String?{
        for(i in keys)
            if(i.value.contains(signal)) return i.key
        return null
    }
}