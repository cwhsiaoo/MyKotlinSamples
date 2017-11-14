package xlet.android.samples.kotlinsample.di.modules

import android.databinding.DataBindingUtil
import dagger.Module
import dagger.Provides
import xlet.android.samples.kotlinsample.R
import xlet.android.samples.kotlinsample.databinding.ActLoadingBinding
import xlet.android.samples.kotlinsample.di.utils.ActivityScope
import xlet.android.samples.kotlinsample.screen.loading.LoadingActivity
import xlet.android.samples.kotlinsample.screen.loading.LoadingView
import xlet.android.samples.kotlinsample.screen.loading.LoadingViewModel

@Module
class LoadingModule {
    @Provides
    @ActivityScope
    fun provideLoadingViewModel(view: LoadingView): LoadingViewModel {
        return LoadingViewModel(100, view)
    }

    @Provides
    @ActivityScope
    fun provideLoadingView(activity: LoadingActivity): LoadingView {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideActLoadingBinding(activity: LoadingActivity, viewModel: LoadingViewModel): ActLoadingBinding {
        val binding: ActLoadingBinding = DataBindingUtil.setContentView(activity, R.layout.act_loading)
        binding.loadingViewModel = viewModel
        return binding
    }
}
