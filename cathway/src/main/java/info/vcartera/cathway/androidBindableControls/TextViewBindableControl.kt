package info.vcartera.cathway.androidBindableControls

import android.widget.TextView
import info.vcartera.cathway.binder.IOneWayBindableControl

class TextViewBindableControl(private val control: TextView) : IOneWayBindableControl<String, TextView> {
    override val valueType = String::class.java
    override val controlType = TextView::class.java

    override fun setValue(value: String) {
        control.text = value
    }
}