package info.vcartera.cathway.binder

internal class BindEventDispatcher {
    companion object {
        private val subscribers = mutableListOf<IBindEvent>()

        fun subscribe(subscriber: IBindEvent) {
            subscribers.add(subscriber)
        }

        fun unsubscribe(subscriber: IBindEvent) {
            subscribers.remove(subscriber)
        }

        fun dispatch(bindEvent: (bindEvent: IBindEvent) -> Unit) {
            subscribers.forEach(bindEvent)
        }
    }
}