package info.vcartera.cathway.binder

import kotlin.reflect.KProperty

class ModelBinder(private val controlFactory: IBindableControlFactory) {
    private val subscriber: IBindEvent

    private val oneWayBindableControls = mutableMapOf<Int, IOneWayBindableControl<*, *>>()
    private val twoWayBindableControls = mutableMapOf<Int, ITwoWayBindableControl<*, *>>()
    private val values = mutableMapOf<Int, Any>()

    init {
        val that = this
        subscriber = object : IBindEvent {
            override fun <TValue> onPropertySet(controlId: Int, property: KProperty<*>, value: TValue, bindType: BindType) {
                that.onPropertySet(controlId, property, value, bindType)
            }

            override fun <TValue> resolvePropertyValue(property: KProperty<*>): TValue =
                    that.resolvePropertyValue(property)
        }
    }

    fun startWatch() {
        BindEventDispatcher.subscribe(subscriber)
    }

    fun stopWatch() {
        BindEventDispatcher.unsubscribe(subscriber)
    }

    private fun <TValue> onBindableControlValueChanged(property: KProperty<*>, newValue: TValue, controlId: Int) {
        val valueKey = property.hashCode()
        values[valueKey] = newValue as Any

        val controlIds = getAllControlIdsBindToProperty(property).filter { it != controlId }
        controlIds.forEach {
            if (oneWayBindableControls.contains(it)) {
                val control = oneWayBindableControls[it] as IOneWayBindableControl<TValue, *>
                control.setValue(newValue)
            }
            else if(twoWayBindableControls.contains(it)) {
                val control = twoWayBindableControls[it] as ITwoWayBindableControl<TValue, *>
                control.setValue(newValue)
            }
        }
    }

    private fun getAllControlIdsBindToProperty(property: KProperty<*>): List<Int> =
            property
                    .annotations
                    .asSequence()
                    .filter { it is TwoWayBindTo || it is OneWayBindTo }
                    .map {
                        when (it) {
                            is TwoWayBindTo -> it.controlId
                            is OneWayBindTo -> it.controlId
                            else -> throw Exception()
                        }
                    }
                    .toList()

    private fun <TValue> resolvePropertyValue(property: KProperty<*>): TValue =
            values[property.hashCode()] as TValue

    private fun <TValue> onPropertySet(controlId: Int, property: KProperty<*>, value: TValue, bindType: BindType) {
        if (bindType == BindType.TWO_WAY) {
            if (!twoWayBindableControls.containsKey(controlId)) {
                val control = controlFactory.getTwoWayBindableControl(controlId, value)
                control.onValueChanged = { onBindableControlValueChanged(property, it, controlId) }
                twoWayBindableControls[controlId] = control
            }

            val control = twoWayBindableControls[controlId] as ITwoWayBindableControl<TValue, *>
            control.setValue(value)
        }

        if (bindType == BindType.ONE_WAY) {
            if (!oneWayBindableControls.containsKey(controlId)) {
                val control = controlFactory.getOneWayBindableControl(controlId, value)
                oneWayBindableControls[controlId] = control
            }

            val control = oneWayBindableControls[controlId] as IOneWayBindableControl<TValue, *>
            control.setValue(value)
        }
    }
}