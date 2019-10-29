package me.pushkaranand.copymeanx.api

import me.pushkaranand.copymeanx.data.webster.CollegiateEndpointResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebsterDictionary {

    @GET("${Urls.COLLEGIATE_URL}/{word}")
    fun getCollegiateAPIResult(
        @Path("word") word: String,
        @Query("key") key: String
    ): Call<List<CollegiateEndpointResult>>

}