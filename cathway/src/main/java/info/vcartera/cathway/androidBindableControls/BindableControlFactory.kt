package info.vcartera.cathway.androidBindableControls

import info.vcartera.cathway.binder.IBindableControlFactory
import info.vcartera.cathway.binder.IOneWayBindableControl
import info.vcartera.cathway.binder.ITwoWayBindableControl

abstract class BindableControlFactory : IBindableControlFactory {
    protected val registeredOneWayBindableControls = mutableListOf<IOneWayBindableControl<*, *>>()
    protected val registeredTwoWayBindableControls = mutableListOf<ITwoWayBindableControl<*, *>>()

    protected abstract fun registerStandardControls()
}