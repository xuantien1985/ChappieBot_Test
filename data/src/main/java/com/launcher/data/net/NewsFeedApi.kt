package com.launcher.data.data.net

//import io.reactivex.Observable
import com.launcher.data.data.entity.reponse.NewsFeedResponseEntity
import com.launcher.data.entity.reponse.DetailResponseEntity
import retrofit2.http.GET

interface NewsFeedApi {
    @GET("newsfeed.json")
    suspend fun getNewsFeed(): NewsFeedResponseEntity

    @GET("detail.json")
    suspend fun getDetail(): DetailResponseEntity
}