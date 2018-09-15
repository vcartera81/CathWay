package info.vcartera.cathway.binder

interface IBindableControlFactory {
    fun <TValue>getTwoWayBindableControl(controlId: Int, value: TValue): ITwoWayBindableControl<TValue, *>
    fun <TValue>getOneWayBindableControl(controlId: Int, value: TValue): IOneWayBindableControl<TValue, *>
}