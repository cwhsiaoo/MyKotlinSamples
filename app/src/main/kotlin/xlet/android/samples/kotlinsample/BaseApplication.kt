package xlet.android.samples.kotlinsample

import android.app.Application
import retrofit2.Retrofit
import xlet.android.samples.kotlinsample.di.components.AppComponent
import xlet.android.samples.kotlinsample.di.components.DaggerAppComponent
import xlet.android.samples.kotlinsample.di.modules.AppModule
import xlet.android.samples.kotlinsample.di.modules.NetworkModule
import javax.inject.Inject

class BaseApplication : Application() {
    @Inject lateinit var retrofit: Retrofit

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule("https://api.github.com/"))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}