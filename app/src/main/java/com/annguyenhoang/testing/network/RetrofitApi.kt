package com.annguyenhoang.testing.network

import com.annguyenhoang.testing.models.Comments
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("comments")
    suspend fun getComments(): Response<List<Comments>>

}