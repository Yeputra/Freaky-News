package id.freaky.newsapp.API

import id.freaky.newsapp.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("top-headlines")
    fun getHeadlines(@Query("country") country: String, @Query("apiKey") apiKey: String): Call<News>

}