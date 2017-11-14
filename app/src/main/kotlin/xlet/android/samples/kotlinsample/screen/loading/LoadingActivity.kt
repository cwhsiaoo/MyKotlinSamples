package xlet.android.samples.kotlinsample.screen.loading

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import xlet.android.samples.kotlinsample.BaseApplication
import xlet.android.samples.kotlinsample.base.BaseActivity
import xlet.android.samples.kotlinsample.databinding.ActLoadingBinding
import xlet.android.samples.kotlinsample.di.components.LoadingComponent
import xlet.android.samples.kotlinsample.screen.main.MainActivity
import javax.inject.Inject

class LoadingActivity : BaseActivity(), LoadingView {
    @Inject lateinit var binding: ActLoadingBinding
    @Inject lateinit var viewModel: LoadingViewModel
    private lateinit var progressBar: ProgressBar
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
        viewModel.startProgress()
    }

    override fun bindSubViews() {
        progressBar = binding.loadingProgressBar
    }

    override fun startNextPage() {
        runOnUiThread {
            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
        }
    }
}