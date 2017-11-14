package xlet.android.samples.kotlinsample.base

interface BaseViewModel<out T : BaseView> {
    fun getBaseView(): T
}