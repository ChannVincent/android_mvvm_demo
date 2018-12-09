package chann.vincent.mvvm.data.network

import chann.vincent.mvvm.data.db.entity.Album
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// https://img8.leboncoin.fr/technical-test.json

interface LeBonCoinApiService {

    @GET("technical-test.json")
    fun getAlbums(): Deferred<List<Album>>

    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor)
                : LeBonCoinApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(connectivityInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://img8.leboncoin.fr/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())   // handle response with coroutine
                .addConverterFactory(GsonConverterFactory.create())     // parse json with gson
                .build()
                .create(LeBonCoinApiService::class.java)
        }
    }
}