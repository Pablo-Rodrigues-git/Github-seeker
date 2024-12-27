package com.remotex.github_seeker.di

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.remotex.data.pullrequestlist.service.PullsService
import com.remotex.data.repositorylist.service.RepositoryService
import com.remotex.github_seeker.constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.remotex.github_seeker.constants.Constants.CACHE_CONTROL
import com.remotex.github_seeker.constants.Constants.CACHE_VALUE_WITHOUT_NETWORK
import com.remotex.github_seeker.constants.Constants.CACHE_VALUE_WITH_NETWORK

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    }

    @Provides
    fun provideOkHttp(@ApplicationContext context: Context): OkHttpClient {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        val cache = Cache(context.cacheDir, cacheSize.toLong())

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (isNetworkAvailable(context)) {
                    request.newBuilder().header(CACHE_CONTROL, CACHE_VALUE_WITH_NETWORK).build()
                } else {
                    request.newBuilder().header(CACHE_CONTROL, CACHE_VALUE_WITHOUT_NETWORK).build()
                }
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideReposService(retrofit: Retrofit): RepositoryService =
        retrofit.create(RepositoryService::class.java)

    @Provides
    fun providePullsService(retrofit: Retrofit): PullsService =
        retrofit.create(PullsService::class.java)
}
