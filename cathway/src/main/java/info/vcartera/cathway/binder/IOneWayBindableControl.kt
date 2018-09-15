package info.vcartera.cathway.binder

interface IOneWayBindableControl<TValue, TControl> {
    val valueType: Class<TValue>
    val controlType: Class<TControl>
    fun setValue(value: TValue)
}