package info.vcartera.cathway.binder

import android.support.annotation.IdRes
import kotlin.reflect.KProperty

internal interface IBindEvent {
    fun <TValue>onPropertySet(@IdRes controlId: Int, property: KProperty<*>, value: TValue, bindType: BindType)
    fun <TValue>resolvePropertyValue(property: KProperty<*>): TValue
}