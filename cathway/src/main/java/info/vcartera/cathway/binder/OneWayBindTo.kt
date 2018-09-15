package info.vcartera.cathway.binder

import android.support.annotation.IdRes

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class OneWayBindTo(@IdRes val controlId: Int)