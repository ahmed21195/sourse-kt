package com.app.telefric.makeen.data.source.remote

import com.app.telefric.makeen.data.model.response.MakeenResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MakeenApiService {

    @GET("search")
    fun getMakeen(
        @Query("page") page:Int = 0,
        @Query("per_page") per_page:Int = 10
    ): Single<MakeenResponse>


}


