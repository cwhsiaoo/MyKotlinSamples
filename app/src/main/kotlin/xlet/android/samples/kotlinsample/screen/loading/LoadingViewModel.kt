package xlet.android.samples.kotlinsample.screen.loading

import xlet.android.samples.kotlinsample.base.BaseViewModel

class LoadingViewModel(val maxProgress: Int, private val view: LoadingView) : BaseViewModel<LoadingView> {
    var progress = 60
    private val eventHandler = EventHandler()
    override fun getBaseView(): LoadingView = view

    private class EventHandler {
        fun startProgress() {

        }
    }
}
