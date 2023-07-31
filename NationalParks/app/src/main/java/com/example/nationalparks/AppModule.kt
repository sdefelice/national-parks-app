package com.example.nationalparks

import com.example.nationalparks.common.Constants
import com.example.nationalparks.data.remote.NationalParksAPIClient
import com.example.nationalparks.data.repository.ParkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNationalParksApi(): NationalParksAPIClient {

        val okHttpClientBuilder = OkHttpClient.Builder()

        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY}
        okHttpClientBuilder.addNetworkInterceptor {chain ->
            chain.proceed(chain.request()
                .newBuilder()
                .header("Authorization", "cjqKCaSdu7gDjYkbRDLOM6AomdaGNeqfKqG9ZThA")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")
                .build()
            )
        }.addNetworkInterceptor(logger)

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
            .create(NationalParksAPIClient::class.java)
    }

    @Provides
    @Singleton
    fun provideParkRepository(api: NationalParksAPIClient): ParkRepository {
        return ParkRepository(api)
    }
}