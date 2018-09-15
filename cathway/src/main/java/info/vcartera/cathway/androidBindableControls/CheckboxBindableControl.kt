package info.vcartera.cathway.androidBindableControls

import android.widget.CheckBox
import info.vcartera.cathway.binder.ITwoWayBindableControl

class CheckboxBindableControl(private val control: CheckBox) : ITwoWayBindableControl<Boolean, CheckBox> {
    override val controlType = CheckBox::class.java
    override var onValueChanged: (newValue: Boolean) -> Unit = {}
    override val valueType = Boolean::class.java

    init {
        control.setOnCheckedChangeListener {
            _, isChecked ->
            onValueChanged(isChecked)
        }
    }

    override fun setValue(value: Boolean) {
        control.isChecked = value
    }
}