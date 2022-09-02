package ru.kettuproj.core.input

class InputCombiner {
    private val keys: MutableMap<String, MutableList<InputSignal>> = mutableMapOf()

    fun registerAction(name: String){
        keys[name] = mutableListOf()
    }

    fun checkIsButtonBusy(signal: InputSignal): String?{
        for(item in keys)
            if(item.value.contains(signal)) return item.key
        return null
    }

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

    fun removeButtonFromAction(name: String, signal: InputSignal){
        val inputs = keys[name] ?: return
        inputs.remove(signal)
        keys[name] = inputs
    }

    fun getKeys():Map<String, List<InputSignal>>{
        return keys
    }

    fun getAction(signal: InputSignal):String?{
        for(i in keys)
            if(i.value.contains(signal)) return i.key
        return null
    }
}