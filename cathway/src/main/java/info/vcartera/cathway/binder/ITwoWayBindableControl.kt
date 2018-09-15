package info.vcartera.cathway.binder

interface ITwoWayBindableControl<TValue, TControl> : IOneWayBindableControl<TValue, TControl> {
    var onValueChanged: (newValue: TValue) -> Unit
}