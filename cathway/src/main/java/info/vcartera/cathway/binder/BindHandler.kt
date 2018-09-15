package info.vcartera.cathway.binder

import kotlin.reflect.KProperty

class BindHandler<T> {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        var returnValue: T? = null
        BindEventDispatcher.dispatch { returnValue = it.resolvePropertyValue(property) }

        if (returnValue == null) throw Exception("Can't get value")

        return returnValue as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        forEachAnnotation(property) {
            controlId, bindType ->
            BindEventDispatcher.dispatch { it.onPropertySet(controlId, property, value, bindType) }
        }
    }

    private fun forEachAnnotation(property: KProperty<*>, action: (controlId: Int, bindType: BindType) -> Unit) {
        property.annotations.forEach {
            when (it) {
                is OneWayBindTo ->
                    action(it.controlId, BindType.ONE_WAY)

                is TwoWayBindTo ->
                    action(it.controlId, BindType.TWO_WAY)

                else -> throw Exception()
            }
        }
    }
}

