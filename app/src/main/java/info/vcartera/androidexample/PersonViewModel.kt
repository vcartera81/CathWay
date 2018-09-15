package info.vcartera.androidexample

import info.vcartera.cathway.binder.BindHandler
import info.vcartera.cathway.binder.OneWayBindTo
import info.vcartera.cathway.binder.TwoWayBindTo

class PersonViewModel {

    @TwoWayBindTo(controlId = R.id.firstNameEditText)
    @OneWayBindTo(controlId = R.id.firstNameLabelDuplicator)
    var firstName: String by BindHandler()

    @TwoWayBindTo(controlId = R.id.lastNameEditText)
    @OneWayBindTo(controlId = R.id.lastNameLabelDuplicator)
    var lastName: String by BindHandler()

    @TwoWayBindTo(controlId = R.id.isOver18Cbx)
//    @OneWayBindTo(controlId = R.id.isOver18LabelDuplicator)
    var isOver18: Boolean by BindHandler()
}