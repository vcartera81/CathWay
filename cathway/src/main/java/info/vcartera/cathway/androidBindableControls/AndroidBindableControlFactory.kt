package info.vcartera.cathway.androidBindableControls

import android.support.annotation.IdRes
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import info.vcartera.cathway.binder.IOneWayBindableControl
import info.vcartera.cathway.binder.ITwoWayBindableControl
import java.security.InvalidParameterException

open class AndroidBindableControlFactory(private val rootView: View) : BindableControlFactory() {
    override fun registerStandardControls() {
//        registeredOneWayBindableControls.add()
    }

    override fun <TValue> getTwoWayBindableControl(@IdRes controlId: Int, value: TValue): ITwoWayBindableControl<TValue,*> {
        val control = rootView.findViewById<View>(controlId)
        return when(control) {
            is EditText -> EditTextBindableControl(control) as ITwoWayBindableControl<TValue,*>
            is CheckBox -> CheckboxBindableControl(control) as ITwoWayBindableControl<TValue, *>
            else -> throw InvalidParameterException("There's no two way bindable control implementation for $control.")
        }
    }

    override fun <TValue> getOneWayBindableControl(@IdRes controlId: Int, value: TValue): IOneWayBindableControl<TValue,*> {
        val control = rootView.findViewById<View>(controlId)
        when(control) {
            is TextView -> return TextViewBindableControl(control) as IOneWayBindableControl<TValue, *>
            else -> throw InvalidParameterException("There's no one way bindable control implementation for $control.")
        }
    }
}