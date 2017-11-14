package xlet.android.samples.kotlinsample.di.components

import dagger.BindsInstance
import dagger.Subcomponent
import xlet.android.samples.kotlinsample.di.modules.LoadingModule
import xlet.android.samples.kotlinsample.di.utils.ActivityScope
import xlet.android.samples.kotlinsample.screen.loading.LoadingActivity

@ActivityScope
@Subcomponent(modules = arrayOf(LoadingModule::class))
interface LoadingComponent {
    fun inject(activity: LoadingActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: LoadingActivity): Builder

        fun build(): LoadingComponent
    }
}