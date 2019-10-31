package me.pushkaranand.copymeanx.api

import me.pushkaranand.copymeanx.BuildConfig
import me.pushkaranand.copymeanx.data.webster.CollegiateEndpointResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebsterDictionary {

    companion object Factory {

        private fun clientBuilder(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }

            val httpClient = OkHttpClient.Builder().apply {
                addInterceptor(interceptor)
            }
            return httpClient.build()
        }

        fun create(): WebsterDictionary {
            val retrofit = Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(clientBuilder())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
            return retrofit.create(WebsterDictionary::class.java)
        }
    }

    @GET("collegiate/json/{word}")
    fun getCollegiateAPIResult(
        @Path("word") word: String,
        @Query("key") key: String
    ): Call<List<CollegiateEndpointResult>>

}