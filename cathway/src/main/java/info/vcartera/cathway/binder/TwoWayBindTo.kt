package info.vcartera.cathway.binder

import android.support.annotation.IdRes

@Target(AnnotationTarget.PROPERTY)
@MustBeDocumented
annotation class TwoWayBindTo(@IdRes val controlId: Int)