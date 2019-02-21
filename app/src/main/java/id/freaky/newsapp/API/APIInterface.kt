package id.freaky.newsapp.API

import id.freaky.newsapp.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("top-headlines")
    fun getFeatured(@Query("country") country: String, @Query("apiKey") apiKey: String): Call<News>

    @GET("top-headlines")
    fun getForYou(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String): Call<News>

    @GET("top-headlines")
    fun getHealth(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String): Call<News>

    @GET("top-headlines")
    fun getTech(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String): Call<News>

    @GET("top-headlines")
    fun getEntertainment(@Query("country") country: String, @Query("category") category: String, @Query("apiKey") apiKey: String): Call<News>

}