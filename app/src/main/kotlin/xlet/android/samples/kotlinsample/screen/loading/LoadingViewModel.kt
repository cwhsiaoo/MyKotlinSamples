package xlet.android.samples.kotlinsample.screen.loading

import android.databinding.ObservableField
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import xlet.android.samples.kotlinsample.base.BaseViewModel
import java.util.concurrent.TimeUnit

class LoadingViewModel(val maxProgress: Int, private val view: LoadingView) : BaseViewModel<LoadingView> {
    var progress = ObservableField(0)
    private lateinit var progressDisposable: Disposable
    override fun getBaseView(): LoadingView = view

    init {

    }

    fun startProgress() {
        progressDisposable = Observable.interval(50, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe({
                    val newProgress = handleProgress(progress.get(), maxProgress)
                    checkProgress(newProgress, maxProgress, view, progressDisposable)
                    progress.set(newProgress)
                })
    }

    private companion object {
        fun handleProgress(currentProgress: Int, maxProgress: Int): Int {
            var nextProgress = currentProgress
            when {
                currentProgress < 0 -> nextProgress = 0
                currentProgress >= maxProgress -> nextProgress = maxProgress
                else -> nextProgress++
            }
            return nextProgress
        }

        fun checkProgress(currentProgress: Int, maxProgress: Int, view: LoadingView, disposable: Disposable) {
            if (currentProgress == maxProgress) {
                view.startNextPage()
                disposable.dispose()
            }
        }
    }
}
