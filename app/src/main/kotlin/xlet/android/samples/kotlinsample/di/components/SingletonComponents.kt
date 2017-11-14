package xlet.android.samples.kotlinsample.di.components

import dagger.Component
import xlet.android.samples.kotlinsample.BaseApplication
import xlet.android.samples.kotlinsample.di.modules.AppModule
import xlet.android.samples.kotlinsample.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
    fun inject(app: BaseApplication)

    fun loadingComponent(): LoadingComponent.Builder
}
