package xlet.android.samples.kotlinsample.di.modules

import android.content.Context
import android.os.Build
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xlet.android.samples.kotlinsample.BaseApplication
import xlet.android.samples.kotlinsample.BuildConfig
import xlet.android.samples.kotlinsample.di.utils.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Module
class AppModule @Inject constructor(val app: BaseApplication) {
    @Singleton
    @Provides
    fun provideBaseApplication() : BaseApplication = app

    @Singleton
    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context = app.applicationContext
}

@Module
class NetworkModule @Inject constructor(private val baseUrl: String) {
    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        // Do custom here
        return builder.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        val specBuilder = ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            specBuilder.tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
        } else {
            // It should Larger than Build.VERSION_CODES.HONEYCOMB (SDK 11)
            specBuilder.tlsVersions(TlsVersion.TLS_1_0)
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA)
        }
        builder.connectionSpecs(listOf(specBuilder.build(), ConnectionSpec.CLEARTEXT))

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        val builder = Retrofit.Builder()
        builder.baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
        return builder.build()
    }

}