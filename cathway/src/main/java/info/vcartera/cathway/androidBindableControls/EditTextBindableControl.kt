package info.vcartera.cathway.androidBindableControls

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import info.vcartera.cathway.binder.ITwoWayBindableControl

class EditTextBindableControl(private val control: EditText) : ITwoWayBindableControl<String, EditText> {
    override val valueType = String::class.java
    override val controlType = EditText::class.java

    override var onValueChanged: (newValue: String) -> Unit = {}

    init {
        control.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                onValueChanged(p0.toString())
            }
        })
    }

    override fun setValue(value: String) {
        control.setText(value)
    }
}