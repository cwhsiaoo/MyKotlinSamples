package xlet.android.samples.kotlinsample.screen.loading

import android.os.Bundle
import android.widget.ProgressBar
import xlet.android.samples.kotlinsample.BaseApplication
import xlet.android.samples.kotlinsample.base.BaseActivity
import xlet.android.samples.kotlinsample.databinding.ActLoadingBinding
import xlet.android.samples.kotlinsample.di.components.LoadingComponent
import javax.inject.Inject

class LoadingActivity : BaseActivity(), LoadingView {
    @Inject lateinit var binding: ActLoadingBinding
    @Inject lateinit var viewModel: LoadingViewModel
    lateinit var progressBar: ProgressBar
    private val loadingComponent: LoadingComponent by lazy {
        (application as BaseApplication).appComponent
                .loadingComponent()
                .activity(this)
                .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingComponent.inject(this)
        bindSubViews()
    }

    override fun bindSubViews() {
        progressBar = binding.loadingProgressBar
    }

    override fun showDialog(message: String) {
    }
}